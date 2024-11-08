package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    public List<Time> listarTodosTimes() {
        return timeRepository.findAll();
    }

    public Time criarTime(Time time) {
        return timeRepository.save(time);
    }
}

