package br.com.schiavon.food.domain.models;

import br.com.schiavon.food.domain.exceptions.NegocioException;
import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import br.com.schiavon.food.domain.models.enuns.StatusPedido;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataConfirmacao;

    private OffsetDateTime dataCancelamento;

    private OffsetDateTime dataEntrega;

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

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedidos;

    public void verificaECadastraFormaDePagamento(FormaPagamento formaPagamento) {
        Long id = formaPagamento.getId();

        Optional<FormaPagamento> formaPagamentoOptional = restaurante.getFormaPagamento()
                .stream()
                .filter(pagamento -> id.equals(pagamento.getId()))
                .findAny();

        if (formaPagamentoOptional.isPresent()){
            this.formaPagamento = formaPagamento;
        }else {
            throw new RelacionamentoEntidadeNaoEncontradoException(
                    String.format("A forma de pagamento %s não é permitida no restaurante %s",
                            formaPagamento.getDescricao(), this.restaurante.getNome()));
        }
    }

    public void calculaPreco(){
        this.valorTotal = BigDecimal.ZERO;
        itensPedidos.forEach(item -> this.valorTotal = this.valorTotal.add(item.getPrecoTotal()));
        this.valorTotal = this.valorTotal.add(taxaFrete);
    }

    public void confirma(){
        setStatusPedido(StatusPedido.CONFIRMADO);
        setDataConfirmacao(OffsetDateTime.now());
    }

    public void entregue(){
        setStatusPedido(StatusPedido.ENTREGUE);
        setDataEntrega(OffsetDateTime.now());
    }

    public void cancelado(){
        setStatusPedido(StatusPedido.CANCELADO);
        setDataCancelamento(OffsetDateTime.now());
    }

    public void criado(){
        this.statusPedido = StatusPedido.CRIADO;
    }

    private void setStatusPedido(StatusPedido statusPedido){
        if (!this.statusPedido.verificaAlteracaoStatus(statusPedido)){
            throw new NegocioException(String.format("O pedido de id %s esta com o status \"%s\", não sendo " +
                    "possível alterar para o status \"%s\".", this.codigo, this.statusPedido, statusPedido));
        }

        this.statusPedido = statusPedido;
    }

    @PrePersist
    private void geraCodigo(){
        setCodigo(UUID.randomUUID().toString());
    }
}
