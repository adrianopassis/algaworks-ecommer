package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_pedido")
public class ItenPedido {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pedido_id")
    private  Integer pedidoId;

    @Column(name = "produto_id")
    private Integer produtoId;

    @Column(name = "preco_produto")
    private BigDecimal precoProduto;

    private  Integer quantidade;
}