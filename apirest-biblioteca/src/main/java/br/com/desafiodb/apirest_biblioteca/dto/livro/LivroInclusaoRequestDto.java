package br.com.desafiodb.apirest_biblioteca.dto.livro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.desafiodb.apirest_biblioteca.model.Autor;
import br.com.desafiodb.apirest_biblioteca.model.Livro;

public class LivroInclusaoRequestDto extends LivroDto{
    
    private List<AutorIdDto> autores;

    public LivroInclusaoRequestDto(Livro livro){
        super(livro);
    }

    public LivroInclusaoRequestDto() {
        super();
    }

    public List<AutorIdDto> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorIdDto> autores) {
        this.autores = autores;
    }
    
    public Livro parseToModel() {
        Livro livro = new Livro();
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
