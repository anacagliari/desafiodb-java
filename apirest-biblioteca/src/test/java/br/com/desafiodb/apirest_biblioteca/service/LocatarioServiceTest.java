package br.com.desafiodb.apirest_biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.desafiodb.apirest_biblioteca.enuns.SexoEnum;
import br.com.desafiodb.apirest_biblioteca.model.Locatario;
import br.com.desafiodb.apirest_biblioteca.repository.LocatarioRepository;
import br.com.desafiodb.apirest_biblioteca.util.RegraNegocioException;

public class LocatarioServiceTest {

    @InjectMocks
    private LocatarioService locatarioService;

    @Mock
    private LocatarioRepository locatarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvaLocatarioSucesso() {
        Locatario locatario = new Locatario();
        locatario.setNome("Maria João");
        locatario.setEmail("mariajoao@email.com");
        locatario.setSexo(SexoEnum.MASCULINO.getValor());
        locatario.setTelefone(51234567890L);
        locatario.setDataNascimento(LocalDate.of(1990, 1, 1));
        locatario.setCpf("12345678901");

        when(locatarioRepository.save(any(Locatario.class))).thenReturn(locatario);

        Locatario resultado = locatarioService.salvaLocatario(locatario);

        assertNotNull(resultado);
        assertEquals("Maria João", resultado.getNome());
        verify(locatarioRepository, times(1)).save(locatario);
    }

    @Test
    public void testSalvaLocatarioCpfExistente() {
        Locatario locatario = new Locatario();
        locatario.setNome("Maria João");
        locatario.setEmail("mariajoao@email.com");
        locatario.setSexo(SexoEnum.MASCULINO.getValor());
        locatario.setTelefone(51234567890L);
        locatario.setDataNascimento(LocalDate.of(1990, 1, 1));
        locatario.setCpf("12345678901");

        when(locatarioRepository.quantidadeLocatariosCpf(anyString())).thenReturn(1L);

        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            locatarioService.salvaLocatario(locatario);
        });

        assertEquals("CPF existente.", exception.getMessage());
    }

    @Test
    public void testBuscaLocatarioPorId_Sucesso() {
        Locatario locatario = new Locatario();
        locatario.setNome("Maria João");

        when(locatarioRepository.findById(anyLong())).thenReturn(Optional.of(locatario));

        Optional<Locatario> resultado = locatarioService.buscaLocatarioPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Maria João", resultado.get().getNome());
        verify(locatarioRepository, times(1)).findById(1L);
    }

}
