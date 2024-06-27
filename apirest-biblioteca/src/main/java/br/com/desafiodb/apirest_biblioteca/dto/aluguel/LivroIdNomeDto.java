package br.com.desafiodb.apirest_biblioteca.dto.aluguel;

import io.swagger.v3.oas.annotations.media.Schema;

public class LivroIdNomeDto {

    @Schema(description = "ID para referência do livro para aluguel", example = "1")
    private Long id;

    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
