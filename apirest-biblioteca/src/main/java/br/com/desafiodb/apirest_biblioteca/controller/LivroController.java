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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping()
    public ResponseEntity<LivroInclusaoResponseDto> salvaLivro(@Valid @RequestBody LivroInclusaoRequestDto livro) {
        LivroInclusaoResponseDto livroSalvo = new LivroInclusaoResponseDto(livroService.salvaLivro(livro.parseToModel()));
        return ResponseEntity.ok(livroSalvo);
    }

    @GetMapping()
    public ResponseEntity<List<LivroListaResponseDto>> listaTodosLivros() {
        List<Livro> livros = livroService.listaTodosLivros();
        List<LivroListaResponseDto> response = new ArrayList<LivroListaResponseDto>();
        livros.stream().forEach(livro -> {
            response.add(new LivroListaResponseDto(livro));
        });
        return ResponseEntity.ok(response);
    }

    @GetMapping("/disponivel")
    public ResponseEntity<List<LivroListaResponseDto>> listaTodosLivrosDisponiveis() {
        List<Livro> livros = livroService.listaTodosLivrosDisponiveis();
        List<LivroListaResponseDto> response = new ArrayList<LivroListaResponseDto>();
        livros.stream().forEach(livro -> {
            response.add(new LivroListaResponseDto(livro));
        });
        return ResponseEntity.ok(response);
    }

    @GetMapping("/alugados")
    public ResponseEntity<List<LivroListaResponseDto>> listaTodosLivrosAlugados() {
        List<Livro> livros = livroService.listaTodosLivrosAlugados();
        List<LivroListaResponseDto> response = new ArrayList<LivroListaResponseDto>();
        livros.stream().forEach(livro -> {
            response.add(new LivroListaResponseDto(livro));
        });
        return ResponseEntity.ok(response);
    }

    @GetMapping("/locatario/{id}")
    public ResponseEntity<List<LivroListaResponseDto>> listaTodosLivrosAlugadosLocatario(@PathVariable Long id) {
        List<Livro> livros = livroService.listaTodosLivrosAlugadosLocatario(id);
        List<LivroListaResponseDto> response = new ArrayList<LivroListaResponseDto>();
        livros.stream().forEach(livro -> {
            response.add(new LivroListaResponseDto(livro));
        });
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroConsultaResponseDto> buscaLivroPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscaLivroPorId(id);
        if(!livro.isPresent()){
            return ResponseEntity.notFound().build();
        }
        LivroConsultaResponseDto response = new LivroConsultaResponseDto(livro.get());
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<LivroAlteracaoResponseDto> atualizaLivro(@Valid @RequestBody LivroAlteracaoRequestDto livro) {
        LivroAlteracaoResponseDto livroAtualizado = new LivroAlteracaoResponseDto(livroService.alteraLivro(livro.parseToModel()));
        return ResponseEntity.ok(livroAtualizado);
    }

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
