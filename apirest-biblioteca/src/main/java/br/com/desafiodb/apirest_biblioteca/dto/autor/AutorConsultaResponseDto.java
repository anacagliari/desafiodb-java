package br.com.desafiodb.apirest_biblioteca.dto.autor;

import java.util.ArrayList;
import java.util.List;

import br.com.desafiodb.apirest_biblioteca.dto.livro.LivroDto;
import br.com.desafiodb.apirest_biblioteca.model.Autor;
import io.swagger.v3.oas.annotations.media.Schema;

public class AutorConsultaResponseDto extends AutorDto {

    @Schema(description = "ID para referência do autor", example = "1")
    private Long id;
    private List<LivroDto> livros;

    public AutorConsultaResponseDto(Autor autor) {
        super(autor);
        this.id = autor.getId();
        this.livros = new ArrayList<LivroDto>();
        autor.getLivros().stream().forEach(livro -> {
            this.livros.add(new LivroDto(livro));
        });
    }

    public AutorConsultaResponseDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LivroDto> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroDto> livros) {
        this.livros = livros;
    }
}
