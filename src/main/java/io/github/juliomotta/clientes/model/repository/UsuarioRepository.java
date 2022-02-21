package io.github.juliomotta.clientes.model.repository;


import io.github.juliomotta.clientes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);
}
