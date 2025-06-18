package local.antonio.F.F.controller;

import jakarta.validation.Valid;
import java.util.List;
import local.antonio.F.F.DTO.StatusPedidoDTO;
import local.antonio.F.F.domain.service.PedidoService;
import local.antonio.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    

    @Autowired
    private PedidoService pedidoService;
    

    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.findAll();
    }

    @GetMapping("/{id}")
    public Pedido findById(@PathVariable Long id) {
        return pedidoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PutMapping("/status/{id}")
    public Pedido atualizarStatus(@PathVariable Long id, @RequestBody StatusPedidoDTO dto) {
        return pedidoService.alterarStatus(id, dto.getStatus());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criar(@RequestBody Pedido pedido) {