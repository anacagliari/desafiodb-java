package br.com.desafiodb.apirest_biblioteca.dto.livro;

import br.com.desafiodb.apirest_biblioteca.model.Livro;
import io.swagger.v3.oas.annotations.media.Schema;

public class LivroAlteracaoRequestDto extends LivroDto {

    @Schema(description = "ID para referência do livro", example = "1")
    private Long id;

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

    public Livro parseToModel() {
        Livro livro = new Livro();
        livro.setId(this.getId());
        livro.setNome(super.getNome());
        livro.setIsbn(super.getIsbn());
        livro.setDataPublicacao(super.getDataPublicacao());
        return livro;
    }

}
