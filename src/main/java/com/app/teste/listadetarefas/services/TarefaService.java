package com.app.teste.listadetarefas.services;

import com.app.teste.listadetarefas.model.Tarefa;
import com.app.teste.listadetarefas.repository.TarefaRepository;
import org.springframework.stereotype.Service;
import com.app.teste.listadetarefas.model.Priority;
import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private TarefaRepository tarefaRepository;
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }


    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> getTarefaById( Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa createTarefa(Tarefa novaTarefa) {
        return tarefaRepository.save(novaTarefa);
    }

    public Tarefa updateTarefa(Long id, Tarefa tarefaUpdate) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setTarefa(tarefaUpdate.getTarefa());
                    tarefa.setDescricao(tarefaUpdate.getDescricao());
                    tarefa.setCompleta(tarefaUpdate.getCompleta());
                    tarefa.setData(tarefaUpdate.getData());
                    tarefa.setPrioridade(tarefaUpdate.getPrioridade());
                    return tarefaRepository.save(tarefaUpdate);
                })
                .orElseGet(() ->{
                    tarefaUpdate.setId(id);
                    return tarefaRepository.save(tarefaUpdate);
                });
    }

    public void deleteTarefa(Long id) {
       tarefaRepository.deleteById(id);
    }

    public Optional<Tarefa> marcarTarefaCompleta(Long id) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                   tarefa.setCompleta(true);
                   return tarefaRepository.save(tarefa);
                });
    }

    public List<Tarefa> getTarefaByCategoria(String categoria) {
        return tarefaRepository.findByCategoria(categoria);
    }

    public List<Tarefa> getTarefaOdenadaByOrderByCategoriaDesc() {
       return tarefaRepository.findAllByOrderByCategoriaDesc();
    }

    public List<Tarefa> filtrarTarefas(String categoria, Priority prioridade, Boolean completa, LocalDate startDate, LocalDate endDate) {
        return  tarefaRepository.procurarTarefaPorCriterios(categoria, prioridade, completa, startDate, endDate);
    }

    public List<Tarefa> tarefaNaoCompletaOrdenadaPorData() {
       return tarefaRepository.findByCompletaFalseOrderByDataAscPrioridadeDesc();
    }
    public List<Tarefa> getAllTarefasCompletas(boolean completa) {
        return tarefaRepository.findBycompleta(completa);
    }


}
