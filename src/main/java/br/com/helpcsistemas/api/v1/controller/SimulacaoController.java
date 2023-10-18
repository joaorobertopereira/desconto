package br.com.helpcsistemas.api.v1.controller;

import br.com.helpcsistemas.api.v1.model.SimulacaoRecebivel;
import br.com.helpcsistemas.api.v1.repository.SimulacaoRecebivelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/simulacoes")
@RequiredArgsConstructor
public class SimulacaoController {
    private final SimulacaoRecebivelRepository simulacaoRecebivelRepository;

    @GetMapping
    public ResponseEntity<Iterable<SimulacaoRecebivel>> listarSimulacoesSemRecebiveis() {
        Iterable<SimulacaoRecebivel> simulacoes = simulacaoRecebivelRepository.findAll();

        for (SimulacaoRecebivel simulacao : simulacoes) {
            simulacao.setRecebivel(null);
        }

        return ResponseEntity.ok(simulacoes);
    }

    @GetMapping("/listaRecebiveis")
    public ResponseEntity<Iterable<SimulacaoRecebivel>> listarSimulacoesComRecebiveis() {
        Iterable<SimulacaoRecebivel> simulacoes = simulacaoRecebivelRepository.findAll();
        return ResponseEntity.ok(simulacoes);
    }

    @GetMapping("/{id}")
    public Optional<SimulacaoRecebivel> consultarSimulacaoComRecebiveis(@RequestParam String id) {
        return simulacaoRecebivelRepository.findById(id);
    }

}