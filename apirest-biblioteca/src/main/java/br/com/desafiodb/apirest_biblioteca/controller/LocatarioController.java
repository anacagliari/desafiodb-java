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

import br.com.desafiodb.apirest_biblioteca.dto.locatario.LocatarioAlteracaoRequestDto;
import br.com.desafiodb.apirest_biblioteca.dto.locatario.LocatarioAlteracaoResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.locatario.LocatarioConsultaResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.locatario.LocatarioInclusaoRequestDto;
import br.com.desafiodb.apirest_biblioteca.dto.locatario.LocatarioInclusaoResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.locatario.LocatarioListaResponseDto;
import br.com.desafiodb.apirest_biblioteca.model.Locatario;
import br.com.desafiodb.apirest_biblioteca.service.LocatarioService;

@RestController
@RequestMapping("/locatario")
public class LocatarioController {

    @Autowired
    private LocatarioService locatarioService;

    @PostMapping
    public ResponseEntity<LocatarioInclusaoResponseDto> salvaLocatario(
            @RequestBody LocatarioInclusaoRequestDto locatarioInclusaoRequestDto) {
        LocatarioInclusaoResponseDto response = new LocatarioInclusaoResponseDto(
                locatarioService.salvaLocatario(locatarioInclusaoRequestDto.parseToModel()));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LocatarioListaResponseDto>> listaTodosLocatarios() {
        List<Locatario> locatarios = locatarioService.listaTodosLocatarios();
        List<LocatarioListaResponseDto> locatariosDto = new ArrayList<LocatarioListaResponseDto>();
        locatarios.stream().forEach(locatario -> {
            locatariosDto.add(new LocatarioListaResponseDto(locatario));
        });
        return ResponseEntity.ok(locatariosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocatarioConsultaResponseDto> buscaLocatarioPorId(@PathVariable Long id) {
        Optional<Locatario> locatario = locatarioService.buscaLocatarioPorId(id);
        if(!locatario.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        LocatarioConsultaResponseDto response = new LocatarioConsultaResponseDto(locatario.get());
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<LocatarioAlteracaoResponseDto> atualizaLocatario(
            @RequestBody LocatarioAlteracaoRequestDto locatario) {
        LocatarioAlteracaoResponseDto locatarioAtualizado = new LocatarioAlteracaoResponseDto(
                locatarioService.alteraLocatario(locatario.parseToModel()));
        return ResponseEntity.ok(locatarioAtualizado);
    }

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
