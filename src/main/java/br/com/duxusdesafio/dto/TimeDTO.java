package br.com.duxusdesafio.dto;

import java.time.LocalDate;
import java.util.List;

public class TimeDTO {

    private long id;  // ID do Time
    private LocalDate data;  // Data do Time
    private List<String> integrantes;  // Lista de nomes dos integrantes

    public TimeDTO(long id, LocalDate data, List<String> integrantes) {
        this.id = id;
        this.data = data;
        this.integrantes = integrantes;
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<String> integrantes) {
        this.integrantes = integrantes;
    }
}
