package com.servico.backservico.controller;

import com.servico.backservico.entity.Servico;
import com.servico.backservico.service.ServicoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping("/api/servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/find_all")
    public List<Servico> buscarTodos(){
        return servicoService.buscarTodos();
    }

    @GetMapping("/pagamentoPendente")
    public List<Servico> buscarServicosPagamentoPendente(){
        return servicoService.buscarPagamentoPagamentosPendentes();
    }

    @GetMapping("/cancelados")
    public List<Servico> buscarServicosPagamentosCancelados(){
        return servicoService.buscarPagamentoPagamentosCancelados();
    }

    @PostMapping("/inserirservico")
    public Servico inserir(@RequestBody Servico servico){
        return servicoService.inserir(servico);
    }

    @PutMapping("/alterarservico")
    public Servico alterar(@RequestBody Servico servico){
        return servicoService.alterar(servico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathParam("id") Long id){
        servicoService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
