package br.com.desafiodb.apirest_biblioteca.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.desafiodb.apirest_biblioteca.dto.autor.AutorInclusaoRequestDto;
import br.com.desafiodb.apirest_biblioteca.dto.autor.AutorInclusaoResponseDto;
import br.com.desafiodb.apirest_biblioteca.model.Autor;
import br.com.desafiodb.apirest_biblioteca.service.AutorService;

@WebMvcTest(AutorController.class)
public class AutorControllerTest {

    @MockBean
    private AutorService autorService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvaAutor_Sucesso() throws Exception {
        AutorInclusaoRequestDto requestDto = new AutorInclusaoRequestDto();
        requestDto.setNome("Maria João");
        requestDto.setSexo("feminino");
        requestDto.setCpf("12345678901");
        requestDto.setAnoNascimento(1990);

        AutorInclusaoResponseDto responseDto = new AutorInclusaoResponseDto();
        responseDto.setId(1L);
        responseDto.setNome("Maria João");
        responseDto.setSexo("feminino");
        responseDto.setCpf("12345678901");
        responseDto.setAnoNascimento(1990);

        when(autorService.salvaAutor(any(Autor.class))).thenReturn(responseDto.parseToModel());

        mockMvc.perform(post("/autor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Maria João"))
                .andExpect(jsonPath("$.sexo").value("feminino"))
                .andExpect(jsonPath("$.cpf").value("12345678901"))
                .andExpect(jsonPath("$.anoNascimento").value(1990));
    }

    @Test
    public void testListaTodosAutores_Sucesso() throws Exception {
        Autor autor1 = new Autor();
        autor1.setNome("Maria João");
        Autor autor2 = new Autor();
        autor2.setNome("João Maria");

        when(autorService.listaTodosAutores()).thenReturn(Arrays.asList(autor1, autor2));

        mockMvc.perform(get("/autor")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Maria João"))
                .andExpect(jsonPath("$[1].nome").value("João Maria"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
