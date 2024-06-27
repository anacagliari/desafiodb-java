package br.com.desafiodb.apirest_biblioteca.dto.locatario;

import br.com.desafiodb.apirest_biblioteca.model.Locatario;
import io.swagger.v3.oas.annotations.media.Schema;

public class LocatarioListaResponseDto extends LocatarioDto {

    @Schema(description = "ID para referência do locatário", example = "1")
    private Long id;

    public LocatarioListaResponseDto(Locatario locatario) {
        super(locatario);
        this.id = locatario.getId();
    }

    public LocatarioListaResponseDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
