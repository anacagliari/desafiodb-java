package br.com.desafiodb.apirest_biblioteca.dto.aluguel;

import java.time.LocalDate;

import br.com.desafiodb.apirest_biblioteca.model.Aluguel;

public class AlguelDto {

    private LocalDate dataRetirada;

    private LocalDate dataDevolucao;

    public AlguelDto(Aluguel aluguel) {
        this.dataRetirada = aluguel.getDataRetirada();
        this.dataDevolucao = aluguel.getDataDevolucao();
    }

    public AlguelDto() {
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
