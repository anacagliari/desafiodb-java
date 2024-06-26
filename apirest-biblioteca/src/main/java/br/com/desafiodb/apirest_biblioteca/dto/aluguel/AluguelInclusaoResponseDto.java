package br.com.desafiodb.apirest_biblioteca.dto.aluguel;

import br.com.desafiodb.apirest_biblioteca.model.Aluguel;

public class AluguelInclusaoResponseDto extends AlguelDto {

    private Long id;
    
    public AluguelInclusaoResponseDto(Aluguel aluguel) {
        super(aluguel);
        this.id = aluguel.getId();
    }
    
    public AluguelInclusaoResponseDto(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
