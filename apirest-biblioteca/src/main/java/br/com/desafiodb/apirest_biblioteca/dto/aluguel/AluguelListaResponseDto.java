package br.com.desafiodb.apirest_biblioteca.dto.aluguel;

import java.util.ArrayList;
import java.util.List;

import br.com.desafiodb.apirest_biblioteca.model.Aluguel;

public class AluguelListaResponseDto extends AlguelDto {
    
    private List<LivroIdNomeDto> livros;

    private Long idLocatario;

    private String nomeLocatario;

    public AluguelListaResponseDto(Aluguel aluguel) {
        super(aluguel);
        this.idLocatario = aluguel.getLocatario().getId();
        this.nomeLocatario = aluguel.getLocatario().getNome();
        this.livros = new ArrayList<LivroIdNomeDto>();
        aluguel.getLivros().stream().forEach(livro -> {
            LivroIdNomeDto livroDto = new LivroIdNomeDto();
            livroDto.setId(livro.getId());
            livroDto.setNome(livro.getNome());
            this.livros.add(livroDto);
        });
    }

    public AluguelListaResponseDto() {
        super();
    }

    public List<LivroIdNomeDto> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroIdNomeDto> livros) {
        this.livros = livros;
    }

    public Long getIdLocatario() {
        return idLocatario;
    }

    public void setIdLocatario(Long idLocatario) {
        this.idLocatario = idLocatario;
    }

    public String getNomeLocatario() {
        return nomeLocatario;
    }

    public void setNomeLocatario(String nomeLocatario) {
        this.nomeLocatario = nomeLocatario;
    }

    

}
