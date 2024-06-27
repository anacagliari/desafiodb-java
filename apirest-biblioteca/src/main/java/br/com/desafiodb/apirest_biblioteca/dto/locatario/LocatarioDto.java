package br.com.desafiodb.apirest_biblioteca.dto.locatario;

import java.time.LocalDate;

import br.com.desafiodb.apirest_biblioteca.model.Locatario;
import io.swagger.v3.oas.annotations.media.Schema;

public class LocatarioDto {

    @Schema(description = "Nome do locatário", example = "João da Silva")
    private String nome;

    @Schema(description = "Sexo do locatário", example = "Masculino")
    private String sexo;

    @Schema(description = "Telefone do locatário", example = "51987654321")
    private Long telefone;

    @Schema(description = "E-mail do locatário", example = "joao.silva@email.com")
    private String email;

    @Schema(description = "Data de nascimento do locatário", example = "1990-01-01")
    private LocalDate dataNascimento;

    @Schema(description = "CPF do locatário", example = "11122233300")
    private String cpf;

    public LocatarioDto(Locatario locatario) {
        this.nome = locatario.getNome();
        this.sexo = locatario.getSexo();
        this.telefone = locatario.getTelefone();
        this.email = locatario.getEmail();
        this.dataNascimento = locatario.getDataNascimento();
        this.cpf = locatario.getCpf();
    }

    public LocatarioDto() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
