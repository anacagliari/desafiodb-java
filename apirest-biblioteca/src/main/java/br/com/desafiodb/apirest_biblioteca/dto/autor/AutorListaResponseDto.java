package br.com.desafiodb.apirest_biblioteca.dto.autor;

import br.com.desafiodb.apirest_biblioteca.model.Autor;

public class AutorListaResponseDto extends AutorDto {
    
    private Long id;
    
    public AutorListaResponseDto(Autor autor) {
        super(autor);
        this.id = autor.getId();
    }

    public AutorListaResponseDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  
}
