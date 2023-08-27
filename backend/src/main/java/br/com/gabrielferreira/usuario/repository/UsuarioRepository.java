package br.com.gabrielferreira.usuario.repository;

import br.com.gabrielferreira.usuario.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u JOIN FETCH u.telefone t JOIN FETCH t.tipoTelefone tt " +
            "JOIN FETCH u.genero g WHERE u.id = :id")
    Optional<Usuario> buscarUsuarioPorId(@Param("id") Long id);

}