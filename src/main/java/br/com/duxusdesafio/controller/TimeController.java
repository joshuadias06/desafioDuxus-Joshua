package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.TimeDTO;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.IntegranteService;
import br.com.duxusdesafio.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartogames/times")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @Autowired
    private IntegranteService integranteService;

    @PostMapping("/cadastro")
    public ResponseEntity<Time> cadastrarTime(@RequestBody TimeDTO timeDTO) {
        List<Integrante> integrantes = integranteService.listarIntegrantes()
                .stream()
                .filter(integrante -> timeDTO.getIntegranteIds().contains(integrante.getId()))
                .collect(Collectors.toList());

        Time novoTime = new Time();
        novoTime.setData(timeDTO.getData());
        novoTime.setComposicaoTime(integrantes.stream()
                .map(integrante -> new ComposicaoTime(novoTime, integrante))
                .collect(Collectors.toList()));

        Time timeSalvo = timeService.criarTime(novoTime);
        return ResponseEntity.ok(timeSalvo);
    }
}