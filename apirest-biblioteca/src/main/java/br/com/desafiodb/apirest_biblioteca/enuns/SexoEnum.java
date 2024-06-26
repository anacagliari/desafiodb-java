package br.com.desafiodb.apirest_biblioteca.enuns;

public enum SexoEnum {
    FEMININO("feminino"),
    MASCULINO("masculino");

    private String valor;

    private SexoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
