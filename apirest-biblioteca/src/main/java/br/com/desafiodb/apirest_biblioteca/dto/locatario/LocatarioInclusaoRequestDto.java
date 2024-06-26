package br.com.desafiodb.apirest_biblioteca.dto.locatario;

import br.com.desafiodb.apirest_biblioteca.model.Locatario;

public class LocatarioInclusaoRequestDto extends LocatarioDto {

    public LocatarioInclusaoRequestDto(Locatario locatario) {
        super(locatario);
    }

    public LocatarioInclusaoRequestDto() {
        super();
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
