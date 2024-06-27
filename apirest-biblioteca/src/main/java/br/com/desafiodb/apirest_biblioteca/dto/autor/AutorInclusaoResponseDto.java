package br.com.desafiodb.apirest_biblioteca.dto.autor;

import br.com.desafiodb.apirest_biblioteca.model.Autor;
import io.swagger.v3.oas.annotations.media.Schema;

public class AutorInclusaoResponseDto extends AutorDto {

    @Schema(description = "ID para referência do autor", example = "1")
    private Long id;

    public AutorInclusaoResponseDto(Autor autor) {
        super(autor);
        this.id = autor.getId();
    }

    public AutorInclusaoResponseDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor parseToModel() {
        Autor autor = new Autor();
        autor.setId(this.id);
        autor.setNome(super.getNome());
        autor.setSexo(super.getSexo());
        autor.setAnoNascimento(super.getAnoNascimento());
        autor.setCpf(super.getCpf());
        return autor;
    }

}
