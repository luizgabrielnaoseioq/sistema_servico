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
@CrossOrigin(origins = "http://localhost:3000")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/")
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

    @PostMapping("/")
    public ResponseEntity<Servico> inserir(@RequestBody Servico servico){
        System.out.println("Recebido: " + servico);
        Servico novoServico = servicoService.inserir(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServico);
    }

    @PutMapping("/")
    public Servico alterar(@RequestBody Servico servico){
        return servicoService.alterar(servico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        servicoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> cancelar(@PathVariable("id") Long id, @RequestBody Servico servicoAtualizado) {
        Servico servico = servicoService.buscarPorId(id);
        if (servico == null) {
            return ResponseEntity.notFound().build();
        }
        servico.setStatus(servicoAtualizado.getStatus()); // Altera apenas o status
        servicoService.salvar(servico); // Salva no banco
        return ResponseEntity.ok(servico);
    }

}