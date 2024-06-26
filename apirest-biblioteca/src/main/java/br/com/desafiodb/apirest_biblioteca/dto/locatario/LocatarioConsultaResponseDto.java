package br.com.desafiodb.apirest_biblioteca.dto.locatario;

import br.com.desafiodb.apirest_biblioteca.model.Locatario;

public class LocatarioConsultaResponseDto extends LocatarioDto {
    
    private Long id;

    public LocatarioConsultaResponseDto(Locatario locatario) {
        super(locatario);
        this.id = locatario.getId();
    }

    public LocatarioConsultaResponseDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
