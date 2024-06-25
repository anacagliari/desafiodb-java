package br.com.desafiodb.apirest_biblioteca.dto.livro;

import br.com.desafiodb.apirest_biblioteca.model.Livro;

public class LivroAlteracaoResponseDto extends LivroDto {

    private Long id;

    public LivroAlteracaoResponseDto(Livro livro) {
        super(livro);
        this.id = livro.getId();
    }

    public LivroAlteracaoResponseDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
