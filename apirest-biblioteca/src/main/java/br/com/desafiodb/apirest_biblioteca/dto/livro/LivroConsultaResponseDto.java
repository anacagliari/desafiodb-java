package br.com.desafiodb.apirest_biblioteca.dto.livro;

import java.util.ArrayList;
import java.util.List;

import br.com.desafiodb.apirest_biblioteca.dto.autor.AutorDto;
import br.com.desafiodb.apirest_biblioteca.model.Livro;

public class LivroConsultaResponseDto extends LivroDto {
    
    private Long id;

    private List<AutorDto> autores;

    public LivroConsultaResponseDto(Livro livro) {
        super(livro);
        this.id = livro.getId();
        this.autores = new ArrayList<AutorDto>();
        livro.getAutores().stream().forEach(autor -> {
            this.autores.add(new AutorDto(autor));
        });
    }

    public LivroConsultaResponseDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AutorDto> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorDto> autores) {
        this.autores = autores;
    }

}
