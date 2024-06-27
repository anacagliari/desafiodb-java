package br.com.desafiodb.apirest_biblioteca.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.desafiodb.apirest_biblioteca.dto.locatario.LocatarioAlteracaoRequestDto;
import br.com.desafiodb.apirest_biblioteca.dto.locatario.LocatarioAlteracaoResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.locatario.LocatarioConsultaResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.locatario.LocatarioInclusaoRequestDto;
import br.com.desafiodb.apirest_biblioteca.dto.locatario.LocatarioInclusaoResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.locatario.LocatarioListaResponseDto;
import br.com.desafiodb.apirest_biblioteca.model.Locatario;
import br.com.desafiodb.apirest_biblioteca.service.LocatarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/locatario")
@Tag(name = "Locatário", description = "Operações relacionadas aos locatários")
public class LocatarioController {

    @Autowired
    private LocatarioService locatarioService;

    @Operation(summary = "Salvar um locatário", description = "Adiciona um novo locatário ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Locatário salvo com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LocatarioInclusaoResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<LocatarioInclusaoResponseDto> salvaLocatario(@RequestBody LocatarioInclusaoRequestDto locatarioInclusaoRequestDto) {
        LocatarioInclusaoResponseDto response = new LocatarioInclusaoResponseDto(locatarioService.salvaLocatario(locatarioInclusaoRequestDto.parseToModel()));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Listar todos os locatários", description = "Retorna uma lista de todos os locatários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de locatários retornada com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LocatarioListaResponseDto.class)) })
    })
    @GetMapping
    public ResponseEntity<List<LocatarioListaResponseDto>> listaTodosLocatarios() {
        List<Locatario> locatarios = locatarioService.listaTodosLocatarios();
        List<LocatarioListaResponseDto> locatariosDto = new ArrayList<LocatarioListaResponseDto>();
        locatarios.stream().forEach(locatario -> {
            locatariosDto.add(new LocatarioListaResponseDto(locatario));
        });
        return ResponseEntity.ok(locatariosDto);
    }

    @Operation(summary = "Buscar locatário por ID", description = "Retorna um locatário pelo ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locatário encontrado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LocatarioConsultaResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Locatário não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<LocatarioConsultaResponseDto> buscaLocatarioPorId(@PathVariable Long id) {
        Optional<Locatario> locatario = locatarioService.buscaLocatarioPorId(id);
        if (!locatario.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        LocatarioConsultaResponseDto response = new LocatarioConsultaResponseDto(locatario.get());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Atualizar um locatário", description = "Atualiza as informações de um locatário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locatário atualizado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LocatarioAlteracaoResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Locatário não encontrado", content = @Content)
    })
    @PutMapping()
    public ResponseEntity<LocatarioAlteracaoResponseDto> atualizaLocatario(
            @RequestBody LocatarioAlteracaoRequestDto locatario) {
        LocatarioAlteracaoResponseDto locatarioAtualizado = new LocatarioAlteracaoResponseDto(
                locatarioService.alteraLocatario(locatario.parseToModel()));
        return ResponseEntity.ok(locatarioAtualizado);
    }

    @Operation(summary = "Deletar um locatário", description = "Remove um locatário pelo ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Locatário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Locatário não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaLocatario(@PathVariable Long id) {
        Optional<Locatario> locatario = locatarioService.buscaLocatarioPorId(id);
        if (!locatario.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        locatarioService.deletaLocatario(id);
        return ResponseEntity.noContent().build();
    }

}
