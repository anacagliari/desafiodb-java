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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiodb.apirest_biblioteca.dto.aluguel.AluguelInclusaoRequestDto;
import br.com.desafiodb.apirest_biblioteca.dto.aluguel.AluguelInclusaoResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.aluguel.AluguelListaResponseDto;
import br.com.desafiodb.apirest_biblioteca.model.Aluguel;
import br.com.desafiodb.apirest_biblioteca.service.AluguelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/aluguel")
@Tag(name = "Aluguel", description = "Operações relacionadas aos aluguéis de livros")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @Operation(summary = "Registrar um aluguel", description = "Registra o aluguel de um livro por um locatário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluguel salvo com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AluguelInclusaoResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<AluguelInclusaoResponseDto> salvaAluguel(@RequestBody AluguelInclusaoRequestDto aluguel) {
        AluguelInclusaoResponseDto aluguelSalvo = new AluguelInclusaoResponseDto(
                aluguelService.salvaAluguel(aluguel.parseToModel()));
        return ResponseEntity.ok(aluguelSalvo);
    }

    @Operation(summary = "Listar todos os aluguéis de livros", description = "Retorna uma lista de todos os aluguéis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de aluguéis retornada com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AluguelListaResponseDto.class)) })
    })
    @GetMapping
    public ResponseEntity<List<AluguelListaResponseDto>> listaTodosAlugueis() {
        List<Aluguel> alugueis = aluguelService.listaTodosAlugueis();
        List<AluguelListaResponseDto> response = new ArrayList<AluguelListaResponseDto>();
        alugueis.stream().forEach(aluguel -> {
            response.add(new AluguelListaResponseDto(aluguel));
        });
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deletar um aluguel", description = "Remove um aluguel pelo ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluguel deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluguel não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaAluguel(@PathVariable Long id) {
        Optional<Aluguel> aluguel = aluguelService.buscaAluguelPorId(id);
        if (!aluguel.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        aluguelService.deletaAluguel(id);
        return ResponseEntity.noContent().build();
    }
}
