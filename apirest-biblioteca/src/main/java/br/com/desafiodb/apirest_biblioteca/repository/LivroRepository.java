package br.com.desafiodb.apirest_biblioteca.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.desafiodb.apirest_biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query(value = "SELECT COUNT(AL.*) FROM ALUGUEL_LIVROS AL WHERE AL.LIVRO_ID = ?1", nativeQuery = true)
    Long quantidadeAlugueisDoLivro(Long id);

    @Query("SELECT l FROM Livro l LEFT JOIN l.alugueis a " +
           "WHERE a IS NULL OR :data < a.dataRetirada OR :data > a.dataDevolucao")
    List<Livro> listaTodosLivrosDisponiveis(LocalDate data);
    
}
