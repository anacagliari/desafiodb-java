package br.com.desafiodb.apirest_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.desafiodb.apirest_biblioteca.model.Locatario;

public interface LocatarioRepository extends JpaRepository<Locatario, Long> {
    @Query("SELECT COUNT(L) FROM Locatario L WHERE L.cpf = :cpf")
    Long quantidadeLocatariosCpf(String cpf);

    @Query("SELECT COUNT(L) FROM Locatario L WHERE L.email = :email")
    Long quantidadeLocatariosEmail(String email);
}
