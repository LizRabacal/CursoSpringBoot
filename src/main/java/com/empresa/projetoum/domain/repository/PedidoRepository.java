package com.empresa.projetoum.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.projetoum.domain.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("select p from Pedido p left join fetch p.itens_pedidos where p.id = :id")
    Pedido findPedidoFetchItensPedido(@Param("id") Integer id);

    
}
