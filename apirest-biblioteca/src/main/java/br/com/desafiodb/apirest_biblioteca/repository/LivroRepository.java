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

    @Query("SELECT l FROM Livro l JOIN l.alugueis a " +
           "WHERE :data > a.dataRetirada AND :data < a.dataDevolucao")
    List<Livro> listaTodosLivrosAlugados(LocalDate data);

    @Query("SELECT l FROM Livro l JOIN l.alugueis a JOIN a.locatario loc " +
           "WHERE loc.id = :id")
    List<Livro> listaTodosLivrosAlugadosLocatario(Long id);
    
    @Query("SELECT COUNT(L) FROM Livro L WHERE L.isbn = :isbn")
    Long quantidadeLivroIsbn(String isbn);
}
