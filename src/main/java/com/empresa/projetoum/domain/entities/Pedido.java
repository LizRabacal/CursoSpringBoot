package com.empresa.projetoum.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // many pedidos tem one cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "total", length = 20, precision = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<ItemPedido> itens_pedidos;

    public List<ItemPedido> getItens_pedidos() {
        return itens_pedidos;
    }

    public void setItens_pedidos(List<ItemPedido> itens_pedidos) {
        this.itens_pedidos = itens_pedidos;
    }

    /*
     * public BigDecimal getTotalPedido() {
     * return itens_pedidos.stream().map(i -> i.getProduto().getPreco().multiply(new
     * BigDecimal(i.getQuantidade())))
     * .reduce(BigDecimal.ZERO, (acc, curr) -> acc.add(curr));
     * }
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String toString() {
        return "Id do pedido: " + this.getId();
    }

}
