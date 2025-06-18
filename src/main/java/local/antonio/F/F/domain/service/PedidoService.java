package local.antonio.F.F.domain.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import local.antonio.domain.model.Pedido;
import local.antonio.domain.model.Produto;
import local.antonio.domain.model.StatusPedido;
import local.antonio.domain.repository.PedidoRepository;
import local.antonio.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PedidoService {
    

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;
    

    public Pedido criar(Pedido pedido) {
        List<Long> idsProdutos = pedido.getProdutos().stream()
                .map(Produto::getId)
                .collect(Collectors.toList());

        List<Produto> produtosCompletos = produtoRepository.findAllById(idsProdutos);

        if (produtosCompletos.size() != idsProdutos.size()) {
            throw new IllegalArgumentException("Alguns produtos não foram encontrados no banco");
        }

        BigDecimal precoTotal = produtosCompletos.stream()
                .map(prod -> BigDecimal.valueOf(prod.getPreco()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setProdutos(produtosCompletos);
        pedido.setPreco(precoTotal);
        pedido.setStatus(StatusPedido.ABERTO);
        pedido.setDataAbertura(LocalDateTime.now());
        

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido finalizarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(StatusPedido.PRONTO);
        pedido.setDataFinalizacao(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

    public Pedido alterarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        // Impede alteração se o pedido já estiver finalizado ou cancelado
        if (pedido.getStatus() == StatusPedido.ENTREGUE || pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Não é possível alterar o status de um pedido finalizado ou cancelado.");
        }

        pedido.setStatus(novoStatus);

        // Define a data de finalização apenas se o novo status for FINALIZADO
        if (novoStatus == StatusPedido.ENTREGUE) {
            pedido.setDataFinalizacao(LocalDateTime.now());
        }

        return pedidoRepository.save(pedido);
    }
}
