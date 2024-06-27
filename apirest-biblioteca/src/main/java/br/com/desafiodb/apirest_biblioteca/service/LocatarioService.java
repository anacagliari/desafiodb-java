package br.com.desafiodb.apirest_biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiodb.apirest_biblioteca.enuns.SexoEnum;
import br.com.desafiodb.apirest_biblioteca.model.Locatario;
import br.com.desafiodb.apirest_biblioteca.repository.LocatarioRepository;
import br.com.desafiodb.apirest_biblioteca.util.RegraNegocioException;

@Service
public class LocatarioService {

    @Autowired
    private LocatarioRepository locatarioRepository;

    public Locatario salvaLocatario(Locatario locatario) {
        this.validaSalvaLocatario(locatario, null);
        return locatarioRepository.save(locatario);
    }

    public List<Locatario> listaTodosLocatarios() {
        return locatarioRepository.findAll();
    }

    public Optional<Locatario> buscaLocatarioPorId(Long id) {
        return locatarioRepository.findById(id);
    }

    /**
     * Método responsável pela alteração de Locatário.
     * Verifica se o Locatário passado como parâmetro existe no Banco de Dados, após isso, aplica as alterações.
     * @autor AnaCagliari
     * @param locatario
     * @return locatarioAtualizado
     */
    public Locatario alteraLocatario(Locatario locatario) {
        Optional<Locatario> locatarioExiste = locatarioRepository.findById(locatario.getId());
        if (!locatarioExiste.isPresent()) {
            throw new RegraNegocioException("Locatário não encontrado.");
        }
        Locatario locatarioExistente = locatarioExiste.get();
        this.validaSalvaLocatario(locatario, locatarioExistente);
        locatarioExistente.setNome(locatario.getNome());
        locatarioExistente.setSexo(locatario.getSexo());
        locatarioExistente.setTelefone(locatario.getTelefone());
        locatarioExistente.setEmail(locatario.getEmail());
        locatarioExistente.setDataNascimento(locatario.getDataNascimento());
        locatarioExistente.setCpf(locatario.getCpf());
        Locatario locatarioAtualizado = locatarioRepository.save(locatarioExistente);
        return locatarioAtualizado;
    }

    public void deletaLocatario(Long id) {
        locatarioRepository.deleteById(id);
    }

    /**
     * Método responsável pela validação das regras do Locatário.
     * Verifica se os dados não estão nulos ou vazios dos atributos obrigatórios.
     * Valida o valor recebido de sexo: feminino ou masculino.
     * Valida o telefone, onde deve receber 10 ou 11 dígitos.
     * Valida o CPF, onde deve receber 11 dígitos numéricos e ser único.
     * @autor AnaCagliari
     * @param locatario
     * @param locatarioExistente
     */
    private void validaSalvaLocatario(Locatario locatario, Locatario locatarioExistente) {
        if(locatarioExistente == null || !locatario.getEmail().equals(locatarioExistente.getEmail())){
            this.validaEmail(locatario.getEmail());
        }
        if (locatario.getNome() == null || locatario.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do locatário não informado.");
        }
        if (!locatario.getSexo().equalsIgnoreCase(SexoEnum.FEMININO.getValor())
                && !locatario.getSexo().equalsIgnoreCase(SexoEnum.MASCULINO.getValor())) {
            throw new RegraNegocioException("Sexo inválido. Valores devem ser Feminino ou Masculino.");
        }
        if (locatario.getTelefone() == null) {
            throw new RegraNegocioException("Telefone do locatário não informado.");
        }
        if (locatario.getTelefone().toString().length() < 10 || locatario.getTelefone().toString().length() > 11) {
            throw new RegraNegocioException("Telefone inválido, deve conter dez ou onze dígitos.");
        }
        if (locatario.getDataNascimento() == null) {
            throw new RegraNegocioException("Data de nascimento do locatário não informado.");
        }
        if (locatario.getCpf() == null || locatario.getCpf().isEmpty()) {
            throw new RegraNegocioException("CPF do locatário não informado.");
        }
        if (locatario.getCpf().length() != 11) {
            throw new RegraNegocioException("CPF deve conter onze dígitos numérico.");
        }
        for (char c : locatario.getCpf().toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new RegraNegocioException("CPF deve conter apenas dígitos numérico.");
            }
        }
        if(locatarioExistente == null || !locatario.getCpf().equals(locatarioExistente.getCpf())){
            if (locatarioRepository.quantidadeLocatariosCpf(locatario.getCpf()) > 0) {
                throw new RegraNegocioException("CPF existente.");
            }
        }
    }

    /**
     * Método responsável pela validação do email, onde verifica se está no formato email@email.com
     * @autor AnaCagliari
     * @param email
     */
    private void validaEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new RegraNegocioException("E-mail do locatário não informado.");
        }
        int arrobaPosicao = email.indexOf('@');
        int pontoPosicao = email.lastIndexOf('.');
        if (arrobaPosicao < 1 || pontoPosicao < arrobaPosicao + 2 || pontoPosicao + 2 >= email.length()) {
            throw new RegraNegocioException("E-mail deve ser válido.");
        }
        if (locatarioRepository.quantidadeLocatariosEmail(email) > 0) {
            throw new RegraNegocioException("E-mail existente.");
        }
    }
}
