package io.github.juliomotta.clientes.service;

import io.github.juliomotta.clientes.model.entity.Cliente;
import io.github.juliomotta.clientes.model.repository.ClienteRepository;
import io.github.juliomotta.clientes.rest.dto.ClienteDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteDTO save( ClienteDTO cliente ) {
        Cliente cli = new Cliente();
        cli.setNome(cliente.getNome());
        cli.setCpf(cliente.getCpf());
         repository.save(cli);
         cliente.setId(cli.getId());
         cliente.setNome(cli.getNome());
         cliente.setCpf(cli.getCpf());
         cliente.setDataCadastro(cli.getDataCadastro());
         return cliente;
    }
    public ClienteDTO findById(Integer id) {
       Cliente cli =  repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
       ClienteDTO dto = new ClienteDTO();
        dto.setId(cli.getId());
        dto.setNome(cli.getNome());
        dto.setCpf(cli.getCpf());
        dto.setDataCadastro(cli.getDataCadastro());
        return dto;

    }

    public List<Cliente> findAll() {

        return repository.findAll();
    }

    public void delete( Integer id) {
        repository
                .findById(id)
                .map( cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }

    public void update( Integer id,  Cliente clienteAtualizado) {
        repository
                .findById(id)
                .map( cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    return repository.save(cliente);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }
}
