package br.com.desafiodb.apirest_biblioteca.dto.autor;

import br.com.desafiodb.apirest_biblioteca.model.Autor;

public class AutorInclusaoRequestDto extends AutorDto {

    public AutorInclusaoRequestDto(Autor autor) {
        super(autor);
    }

    public AutorInclusaoRequestDto() {
        super();
    }

    public Autor parseToModel() {
        Autor autor = new Autor();
        autor.setNome(super.getNome());
        autor.setSexo(super.getSexo());
        autor.setAnoNascimento(super.getAnoNascimento());
        autor.setCpf(super.getCpf());
        return autor;
    }
}
