package br.com.desafiodb.apirest_biblioteca.dto.aluguel;

import java.time.LocalDate;

import br.com.desafiodb.apirest_biblioteca.model.Aluguel;
import io.swagger.v3.oas.annotations.media.Schema;

public class AluguelDto {

    @Schema(description = "Data de retirada do aluguel do livro", example = "1990-01-01")
    private LocalDate dataRetirada;

    @Schema(description = "Data de devolução do aluguel do livro", example = "1990-01-01")
    private LocalDate dataDevolucao;

    public AluguelDto(Aluguel aluguel) {
        this.dataRetirada = aluguel.getDataRetirada();
        this.dataDevolucao = aluguel.getDataDevolucao();
    }

    public AluguelDto() {
        super();
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

}
