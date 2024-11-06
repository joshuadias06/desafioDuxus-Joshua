package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    private TimeRepository timeRepository;

    public Time criarTime(Time time){
        return timeRepository.save(time);
    }
}
