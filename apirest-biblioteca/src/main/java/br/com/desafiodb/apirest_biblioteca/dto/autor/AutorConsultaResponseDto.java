package br.com.desafiodb.apirest_biblioteca.dto.autor;

import java.util.ArrayList;
import java.util.List;

import br.com.desafiodb.apirest_biblioteca.dto.livro.LivroDto;
import br.com.desafiodb.apirest_biblioteca.model.Autor;

public class AutorConsultaResponseDto extends AutorDto {

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
