package br.com.desafiodb.apirest_biblioteca.dto.aluguel;

import java.math.BigDecimal;

import br.com.desafiodb.apirest_biblioteca.model.Aluguel;
import io.swagger.v3.oas.annotations.media.Schema;

public class AluguelDevolveLivrosResponseV2Dto extends AluguelDto{
    
    @Schema(description = "ID para referência do aluguel", example = "1")
    private Long id;

    @Schema(description = "Valor do aluguel", example = "4.00")
    private BigDecimal valor;

    public AluguelDevolveLivrosResponseV2Dto(Aluguel aluguel) {
        super(aluguel);
        this.id = aluguel.getId();
        this.valor = aluguel.getValor();
    }

    public AluguelDevolveLivrosResponseV2Dto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    
}
