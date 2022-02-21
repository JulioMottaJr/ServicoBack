package io.github.juliomotta.clientes.rest;


import io.github.juliomotta.clientes.exception.UsuarioCadastradoException;
import io.github.juliomotta.clientes.model.entity.Usuario;
import io.github.juliomotta.clientes.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Usuario usuario){
        try{
            service.salvar(usuario);
        }catch (UsuarioCadastradoException e){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, e.getMessage() );
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Usuario login(@RequestParam String username, @RequestParam String password){
        return service.login(username, password);
    }
}
