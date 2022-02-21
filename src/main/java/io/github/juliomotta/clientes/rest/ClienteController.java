package io.github.juliomotta.clientes.rest;

import io.github.juliomotta.clientes.model.entity.Cliente;
import io.github.juliomotta.clientes.rest.dto.ClienteDTO;
import io.github.juliomotta.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO save( @RequestBody @Valid ClienteDTO cliente ){
        return service.save(cliente);
    }

    @GetMapping
    public List<Cliente> findAll(){

        return service.findAll();
    }





    @GetMapping("{id}")
    public ClienteDTO findById( @PathVariable Integer id ){
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){
        service.delete(id);
    }



    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id,
                           @RequestBody @Valid Cliente clienteAtualizado ) {
        service.update(id, clienteAtualizado);
    }


}
