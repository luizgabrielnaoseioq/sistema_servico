package com.servico.backservico.controller;

import com.servico.backservico.entity.Servico;
import com.servico.backservico.service.ServicoService;
import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/")
    @CrossOrigin("http://localhost:3003")
    public List<Servico> buscarTodos(){
        return servicoService.buscarTodos();
    }

    @GetMapping("/pagamentoPendente")
    @CrossOrigin("http://localhost:3003")
    public List<Servico> buscarServicosPagamentoPendente(){
        return servicoService.buscarPagamentoPagamentosPendentes();
    }

    @GetMapping("/cancelados")
    @CrossOrigin("http://localhost:3003")
    public List<Servico> buscarServicosPagamentosCancelados(){
        return servicoService.buscarPagamentoPagamentosCancelados();
    }

    @PostMapping("/")
    @CrossOrigin("http://localhost:3003")
    public ResponseEntity<Servico> inserir(@RequestBody Servico servico){
        System.out.println("Recebido: " + servico);
        Servico novoServico = servicoService.inserir(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServico);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3003")
    public Servico alterar(@RequestBody Servico servico){
        return servicoService.alterar(servico);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3003")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        servicoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    @CrossOrigin("http://localhost:3003")
    public ResponseEntity<Void> cancelar(@PathVariable("id") Long id){
        servicoService.calcelarServico(id);
        return ResponseEntity.ok().build();
    }
}