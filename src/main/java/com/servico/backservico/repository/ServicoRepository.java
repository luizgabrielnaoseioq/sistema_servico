package com.servico.backservico.repository;

import com.servico.backservico.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query("select s from Servico s where s.valorPago > 0")
    List<Servico> buscarServicosPagamentoPendente();

    @Query("select s from servico s where s.status = 'cancelado'")
    List<Servico> buscarServicosPagamentoCancelados();
}
