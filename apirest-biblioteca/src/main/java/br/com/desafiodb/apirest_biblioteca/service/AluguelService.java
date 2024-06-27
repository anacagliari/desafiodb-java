package br.com.desafiodb.apirest_biblioteca.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiodb.apirest_biblioteca.model.Aluguel;
import br.com.desafiodb.apirest_biblioteca.model.Livro;
import br.com.desafiodb.apirest_biblioteca.model.Locatario;
import br.com.desafiodb.apirest_biblioteca.repository.AluguelRepository;
import br.com.desafiodb.apirest_biblioteca.util.RegraNegocioException;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private LocatarioService locatarioService;

    @Autowired
    private LivroService livroService;

    public Aluguel salvaAluguel(Aluguel aluguel) {
        validaSalvaAluguel(aluguel);

        aluguel.setDataRetirada(LocalDate.now());

        return aluguelRepository.save(aluguel);
    }

    public List<Aluguel> listaTodosAlugueis() {
        return aluguelRepository.findAll();
    }

    public Optional<Aluguel> buscaAluguelPorId(Long id) {
        if (id == null) {
            throw new RegraNegocioException("ID não informado.");
        }
        return aluguelRepository.findById(id);
    }

    /**
     * Métodos responsável pela validação das regras do Aluguel.
     * Verifica se o locatário e livro(s) foi informado corretamente, não estando nulo, vazio ou inexistente.
     * Verifica se o livro pode ser alugado ou não.
     * @autor AnaCagliari
     * @param aluguel
     */
    private void validaSalvaAluguel(Aluguel aluguel) {
        if (aluguel.getLocatario() == null || aluguel.getLocatario().getId() == null) {
            throw new RegraNegocioException("Locatário não informado.");
        }
        Optional<Locatario> locatarioExistente = locatarioService.buscaLocatarioPorId(aluguel.getLocatario().getId());
        if (!locatarioExistente.isPresent()) {
            throw new RegraNegocioException("Locatário não encontrado.");
        }
        if (aluguel.getLivros() == null || aluguel.getLivros().isEmpty()) {
            throw new RegraNegocioException("Livro(s) não informado(s).");
        }
        List<Livro> livrosAlugados = livroService.listaTodosLivrosAlugados();
        aluguel.getLivros().stream().forEach(livro -> {
            if (livro.getId() == null) {
                throw new RegraNegocioException("Livro não informado.");
            }
            Optional<Livro> livroExistente = livroService.buscaLivroPorId(livro.getId());
            if (!livroExistente.isPresent()) {
                throw new RegraNegocioException("Livro não encontrado. ID: " + livro.getId());
            }
            if (livrosAlugados.contains(livroExistente.get())) {
                throw new RegraNegocioException("Livro está alugado. ID: " + livro.getId());
            }
        });
    }

    public void deletaAluguel(Long id) {
        aluguelRepository.deleteById(id);
    }

    public Aluguel devolucaoLivros(Aluguel aluguel) {
        if (aluguel.getDataDevolucao() != null) {
            throw new RegraNegocioException("Livro(s) deste alguel já devolvido(s).");
        }
        aluguel.setDataDevolucao(LocalDate.now());
        return aluguelRepository.save(aluguel);
    }

    /**
     * Método responsável pela devolução do livro e cobrança do aluguel do livro, incluindo multa de atraso.
     * Permite entrega até as 22:00.
     * @autor AnaCagliari
     * @param aluguel
     * @return
     */
    public Aluguel devolucaoLivrosV2(Aluguel aluguel) {
        if (aluguel.getDataDevolucao() != null) {
            throw new RegraNegocioException("Livro(s) deste alguel já devolvido(s).");
        }
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        LocalDate hoje = LocalDate.now();
        LocalTime hora = LocalTime.of(22, 0);
        LocalDateTime dataHora22 = LocalDateTime.of(hoje, hora);
        if (dataHora22.isBefore(dataHoraAtual)) {
            throw new RegraNegocioException("Entrega somente até as 22:00.");
        }
        aluguel.setDataDevolucao(LocalDate.now());
        aluguel.setValor(calculaValorAluguel(aluguel));
        return aluguelRepository.save(aluguel);
    }

    /**
     * Metódo responável pelo cálculo do aluguel e multa (se houver).
     * Verifica quantos dias o livro estava alugado para cobrar o aluguel e multa (caso passe de 2 dias para entregar).
     * @param aluguel
     * @return
     */
    private BigDecimal calculaValorAluguel(Aluguel aluguel) {
        BigDecimal valor = BigDecimal.valueOf(0.0);
        Long quantidadeDiasAlugados = 1 + aluguel.getDataDevolucao().toEpochDay() - aluguel.getDataRetirada().toEpochDay();
        for(int i = 1; i <= quantidadeDiasAlugados; i++){
            if(i <= 2) {
                valor = valor.add(BigDecimal.valueOf(2));
            } else {
                valor = valor.add(BigDecimal.valueOf(1));
            }
        }
        return valor;
    }
   
}
