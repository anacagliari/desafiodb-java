package br.com.desafiodb.apirest_biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.desafiodb.apirest_biblioteca.model.Autor;
import br.com.desafiodb.apirest_biblioteca.repository.AutorRepository;
import br.com.desafiodb.apirest_biblioteca.util.RegraNegocioException;

public class AutorServiceTest {

    @InjectMocks
    private AutorService autorService;

    @Mock
    private AutorRepository autorRepository;

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

    @Test
    void testSalvaAutorSexoInvalido() {
        Autor autor = new Autor();
        autor.setNome("Maria João");
        autor.setSexo("M");
        autor.setAnoNascimento(1980);
        autor.setCpf("12345678901");

        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            autorService.salvaAutor(autor);
        });

        assertEquals("Sexo inválido. Valores devem ser Feminino ou Masculino.", exception.getMessage());
    }

    @Test
    void testSalvaAutorComSucesso() {
        Autor autor = new Autor();
        autor.setNome("Autor 1");
        autor.setSexo("MASCULINO");
        autor.setAnoNascimento(1980);
        autor.setCpf("12345678901");

        when(autorRepository.save(any(Autor.class))).thenReturn(autor);

        Autor salvo = autorService.salvaAutor(autor);

        assertNotNull(salvo);
        assertEquals("Autor 1", salvo.getNome());
        verify(autorRepository, times(1)).save(autor);
    }

    @Test
    void testListaTodosAutoresComSucesso() {
        Autor autor1 = new Autor();
        autor1.setNome("Autor 1");
        Autor autor2 = new Autor();
        autor2.setNome("Autor 2");

        when(autorRepository.findAll()).thenReturn(Arrays.asList(autor1, autor2));

        List<Autor> autores = autorService.listaTodosAutores();

        assertEquals(2, autores.size());
        verify(autorRepository, times(1)).findAll();
    }

    @Test
    void testBuscaAutorPorNomeComSucesso() {
        Autor autor = new Autor();
        autor.setNome("Autor 1");
    
        when(autorRepository.findByNome("Autor 1")).thenReturn(Optional.of(autor));
    
        Optional<Autor> resultado = autorService.buscaAutorPorNome("Autor 1");
    
        assertTrue(resultado.isPresent());
        assertEquals("Autor 1", resultado.get().getNome());
        verify(autorRepository, times(1)).findByNome("Autor 1");
    }
    
    @Test
    void testBuscaAutorPorNomeComNomeVazio() {
        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            autorService.buscaAutorPorNome("");
        });
    
        assertEquals("Nome não informado.", exception.getMessage());
    }
    

}
