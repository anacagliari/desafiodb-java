package br.com.desafiodb.apirest_biblioteca.dto.autor;

import br.com.desafiodb.apirest_biblioteca.model.Autor;

public class AutorAlteracaoRequestDto extends AutorDto {

    private Long id;

    public AutorAlteracaoRequestDto(Autor autor) {
        super(autor);
        this.id = autor.getId();
    }

    public AutorAlteracaoRequestDto() {
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
        autor.setId(this.getId());
        autor.setNome(super.getNome());
        autor.setSexo(super.getSexo());
        autor.setAnoNascimento(super.getAnoNascimento());
        autor.setCpf(super.getCpf());
        return autor;
    }

}
