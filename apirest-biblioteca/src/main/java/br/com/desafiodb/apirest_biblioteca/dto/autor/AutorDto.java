package br.com.desafiodb.apirest_biblioteca.dto.autor;

import br.com.desafiodb.apirest_biblioteca.model.Autor;

public class AutorDto {
    
    private String nome;
    private String sexo;
    private Integer anoNascimento;
    private String cpf;
    
    public AutorDto(Autor autor){
        this.nome = autor.getNome();
        this.sexo = autor.getSexo();
        this.anoNascimento = autor.getAnoNascimento();
        this.cpf = autor.getCpf();
    }

    public AutorDto(){
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
