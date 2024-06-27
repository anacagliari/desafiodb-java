package br.com.desafiodb.apirest_biblioteca.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiodb.apirest_biblioteca.model.Autor;
import br.com.desafiodb.apirest_biblioteca.model.Livro;
import br.com.desafiodb.apirest_biblioteca.repository.LivroRepository;
import br.com.desafiodb.apirest_biblioteca.util.RegraNegocioException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorService autorService;

    @Autowired
    private LivroGoogleApiService livroGoogleApiService;

    public Livro salvaLivro(Livro livro) {
        this.validaSalvaLivro(livro, null);
        livro.setResumo(livroGoogleApiService.buscaResumoLivro(livro.getIsbn()));
        return livroRepository.save(livro);
    }

    public List<Livro> listaTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> listaTodosLivrosDisponiveis() {
        return livroRepository.listaTodosLivrosDisponiveis(LocalDate.now());
    }

    public List<Livro> listaTodosLivrosAlugados() {
        return livroRepository.listaTodosLivrosAlugados(LocalDate.now());
    }

    public List<Livro> listaTodosLivrosAlugadosLocatario(Long id) {
        return livroRepository.listaTodosLivrosAlugadosLocatario(id);
    }

    public Optional<Livro> buscaLivroPorId(Long id) {
        return livroRepository.findById(id);
    }

    /**
     * Método responsável pela alteração de Livro.
     * Verifica se o Livro passado como parâmetro existe no Banco de Dados, após isso, aplica as alterações.
     * @autor AnaCagliari
     * @param livro
     * @return livroAtualizado
     */
    public Livro alteraLivro(Livro livro) {
        Optional<Livro> livroExiste = livroRepository.findById(livro.getId());
        if (!livroExiste.isPresent()) {
            throw new RegraNegocioException("Livro não encontrado.");
        }
        Livro livroExistente = livroExiste.get();
        this.validaSalvaLivro(livro, livroExistente);
        livroExistente.setNome(livro.getNome());
        livroExistente.setIsbn(livro.getIsbn());
        livroExistente.setDataPublicacao(livro.getDataPublicacao());
        livroExistente.setAutores(livro.getAutores());
        Livro livroAtualizado = livroRepository.save(livroExistente);
        return livroAtualizado;
    }

    public void deletaLivro(Long id) {
        if (livroRepository.quantidadeAlugueisDoLivro(id) > 0) {
            throw new RegraNegocioException("Livro foi alugado, não pode ser deletado.");
        }
        livroRepository.deleteById(id);
    }

    /**
     * Método responsável pela validação das regras do Livro.
     * Verifica se os dados não estão nulos ou vazios dos atributos obrigatórios.
     * Valida o ISBN, onde deve receber 13 dígitos numéricos e ser único.
     * @autor AnaCagliari
     * @param livro
     * @param livroExistente
     */
    private void validaSalvaLivro(Livro livro, Livro livroExistente) {
        if (livro.getNome() == null || livro.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do livro não informado.");
        }
        if (livro.getIsbn() == null || livro.getIsbn().isEmpty()) {
            throw new RegraNegocioException("ISBN do livro não informado.");
        }
        if (livro.getIsbn().length() != 13) {
            throw new RegraNegocioException("ISBN deve conter treze dígitos numérico.");
        }
        if (livroExistente == null || !livro.getIsbn().equals(livroExistente.getIsbn())) {
            if (livroRepository.quantidadeLivroIsbn(livro.getIsbn()) > 0) {
                throw new RegraNegocioException("ISBN existente.");
            }
        }
        for (char c : livro.getIsbn().toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new RegraNegocioException("ISBN deve conter apenas dígitos numérico.");
            }
        }
        if (livro.getDataPublicacao() == null) {
            throw new RegraNegocioException("Ano de publicação do livro não informado.");
        }
        if (livro.getAutores() == null || livro.getAutores().isEmpty()) {
            throw new RegraNegocioException("Autor(es) não informado(s).");
        }
        Set<Autor> autoresConsultados = new HashSet<Autor>();
        for (Autor autor : livro.getAutores()) {
            Optional<Autor> autorExistente = this.autorService.buscaAutorPorId(autor.getId());
            if (!autorExistente.isPresent()) {
                throw new RegraNegocioException("Autor não encontrado.");
            }
            autoresConsultados.add(autorExistente.get());
        }
        livro.setAutores(autoresConsultados);
    }

}
