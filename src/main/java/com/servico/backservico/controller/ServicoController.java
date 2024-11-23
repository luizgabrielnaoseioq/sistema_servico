package com.servico.backservico.controller;

import com.servico.backservico.entity.Servico;
import com.servico.backservico.service.ServicoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping(value = "/buscarTodos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Servico> buscarTodos(){
        return servicoService.buscarTodos();
    }

    @GetMapping(value = "/pagamentosPendentes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Servico> buscarServicosPagamentoPendente(){
        return servicoService.buscarPagamentoPagamentosPendentes();
    }

    @GetMapping(value = "/pagamentosCancelados", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Servico> buscarServicosPagamentosCancelados(){
        return servicoService.buscarPagamentoPagamentosCancelados();
    }

    //metodo para criar um produto
    @PostMapping(value = "/inserirNovoServico", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Servico inserir(@RequestBody Servico servico){
        return servicoService.inserir(servico);
    }

    @PutMapping(value = "/editarServico", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Servico alterar(@RequestBody Servico servico){
        return servicoService.alterar(servico);
    }

    @DeleteMapping(value = "/deletarServico/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> excluir(@PathParam("id") Long id){
        servicoService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
