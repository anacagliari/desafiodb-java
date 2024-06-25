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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping()
    public ResponseEntity<AutorInclusaoResponseDto> salvaAutor(@RequestBody AutorInclusaoRequestDto autorInclusaoRequestDto) {
        AutorInclusaoResponseDto response = new AutorInclusaoResponseDto(autorService.salvaAutor(autorInclusaoRequestDto.parseToModel()));
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<AutorListaResponseDto>> listaTodosAutores() {
        List<Autor> autores = autorService.listaTodosAutores();
        List<AutorListaResponseDto> autoresDto = new ArrayList<AutorListaResponseDto>();
        autores.stream().forEach(autor -> {
            autoresDto.add(new AutorListaResponseDto(autor));
        });
        return ResponseEntity.ok(autoresDto);
    }

    @GetMapping("/nome")
    public ResponseEntity<AutorConsultaResponseDto> buscaAutorPorNome(@RequestParam String nome) {
        Autor autor = autorService.buscaAutorPorNome(nome).orElseThrow(() -> new RuntimeException("Autor não encontrado."));
        AutorConsultaResponseDto response = new AutorConsultaResponseDto(autor);
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<AutorAlteracaoResponseDto> atualizarAutor(@Valid @RequestBody AutorAlteracaoRequestDto autor) {
        AutorAlteracaoResponseDto autorAtualizado = new AutorAlteracaoResponseDto(autorService.alteraAutor(autor.parseToModel()));
        return ResponseEntity.ok(autorAtualizado);
    }

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
