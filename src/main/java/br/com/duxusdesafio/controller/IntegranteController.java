package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.service.IntegranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartogames/integrantes")
public class IntegranteController {

    @Autowired
    private IntegranteService integranteService;

    @PostMapping("/cadastro")
    public ResponseEntity<Integrante> cadastroIntegrante(@RequestBody Integrante integrante) {
        Integrante novoIntegrante = integranteService.criarIntegrante(integrante);
        return new ResponseEntity<>(novoIntegrante, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public List<Integrante> listarIntegrantes() {
        return integranteService.listarIntegrantes();
    }
}
