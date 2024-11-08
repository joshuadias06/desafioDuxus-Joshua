package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.TimeDTO;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.IntegranteService;
import br.com.duxusdesafio.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartogames/times")
@CrossOrigin("*")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @Autowired
    private IntegranteService integranteService;

    @PostMapping("/cadastro")
    public ResponseEntity<TimeDTO> cadastrarTime(@RequestBody TimeDTO timeDTO) {
        Time novoTime = new Time();

        if (timeDTO.getData() != null) {
            try {
                novoTime.setData(timeDTO.getData());
            } catch (DateTimeParseException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        List<Integrante> integrantes = integranteService.listarIntegrantes()
                .stream()
                .filter(integrante -> timeDTO.getIntegrantes().contains(integrante.getNome()))
                .collect(Collectors.toList());

        if (integrantes.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ComposicaoTime> composicoes = integrantes.stream()
                .map(integrante -> new ComposicaoTime(novoTime, integrante))
                .collect(Collectors.toList());

        novoTime.setComposicaoTime(composicoes);

        Time timeSalvo = timeService.criarTime(novoTime);

        TimeDTO timeDTOResposta = new TimeDTO(timeSalvo.getId(), timeSalvo.getData(),
                timeSalvo.getComposicaoTime().stream()
                        .map(composicao -> composicao.getIntegrante().getNome())
                        .collect(Collectors.toList()));

        return ResponseEntity.ok(timeDTOResposta);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Time>> listarTimes() {
        List<Time> times = timeService.listarTodosTimes();

        if (times.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(times);
    }
}
