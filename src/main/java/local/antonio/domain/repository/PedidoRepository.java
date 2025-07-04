/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package local.antonio.domain.repository;

import java.util.List;
import local.antonio.domain.model.Pedido;
import local.antonio.domain.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Junior
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    
    List<Pedido> findByStatus(StatusPedido status);
}