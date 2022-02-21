package io.github.juliomotta.clientes.service;

import io.github.juliomotta.clientes.model.entity.Cliente;
import io.github.juliomotta.clientes.model.entity.ServicoPrestado;
import io.github.juliomotta.clientes.model.repository.ClienteRepository;
import io.github.juliomotta.clientes.model.repository.ServicoPrestadoRepository;
import io.github.juliomotta.clientes.rest.dto.ServicoPrestadoDTO;
import io.github.juliomotta.clientes.util.BigDecimalConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class ServicoPrestadoService {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository repository;
    private final BigDecimalConverter bigDecimalConverter;


    public ServicoPrestado save(ServicoPrestadoDTO dto) {
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();

        Cliente cliente =
                clienteRepository
                        .findById(idCliente)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST, "Cliente inexistente."));


        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

        return repository.save(servicoPrestado);
    }

    public List<ServicoPrestado> searchByNameAndMonth(String nome,Integer mes ) {
        return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }
}
