package com.servico.backservico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "servico")
@EqualsAndHashCode(of = "id")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio = new Date();

    @Column(name = "data_termino")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;

    @Column(name = "descricao_servico")
    private String descricaoServico;

    @Column(name = "valor_servico")
    private Double valorServico;

    @Column(name = "valor_pago")
    private Double valorPago;

    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    @Column(name = "status")
    private String status; //"pendente, "realizado" ou "cancelado"
}
