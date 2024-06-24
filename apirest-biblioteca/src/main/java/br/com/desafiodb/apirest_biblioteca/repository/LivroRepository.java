package br.com.desafiodb.apirest_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiodb.apirest_biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
}
