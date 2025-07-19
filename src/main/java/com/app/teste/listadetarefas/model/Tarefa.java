package com.app.teste.listadetarefas.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //Marca esta classe como uma entidade JPA, maopeando-a para uma tabela no DB
public class Tarefa {

    @Id // marca esta campo como sendo chave primária da tabela;
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura a geração automática de ID (auto-incremento)
    private Long id;
    private String tarefa;
    private String descricao;
    private Boolean completa;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private Priority prioridade;
    private String categoria;

}


