package br.com.desafiodb.apirest_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiodb.apirest_biblioteca.model.Aluguel;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    
}
