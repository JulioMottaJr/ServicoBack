package io.github.juliomotta.clientes.rest;

import io.github.juliomotta.clientes.model.entity.ServicoPrestado;
import io.github.juliomotta.clientes.rest.dto.ServicoPrestadoDTO;
import io.github.juliomotta.clientes.service.ServicoPrestadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController  {

    private final ServicoPrestadoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado save(@RequestBody @Valid ServicoPrestadoDTO dto ){
        return service.save(dto);
    }



    @GetMapping
    public List<ServicoPrestado> search(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ) {
        return service.searchByNameAndMonth("%" + nome + "%", mes);
    }
}
