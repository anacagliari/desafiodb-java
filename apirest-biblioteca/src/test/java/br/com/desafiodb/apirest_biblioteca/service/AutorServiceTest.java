package br.com.desafiodb.apirest_biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.desafiodb.apirest_biblioteca.model.Autor;
import br.com.desafiodb.apirest_biblioteca.repository.AutorRepository;
import br.com.desafiodb.apirest_biblioteca.util.RegraNegocioException;

class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvaAutorComNomeVazio() {
        Autor autor = new Autor();
        autor.setNome("");
        autor.setSexo("MASCULINO");
        autor.setAnoNascimento(1980);
        autor.setCpf("12345678901");

        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            autorService.salvaAutor(autor);
        });

        assertEquals("Nome do autor não informado.", exception.getMessage());
    }

}
