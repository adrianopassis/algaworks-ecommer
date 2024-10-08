package com.algaworks.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public enum StatusPagamento {

    PROCESSANDO,
    CANCELADO,
    RECEBIDO;

    @Getter
    @Setter
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @Entity

    public static class ItenPedido {
        @EqualsAndHashCode.Include
        @Id
        private Integer id;

        private Integer pedidoId;

        private Integer produtoId;

        private BigDecimal precoProduto;

        private Integer quantidade;

    }
}
