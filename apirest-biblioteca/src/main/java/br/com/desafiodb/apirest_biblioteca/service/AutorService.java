package br.com.desafiodb.apirest_biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiodb.apirest_biblioteca.enuns.SexoEnum;
import br.com.desafiodb.apirest_biblioteca.model.Autor;
import br.com.desafiodb.apirest_biblioteca.repository.AutorRepository;
import br.com.desafiodb.apirest_biblioteca.util.RegraNegocioException;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public Autor salvaAutor(Autor autor) {
        this.validaSalvaAutor(autor, null);
        return autorRepository.save(autor);
    }

    public List<Autor> listaTodosAutores() {
        return autorRepository.findAll();
    }

    public Optional<Autor> buscaAutorPorId(Long id) {
        if (id == null) {
            throw new RegraNegocioException("ID não informado.");
        }
        return autorRepository.findById(id);
    }

    public Optional<Autor> buscaAutorPorNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new RegraNegocioException("Nome não informado.");
        }
        return autorRepository.findByNome(nome);
    }

    public void deletaAutor(Long id) {
        if (id == null) {
            throw new RegraNegocioException("ID não informado.");
        }
        if (autorRepository.quantidadeLivrosAssociados(id) > 0) {
            throw new RegraNegocioException("Autor possui livro(s) associado(s), não pode ser deletado.");
        }
        autorRepository.deleteById(id);
    }

    public Autor alteraAutor(Autor autor) {
        Optional<Autor> autorExiste = autorRepository.findById(autor.getId());
        if (!autorExiste.isPresent()) {
            throw new RegraNegocioException("Autor não encontrado.");
        }
        Autor autorExistente = autorExiste.get();
        this.validaSalvaAutor(autor, autorExistente);
        autorExistente.setNome(autor.getNome());
        autorExistente.setSexo(autor.getSexo());
        autorExistente.setAnoNascimento(autor.getAnoNascimento());
        autorExistente.setCpf(autor.getCpf());
        Autor autorAtualizado = autorRepository.save(autorExistente);
        return autorAtualizado;
    }

    private void validaSalvaAutor(Autor autor, Autor autorExistente) {
        if (autor.getNome() == null || autor.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do autor não informado.");
        }
        if (!autor.getSexo().equalsIgnoreCase(SexoEnum.FEMININO.getValor())
                && !autor.getSexo().equalsIgnoreCase(SexoEnum.MASCULINO.getValor())) {
            throw new RegraNegocioException("Sexo inválido. Valores devem ser Feminino ou Masculino.");
        }
        if (autor.getAnoNascimento() == null) {
            throw new RegraNegocioException("Ano de nascimento do autor não informado.");
        }
        if (autor.getCpf() == null || autor.getCpf().isEmpty()) {
            throw new RegraNegocioException("CPF do autor não informado.");
        }
        if (autor.getCpf().length() != 11) {
            throw new RegraNegocioException("CPF deve conter onze dígitos numérico.");
        }
        for (char c : autor.getCpf().toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new RegraNegocioException("CPF deve conter apenas dígitos numérico.");
            }
        }
        if (autorExistente == null || !autor.getCpf().equals(autorExistente.getCpf())) {
            if (autorRepository.quantidadeAutoresCpf(autor.getCpf()) > 0) {
                throw new RegraNegocioException("CPF existente.");
            }
        }
    }

}
