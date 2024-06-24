package br.com.desafiodb.apirest_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiodb.apirest_biblioteca.model.Locatario;

public interface LocatarioRepository extends JpaRepository<Locatario, Long> {
    
}
