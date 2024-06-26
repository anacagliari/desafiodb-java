package br.com.desafiodb.apirest_biblioteca.dto.locatario;

import br.com.desafiodb.apirest_biblioteca.model.Locatario;
import io.swagger.v3.oas.annotations.media.Schema;

public class LocatarioAlteracaoRequestDto extends LocatarioDto {

    @Schema(description = "ID para referência do locatário", example = "1")
    private Long id;

    public LocatarioAlteracaoRequestDto(Locatario locatario) {
        super(locatario);
        this.id = locatario.getId();
    }

    public LocatarioAlteracaoRequestDto() {
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
        locatario.setId(this.id);
        return locatario;
    }
}
