package com.servico.backservico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "servico")
@Data
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String nomeCliente;


    @Temporal(TemporalType.DATE)
    private Date dataInicio = new Date();


    @Temporal(TemporalType.DATE)
    private Date dataTermino;


    private String descricaoServico;


    private Double valorServico;


    private Double valorPago;


    @Temporal(TemporalType.DATE)
    private Double dataPagamento;

    @Column(name = "status")
    private String status; //"pendente, "realizado" ou "cancelado"
}
