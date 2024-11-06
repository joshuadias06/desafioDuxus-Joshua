package br.com.duxusdesafio.dto;

import java.time.LocalDate;
import java.util.List;

public class TimeDTO {

    private LocalDate data;
    private List<Long> integranteIds;


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Long> getIntegranteIds() {
        return integranteIds;
    }

    public void setIntegranteIds(List<Long> integranteIds) {
        this.integranteIds = integranteIds;
    }
}
