package br.com.desafiodb.apirest_biblioteca.dto.livro;

import br.com.desafiodb.apirest_biblioteca.model.Livro;

public class LivroListaResponseDto extends LivroDto {

    private Long id;

    public LivroListaResponseDto(Livro livro) {
        super(livro);
        this.id = livro.getId();
    }

    public LivroListaResponseDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
