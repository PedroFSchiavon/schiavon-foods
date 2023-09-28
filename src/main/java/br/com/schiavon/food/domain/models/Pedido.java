package br.com.schiavon.food.domain.models;

import br.com.schiavon.food.domain.models.enuns.StatusPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCriacao;

    private LocalDateTime dataConfirmacao;

    private LocalDateTime dataCancelamento;

    private LocalDateTime dataEntrega;

    @Enumerated(value = EnumType.STRING)
    private StatusPedido statusPedido;

    @Embedded
    private Endereco enderecoEntrega;

    @ManyToOne()
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne()
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedidos;
}
