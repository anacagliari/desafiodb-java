package br.com.desafiodb.apirest_biblioteca.dto.aluguel;

import io.swagger.v3.oas.annotations.media.Schema;

public class LivroIdDto {

    @Schema(description = "ID para referência do aluguel", example = "1")
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
