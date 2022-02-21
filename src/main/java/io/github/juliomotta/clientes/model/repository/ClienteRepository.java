package io.github.juliomotta.clientes.model.repository;

import io.github.juliomotta.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
