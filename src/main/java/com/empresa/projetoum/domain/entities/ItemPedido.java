package com.empresa.projetoum.domain.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //muitos itens pedidos sao de apenas um pedidos
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;


    private int quantidade;

    //muitos itens pedidos tem um produto
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }



    public String toString(){
        return "id do item: " + this.id;
    }
}
