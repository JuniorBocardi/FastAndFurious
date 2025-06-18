package local.antonio.F.F.controller;

import jakarta.validation.Valid;
import java.util.List;
import local.antonio.F.F.DTO.StatusPedidoDTO;
import local.antonio.F.F.domain.service.PedidoService;
import local.antonio.domain.model.Pedido;
import local.antonio.domain.model.StatusPedido;
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

    @GetMapping("/status/{status}")
    public List<Pedido> findByStatus(@PathVariable StatusPedido status) {
        List<Pedido> pedidos = pedidoService.findByStatus(status);
        if (pedidos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existem pedidos nesse status");

        }
        return pedidos;

    }

    @PutMapping("/{id}")
    public Pedido atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedidoAtualizado) {
        return pedidoService.atualizarPedido(id, pedidoAtualizado);
    }

    @PutMapping("/status/{id}")
    public Pedido atualizarStatus(@PathVariable Long id, @RequestBody StatusPedidoDTO dto) {
        return pedidoService.alterarStatus(id, dto.getStatus());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criar(@RequestBody Pedido pedido) {
        return pedidoService.criar(pedido);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        pedidoService.deletar(id);
    }
}