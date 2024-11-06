package br.com.duxusdesafio.service;


import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegranteService {

    @Autowired
    private IntegranteRepository integranteRepository;

    public Integrante criarIntegrante(Integrante integrante){
        return integranteRepository.save(integrante);
    }

    public List<Integrante> listarIntegrantes(){
        return integranteRepository.findAll();
    }
}
