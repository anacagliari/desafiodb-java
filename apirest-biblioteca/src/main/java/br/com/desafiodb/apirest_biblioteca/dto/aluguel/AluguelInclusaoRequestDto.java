package br.com.desafiodb.apirest_biblioteca.dto.aluguel;

import java.util.ArrayList;
import java.util.List;

import br.com.desafiodb.apirest_biblioteca.model.Aluguel;
import br.com.desafiodb.apirest_biblioteca.model.Livro;
import br.com.desafiodb.apirest_biblioteca.model.Locatario;

public class AluguelInclusaoRequestDto {

    private Long idLocatario;

    private List<LivroIdDto> livros;

    public Long getIdLocatario() {
        return idLocatario;
    }

    public void setIdLocatario(Long idLocatario) {
        this.idLocatario = idLocatario;
    }
    
    public List<LivroIdDto> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroIdDto> livros) {
        this.livros = livros;
    }
    
    public Aluguel parseToModel() {
        Aluguel aluguel = new Aluguel();
        Locatario locatario = new Locatario();
        locatario.setId(this.idLocatario);
        aluguel.setLocatario(locatario);
        List<Livro> listaDeLivros = new ArrayList<Livro>();
        this.livros.stream().forEach(livroDto -> {
            Livro livro = new Livro();
            livro.setId(livroDto.getId());
            listaDeLivros.add(livro);
        });
        aluguel.setLivros(listaDeLivros);
        return aluguel;
    }
}
