package br.com.desafiodb.apirest_biblioteca.dto.autor;

import br.com.desafiodb.apirest_biblioteca.model.Autor;
import io.swagger.v3.oas.annotations.media.Schema;

public class AutorDto {

    @Schema(description = "Nome do autor", example = "João da Silva")
    private String nome;

    @Schema(description = "Sexo do autor", example = "Masculino")
    private String sexo;

    @Schema(description = "Ano de nascimento do autor", example = "1990")
    private Integer anoNascimento;

    @Schema(description = "CPF do autor", example = "11122233300")
    private String cpf;

    public AutorDto(Autor autor) {
        this.nome = autor.getNome();
        this.sexo = autor.getSexo();
        this.anoNascimento = autor.getAnoNascimento();
        this.cpf = autor.getCpf();
    }

    public AutorDto() {
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

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
