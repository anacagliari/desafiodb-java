package br.com.desafiodb.apirest_biblioteca.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiodb.apirest_biblioteca.dto.autor.AutorAlteracaoRequestDto;
import br.com.desafiodb.apirest_biblioteca.dto.autor.AutorAlteracaoResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.autor.AutorConsultaResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.autor.AutorInclusaoRequestDto;
import br.com.desafiodb.apirest_biblioteca.dto.autor.AutorInclusaoResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.autor.AutorListaResponseDto;
import br.com.desafiodb.apirest_biblioteca.model.Autor;
import br.com.desafiodb.apirest_biblioteca.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/autor")
@Tag(name = "Autor", description = "Operações relacionadas aos autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Operation(summary = "Salvar um autor", description = "Adiciona um novo autor ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor salvo com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AutorInclusaoResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<AutorInclusaoResponseDto> salvaAutor(
            @RequestBody AutorInclusaoRequestDto autorInclusaoRequestDto) {
        AutorInclusaoResponseDto response = new AutorInclusaoResponseDto(
                autorService.salvaAutor(autorInclusaoRequestDto.parseToModel()));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os autores", description = "Retorna uma lista de todos os autores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de autores retornada com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AutorListaResponseDto.class)) })
    })
    @GetMapping()
    public ResponseEntity<List<AutorListaResponseDto>> listaTodosAutores() {
        List<Autor> autores = autorService.listaTodosAutores();
        List<AutorListaResponseDto> autoresDto = new ArrayList<AutorListaResponseDto>();
        autores.stream().forEach(autor -> {
            autoresDto.add(new AutorListaResponseDto(autor));
        });
        return ResponseEntity.ok(autoresDto);
    }

    @Operation(summary = "Buscar autor por nome", description = "Retorna um autor pelo nome fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AutorConsultaResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = @Content)
    })
    @GetMapping("/nome")
    public ResponseEntity<AutorConsultaResponseDto> buscaAutorPorNome(@RequestParam String nome) {
        Optional<Autor> autor = autorService.buscaAutorPorNome(nome);
        if (!autor.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        AutorConsultaResponseDto response = new AutorConsultaResponseDto(autor.get());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Atualizar um autor", description = "Atualiza as informações de um autor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor atualizado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AutorAlteracaoResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = @Content)
    })
    @PutMapping()
    public ResponseEntity<AutorAlteracaoResponseDto> atualizarAutor(
            @RequestBody AutorAlteracaoRequestDto autor) {
        AutorAlteracaoResponseDto autorAtualizado = new AutorAlteracaoResponseDto(
                autorService.alteraAutor(autor.parseToModel()));
        return ResponseEntity.ok(autorAtualizado);
    }

    @Operation(summary = "Deletar um autor", description = "Remove um autor pelo ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaAutor(@PathVariable Long id) {
        Optional<Autor> autor = autorService.buscaAutorPorId(id);
        if (!autor.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        autorService.deletaAutor(id);
        return ResponseEntity.noContent().build();
    }

}
