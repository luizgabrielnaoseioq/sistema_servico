package com.servico.backservico.service;

import com.servico.backservico.entity.Servico;
import com.servico.backservico.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> buscarTodos(){
        return servicoRepository.findAll();
    }

    public List<Servico> buscarPagamentoPagamentosPendentes(){
        return servicoRepository.buscarServicosPagamentoPendente();
    }

    public List<Servico> buscarPagamentoPagamentosCancelados(){
        return servicoRepository.buscarServicosPagamentoCancelados();
    }

    public Servico inserir(Servico servico){
        if (servico.getValorPago()==null || servico.getValorPago()==0 || servico.getDataPagamento()==null){
            servico.setStatus("Pendente");
        } else {
            servico.setStatus("Realizado");
        }

        return servicoRepository.saveAndFlush(servico);
    }

    public Servico alterar(Servico servico){
        if (servico.getValorPago()!= null && servico.getValorPago() > 0 && servico.getDataPagamento() != null){
            servico.setStatus("Realizado");
        } else {

        }
        return servicoRepository.saveAndFlush(servico);
    }

    public void excluir(Long id){
        Servico servico = servicoRepository.findById(id).orElseThrow();
        servicoRepository.delete(servico);
    }
}
