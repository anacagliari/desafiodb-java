package br.com.desafiodb.apirest_biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiodb.apirest_biblioteca.model.Locatario;
import br.com.desafiodb.apirest_biblioteca.repository.LocatarioRepository;

@Service
public class LocatarioService {

    @Autowired
    private LocatarioRepository locatarioRepository;

    public Locatario salvaLocatario(Locatario locatario) {
        return locatarioRepository.save(locatario);
    }

    public List<Locatario> listaTodosLocatarios() {
        return locatarioRepository.findAll();
    }

    public Optional<Locatario> buscaLocatarioPorId(Long id) {
        return locatarioRepository.findById(id);
    }

    public void deletaLocatario(Long id) {
        locatarioRepository.deleteById(id);
    }
}
