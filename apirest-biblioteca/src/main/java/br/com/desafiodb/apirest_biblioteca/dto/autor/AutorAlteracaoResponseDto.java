package br.com.desafiodb.apirest_biblioteca.dto.autor;

import br.com.desafiodb.apirest_biblioteca.model.Autor;
import io.swagger.v3.oas.annotations.media.Schema;

public class AutorAlteracaoResponseDto extends AutorDto {

    @Schema(description = "ID para referência do autor", example = "1")
    private Long id;

    public AutorAlteracaoResponseDto(Autor autor) {
        super(autor);
        this.id = autor.getId();
    }

    public AutorAlteracaoResponseDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
