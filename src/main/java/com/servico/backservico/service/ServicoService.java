package com.servico.backservico.service;

import com.servico.backservico.entity.Servico;
import com.servico.backservico.repository.ServicoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Servico inserir(@NotNull Servico servico){
        // Configuração do status com base nos valores
        if (servico.getValorPago() == null || servico.getValorPago() == 0 || servico.getDataPagamento() == null) {
            servico.setStatus("Pendente");
        } else {
            servico.setStatus("Realizado");
        }

        // Persistência no banco
        return servicoRepository.saveAndFlush(servico);
    }

    public Servico alterar(@NotNull Servico servico){
        // Verificando se o serviço existe antes de tentar atualizar
        if (servico.getId() == null || !servicoRepository.existsById(servico.getId())) {
            throw new IllegalArgumentException("Serviço não encontrado para atualização");
        }

        // Atualizando o status de pagamento
        if (servico.getValorPago() != null && servico.getValorPago() > 0 && servico.getDataPagamento() != null) {
            servico.setStatus("Realizado");
        } else {
            servico.setStatus("Pendente");
        }

        // Persistindo as alterações
        return servicoRepository.saveAndFlush(servico);
    }

    public void cancelarServico(Long id) {
        Optional<Servico> servicoOpt = servicoRepository.findById(id);

        if (servicoOpt.isEmpty()) {
            throw new RuntimeException("Serviço não encontrado");
        }

        Servico servico = servicoOpt.get();
        servico.setStatus("cancelado"); // Atualiza o status
        servicoRepository.save(servico); // Salva no banco
    }

    public void excluir(Long id) {
        Optional<Servico> servicoOpt = servicoRepository.findById(id);
        if (servicoOpt.isEmpty()) {
            throw new RuntimeException("Serviço não encontrado para exclusão");
        }
        servicoRepository.delete(servicoOpt.get());
    }

    public Servico buscarPorId(Long id) {
        return servicoRepository.findById(id).orElse(null);
    }

    public Servico salvar(Servico servico) {
        return servicoRepository.save(servico);
    }
}
