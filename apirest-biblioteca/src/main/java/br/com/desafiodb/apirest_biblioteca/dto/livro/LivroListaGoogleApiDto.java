package br.com.desafiodb.apirest_biblioteca.dto.livro;

import java.util.List;

public class LivroListaGoogleApiDto {

    private Long totalItems;

    private List<LivroGoogleApiDto> items;

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public List<LivroGoogleApiDto> getItems() {
        return items;
    }

    public void setItems(List<LivroGoogleApiDto> items) {
        this.items = items;
    }

}
