package br.com.desafiodb.apirest_biblioteca.dto.livro;

import java.util.ArrayList;
import java.util.List;

import br.com.desafiodb.apirest_biblioteca.dto.autor.AutorDto;
import br.com.desafiodb.apirest_biblioteca.model.Livro;
import io.swagger.v3.oas.annotations.media.Schema;

public class LivroConsultaResponseDto extends LivroDto {
    
    @Schema(description = "ID para referência do livro", example = "1")
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
