package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/cartogames/metodos")
public class ServiceController {

    @Autowired
    private ApiService apiService;
    @Autowired
    private TimeRepository timeRepository;

    @GetMapping("/timeDaData")
    public ResponseEntity<?> timeDaData(
            @RequestParam("data") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {

        List<Time> todosOsTimes = timeRepository.findAll();
        List<String> integrantes = apiService.timeDaData(data, todosOsTimes);

        if (integrantes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("integrantes", integrantes);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/integranteMaisUsado")
    public ResponseEntity<?> integranteMaisUsado(@RequestParam(value = "dataInicial", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @RequestParam(value = "dataFinal", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataFinal){

        List<Time> todosOsTimes = timeRepository.findAll();

        Integrante integranteMaisUsado = apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);

        if(integranteMaisUsado == null){
            return ResponseEntity.notFound().build();
        }
        Map<String, Long> response = new HashMap<>();
        response.put(integranteMaisUsado.getNome(), todosOsTimes.stream().flatMap(time -> time.getComposicaoTime().stream())
                .filter(composicao -> composicao.getIntegrante().equals(integranteMaisUsado)).count());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/timeMaisComum")
    public ResponseEntity<?> timeMaisComum(@RequestParam(value = "dataInicial", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @RequestParam(value = "dataFinal", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataFinal){

        List<Time> todosOsTimes = timeRepository.findAll();
        List<String> jogadoresMaisComum = apiService.timeMaisComum(dataInicial, dataFinal, todosOsTimes);

        if(jogadoresMaisComum.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(jogadoresMaisComum);
    }

    @GetMapping("/funcaoMaisComum")
    public ResponseEntity<?> funcaoMaisComum(@RequestParam(value = "dataInicial", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @RequestParam(value = "dataFinal", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataFinal){

        List<Time> todosOsTimes = timeRepository.findAll();
        String funcaoMaisComum = apiService.funcaoMaisComum(dataInicial, dataFinal, todosOsTimes);

        if(funcaoMaisComum == null){
            return ResponseEntity.notFound().build();
        }
        Map<String, String> response = new HashMap<>();
        response.put("Função", funcaoMaisComum);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/franquiaMaisFamosa")
    public ResponseEntity<?> franquiaMaisFamosa(@RequestParam(value = "dataInicial", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @RequestParam(value = "dataFinal", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataFinal){

        List<Time> todosOsTimes = timeRepository.findAll();
        String franquiaMaisFamosa = apiService.franquiaMaisFamosa(dataInicial, dataFinal, todosOsTimes);
        if(franquiaMaisFamosa == null){
            return ResponseEntity.notFound().build();
        }
        Map<String, String> response = new HashMap<>();
        response.put("Franquia", franquiaMaisFamosa);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/contagemPorFranquia")
    public ResponseEntity contagemPorFranquia(@RequestParam(value = "dataInicial", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @RequestParam(value = "dataFinal", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataFinal){

        List<Time> todosOsTimes = timeRepository.findAll();
        Map<String, Long> contagemPorFranquia = apiService.contagemPorFranquia(dataInicial, dataFinal, todosOsTimes);

        if(contagemPorFranquia.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("Franquia", contagemPorFranquia);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/contagemPorFuncao")
    public ResponseEntity<?> contagemPorFuncao(@RequestParam(value = "dataInicial", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @RequestParam(value = "dataFinal", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataFinal){

        List<Time> todosOsTimes = timeRepository.findAll();
        Map<String, Long> contagemPorFuncao = apiService.contagemPorFuncao(dataInicial, dataFinal, todosOsTimes);

        if(contagemPorFuncao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("Funcao", contagemPorFuncao);

        return ResponseEntity.ok(response);
    }
}
