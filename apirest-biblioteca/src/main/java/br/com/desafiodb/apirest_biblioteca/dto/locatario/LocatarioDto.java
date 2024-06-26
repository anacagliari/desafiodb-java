package br.com.desafiodb.apirest_biblioteca.dto.locatario;

import java.time.LocalDate;

import br.com.desafiodb.apirest_biblioteca.model.Locatario;

public class LocatarioDto {

    private String nome;
    private String sexo;
    private Long telefone;
    private String email;
    private LocalDate dataNascimento;
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
