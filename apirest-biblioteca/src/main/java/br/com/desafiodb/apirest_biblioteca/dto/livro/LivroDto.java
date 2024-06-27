package br.com.desafiodb.apirest_biblioteca.dto.livro;

import java.time.LocalDate;

import br.com.desafiodb.apirest_biblioteca.model.Livro;
import io.swagger.v3.oas.annotations.media.Schema;

public class LivroDto {

    @Schema(description = "Nome do livro", example = "João e Maria")
    private String nome;

    @Schema(description = "ISBN do livro, 13 dígitos", example = "1111122222333")
    private String isbn;

    @Schema(description = "Data de publicação do livro", example = "1990-01-01")
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
