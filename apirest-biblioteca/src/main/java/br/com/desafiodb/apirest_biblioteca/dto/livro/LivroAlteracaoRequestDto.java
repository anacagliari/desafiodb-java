package br.com.desafiodb.apirest_biblioteca.dto.livro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.desafiodb.apirest_biblioteca.model.Autor;
import br.com.desafiodb.apirest_biblioteca.model.Livro;
import io.swagger.v3.oas.annotations.media.Schema;

public class LivroAlteracaoRequestDto extends LivroDto {

    @Schema(description = "ID para referência do livro", example = "1")
    private Long id;

    private List<AutorIdDto> autores;

    public LivroAlteracaoRequestDto(Livro livro) {
        super(livro);
        this.id = livro.getId();
    }

    public LivroAlteracaoRequestDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AutorIdDto> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorIdDto> autores) {
        this.autores = autores;
    }

    public Livro parseToModel() {
        Livro livro = new Livro();
        livro.setId(this.getId());
        livro.setNome(super.getNome());
        livro.setIsbn(super.getIsbn());
        livro.setDataPublicacao(super.getDataPublicacao());
        Set<Autor> listaDeAutores = new HashSet<Autor>();
        this.autores.stream().forEach(autorDto -> {
            Autor autor = new Autor();
            autor.setId(autorDto.getId());
            listaDeAutores.add(autor);
        });
        livro.setAutores(listaDeAutores);
        return livro;
    }

}
