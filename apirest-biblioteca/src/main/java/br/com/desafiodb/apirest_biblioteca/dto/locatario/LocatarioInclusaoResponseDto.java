package br.com.desafiodb.apirest_biblioteca.dto.locatario;

import br.com.desafiodb.apirest_biblioteca.model.Locatario;

public class LocatarioInclusaoResponseDto extends LocatarioDto {

    private Long id;

    public LocatarioInclusaoResponseDto(Locatario locatario) {
        super(locatario);
        this.id = locatario.getId();
    }

    public LocatarioInclusaoResponseDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Locatario parseToModel() {
        Locatario locatario = new Locatario();
        locatario.setNome(super.getNome());
        locatario.setSexo(super.getSexo());
        locatario.setTelefone(super.getTelefone());
        locatario.setEmail(super.getEmail());
        locatario.setDataNascimento(super.getDataNascimento());
        locatario.setCpf(super.getCpf());
        return locatario;
    }
}
