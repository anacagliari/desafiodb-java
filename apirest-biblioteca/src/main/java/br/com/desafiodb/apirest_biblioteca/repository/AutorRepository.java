package br.com.desafiodb.apirest_biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.desafiodb.apirest_biblioteca.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

    @Query(value = "SELECT COUNT(LA.*) FROM LIVRO_AUTOR LA WHERE LA.AUTOR_ID = ?1", nativeQuery = true)
    Long quantidadeLivrosAssociados(Long id);
}
