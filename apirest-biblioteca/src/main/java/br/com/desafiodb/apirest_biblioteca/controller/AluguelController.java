package br.com.desafiodb.apirest_biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiodb.apirest_biblioteca.dto.aluguel.AluguelInclusaoRequestDto;
import br.com.desafiodb.apirest_biblioteca.dto.aluguel.AluguelInclusaoResponseDto;
import br.com.desafiodb.apirest_biblioteca.dto.aluguel.AluguelListaResponseDto;
import br.com.desafiodb.apirest_biblioteca.model.Aluguel;
import br.com.desafiodb.apirest_biblioteca.service.AluguelService;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @PostMapping
    public ResponseEntity<AluguelInclusaoResponseDto> salvaAluguel(@RequestBody AluguelInclusaoRequestDto aluguel) {
        AluguelInclusaoResponseDto aluguelSalvo = new AluguelInclusaoResponseDto(aluguelService.salvaAluguel(aluguel.parseToModel()));
        return ResponseEntity.ok(aluguelSalvo);
    }

    @GetMapping
    public ResponseEntity<List<AluguelListaResponseDto>> listaTodosAlugueis() {
        List<Aluguel> alugueis = aluguelService.listaTodosAlugueis();
        List<AluguelListaResponseDto> response = new ArrayList<AluguelListaResponseDto>();
        alugueis.stream().forEach(aluguel -> {
            response.add(new AluguelListaResponseDto(aluguel));
        });
        return ResponseEntity.ok(response);
    }

}
