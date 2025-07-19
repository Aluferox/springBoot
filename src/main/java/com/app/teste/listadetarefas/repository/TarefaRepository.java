package com.app.teste.listadetarefas.repository;

import com.app.teste.listadetarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.teste.listadetarefas.model.Priority;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT t FROM Tarefa t WHERE"+
    "(:categoria IS NULL OR t.categoria = :categoria) AND" +
    "(:prioridade IS NULL OR t.prioridade = :prioridade) AND" +
    "(:completa IS NULL OR t.completa = :completa) AND" +
    "(:startDate IS NULL OR t.data >= :startDate) AND " + // Adicionado filtro de data inicial
    "(:endDate IS NULL OR t.data <= :endDate) " + // Adicionado filtro de data final
    "ORDER BY t.data ASC")


    List<Tarefa>procurarTarefaPorCriterios(
            @Param("categoria") String categoria,
            @Param("prioridade")Priority prioridade,
            @Param("completa") Boolean completa,
            @Param("startDate") LocalDate startDate, // Novo parâmetro
            @Param("endDate") LocalDate endDate   // Novo parâmetro
            );

    // você também pode definir métodos personalizados aqui que o spring data JPA
    // implementará automaticamente com base no nome do método(Query Methods)
    List<Tarefa> findBycompleta(boolean completa);
    List<Tarefa> findByCategoria(String categoria);
    List<Tarefa> findAllByOrderByPrioridadeAsc();
    List<Tarefa> findAllByOrderByCategoriaDesc();
    List<Tarefa> findByCompletaFalseOrderByDataAscPrioridadeDesc();

}
