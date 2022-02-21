package io.github.juliomotta.clientes.service;

import io.github.juliomotta.clientes.exception.UsuarioCadastradoException;
import io.github.juliomotta.clientes.model.entity.Usuario;
import io.github.juliomotta.clientes.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario){
        boolean exists = repository.existsByUsername(usuario.getUsername());
        if(exists){
            throw new UsuarioCadastradoException(usuario.getUsername());
        }
        return repository.save(usuario);
    }


    public Usuario login( String username, String password ) throws EntityNotFoundException {
        Usuario usuario = repository
                            .findByUsernameAndPassword(username, password)
                            .orElseThrow(() -> new EntityNotFoundException("Login não encontrado.") );

        return usuario;
    }
}
