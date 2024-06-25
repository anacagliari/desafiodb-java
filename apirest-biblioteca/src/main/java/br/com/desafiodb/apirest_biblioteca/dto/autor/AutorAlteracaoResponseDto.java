package br.com.desafiodb.apirest_biblioteca.dto.autor;

import br.com.desafiodb.apirest_biblioteca.model.Autor;

public class AutorAlteracaoResponseDto extends AutorDto {
    
    private Long id;

    public AutorAlteracaoResponseDto(Autor autor) {
        super(autor);
        this.id = autor.getId();
    }

    public AutorAlteracaoResponseDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
