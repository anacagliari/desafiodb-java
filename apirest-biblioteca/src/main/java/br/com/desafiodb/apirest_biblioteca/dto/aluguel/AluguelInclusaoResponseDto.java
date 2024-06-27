package br.com.desafiodb.apirest_biblioteca.dto.aluguel;

import br.com.desafiodb.apirest_biblioteca.model.Aluguel;
import io.swagger.v3.oas.annotations.media.Schema;

public class AluguelInclusaoResponseDto extends AlguelDto {

    @Schema(description = "ID para referência do aluguel", example = "1")
    private Long id;

    public AluguelInclusaoResponseDto(Aluguel aluguel) {
        super(aluguel);
        this.id = aluguel.getId();
    }

    public AluguelInclusaoResponseDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
