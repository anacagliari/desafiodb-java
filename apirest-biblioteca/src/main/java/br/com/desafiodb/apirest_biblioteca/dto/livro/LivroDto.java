package br.com.desafiodb.apirest_biblioteca.dto.livro;

import java.time.LocalDate;

import br.com.desafiodb.apirest_biblioteca.model.Livro;

public class LivroDto {

    private String nome;
    private String isbn;
    private LocalDate dataPublicacao;

    public LivroDto(Livro livro) {
        this.nome = livro.getNome();
        this.isbn = livro.getIsbn();
        this.dataPublicacao = livro.getDataPublicacao();
    }

    public LivroDto() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

}
