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
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiodb.apirest_biblioteca.dto.livro.LivroAlteracaoRequestDto;
import br.com.desafiodb.apirest_biblioteca.dto.livro.LivroAlteracaoResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.livro.LivroConsultaResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.livro.LivroInclusaoRequestDto;
import br.com.desafiodb.apirest_biblioteca.dto.livro.LivroInclusaoResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.livro.LivroListaResponseDto;
import br.com.desafiodb.apirest_biblioteca.model.Livro;
import br.com.desafiodb.apirest_biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livro")
@Tag(name = "Livro", description = "Operações relacionadas aos livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Operation(summary = "Salvar um livro", description = "Adiciona um novo livro ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro salvo com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LivroInclusaoResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<LivroInclusaoResponseDto> salvaLivro(@Valid @RequestBody LivroInclusaoRequestDto livro) {
        LivroInclusaoResponseDto livroSalvo = new LivroInclusaoResponseDto(
                livroService.salvaLivro(livro.parseToModel()));
        return ResponseEntity.ok(livroSalvo);
    }

    @Operation(summary = "Listar todos os livros", description = "Retorna uma lista de todos os livros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de livros retornada com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LivroListaResponseDto.class)) })
    })
    @GetMapping()
    public ResponseEntity<List<LivroListaResponseDto>> listaTodosLivros() {
        List<Livro> livros = livroService.listaTodosLivros();
        List<LivroListaResponseDto> response = new ArrayList<LivroListaResponseDto>();
        livros.stream().forEach(livro -> {
            response.add(new LivroListaResponseDto(livro));
        });
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar os livros disponíveis para alugar", description = "Retorna uma lista de livros disponíveis para alugar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de livros disponíveis retornada com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LivroListaResponseDto.class)) })
    })
    @GetMapping("/disponivel")
    public ResponseEntity<List<LivroListaResponseDto>> listaTodosLivrosDisponiveis() {
        List<Livro> livros = livroService.listaTodosLivrosDisponiveis();
        List<LivroListaResponseDto> response = new ArrayList<LivroListaResponseDto>();
        livros.stream().forEach(livro -> {
            response.add(new LivroListaResponseDto(livro));
        });
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar os livros alugados", description = "Retorna uma lista de livros alugados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de livros alugados retornada com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LivroListaResponseDto.class)) })
    })
    @GetMapping("/alugados")
    public ResponseEntity<List<LivroListaResponseDto>> listaTodosLivrosAlugados() {
        List<Livro> livros = livroService.listaTodosLivrosAlugados();
        List<LivroListaResponseDto> response = new ArrayList<LivroListaResponseDto>();
        livros.stream().forEach(livro -> {
            response.add(new LivroListaResponseDto(livro));
        });
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar os livros alugados por locatário", description = "Retorna uma lista de livros alugados por locatário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de livros alugados de um locatário retornada com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LivroListaResponseDto.class)) })
    })
    @GetMapping("/locatario/{id}")
    public ResponseEntity<List<LivroListaResponseDto>> listaTodosLivrosAlugadosLocatario(@PathVariable Long id) {
        List<Livro> livros = livroService.listaTodosLivrosAlugadosLocatario(id);
        List<LivroListaResponseDto> response = new ArrayList<LivroListaResponseDto>();
        livros.stream().forEach(livro -> {
            response.add(new LivroListaResponseDto(livro));
        });
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar livro por ID", description = "Retorna um livro pelo ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LivroConsultaResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<LivroConsultaResponseDto> buscaLivroPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscaLivroPorId(id);
        if (!livro.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        LivroConsultaResponseDto response = new LivroConsultaResponseDto(livro.get());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Atualizar um livro", description = "Atualiza as informações de um livro existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LivroInclusaoResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado", content = @Content)
    })
    @PutMapping()
    public ResponseEntity<LivroAlteracaoResponseDto> atualizaLivro(@RequestBody LivroAlteracaoRequestDto livro) {
        LivroAlteracaoResponseDto livroAtualizado = new LivroAlteracaoResponseDto(
                livroService.alteraLivro(livro.parseToModel()));
        return ResponseEntity.ok(livroAtualizado);
    }

    @Operation(summary = "Deletar um livro", description = "Remove um livro pelo ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaLivro(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscaLivroPorId(id);
        if (!livro.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        livroService.deletaLivro(id);
        return ResponseEntity.noContent().build();
    }

}
