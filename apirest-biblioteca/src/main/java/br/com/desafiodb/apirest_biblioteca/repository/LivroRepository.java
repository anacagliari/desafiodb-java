package br.com.desafiodb.apirest_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.desafiodb.apirest_biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    @Query(value = "SELECT COUNT(AL.*) FROM ALUGUEL_LIVROS AL WHERE AL.LIVRO_ID = ?1", nativeQuery = true)
    Long quantidadeAlugueisDoLivro(Long id);

}
