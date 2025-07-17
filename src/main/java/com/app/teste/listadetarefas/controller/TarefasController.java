package com.app.teste.listadetarefas.controller;
import com.app.teste.listadetarefas.model.Tarefa;
import com.app.teste.listadetarefas.services.TarefaService;
import com.app.teste.listadetarefas.model.Priority;
import java.time.LocalDate;

import org.springframework.http.HttpStatus; //para retornar códigos de STATUS HTTP;
import org.springframework.http.ResponseEntity; // Para construir respostas HTTP mais flexivéis;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Marca Esta classe como um controlador REST
@RequestMapping("/api/tarefas") // Define o caminho base para todos os endpoints nesta classe
public class TarefasController {
    private TarefaService tarefaService;

    public TarefasController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    // --- Recupera todas as Tarefas ---
    @GetMapping
    public List<Tarefa> getAllTarefas() {
        return tarefaService.getAllTarefas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable Long id) {
        return tarefaService.getTarefaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa createTarefa(@RequestBody Tarefa novaTarefa) {
        return tarefaService.createTarefa(novaTarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaUpdate) {
        return ResponseEntity.ok(tarefaService.updateTarefa(id, tarefaUpdate));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTarefa(@PathVariable Long id) {
        tarefaService.deleteTarefa(id);
    }

    @PatchMapping("/{id}/completa")
    public ResponseEntity<Tarefa> marcarTarefaCompleta(@PathVariable Long id) {
        return tarefaService.marcarTarefaCompleta(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{nomeCategoria}")
    public List<Tarefa> getTarefaByCategoria(@PathVariable String nomeCategoria) {
        return tarefaService.getTarefaByCategoria(nomeCategoria);
    }

    @GetMapping("/prioridades")
    public List<Tarefa> getTarefaOdenadaByPrioridade() {
        return tarefaService.getTarefaOdenadaByOrderByCategoriaDesc();
    }

    @GetMapping("/filtrar")
    public List filtrarTarefa(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Priority prioridade,
            @RequestParam(required = false) Boolean completa,
            @RequestParam(required = false) LocalDate startDate, // Novo parâmetro
            @RequestParam(required = false) LocalDate endDate) {   // Novo parâmetro
        {
        return tarefaService.filtrarTarefas(categoria,prioridade,completa, startDate, endDate);
        }
    }

    @GetMapping("/naoCompletas")
    public List<Tarefa> getTarefaOdenadaByNaoCompleta() {
        return tarefaService.tarefaNaoCompletaOrdenadaPorData();
    }
}

