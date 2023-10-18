package br.com.helpcsistemas.api.v1.controller;

import br.com.helpcsistemas.api.v1.model.Recebivel;
import br.com.helpcsistemas.api.v1.service.RecebivelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecebivelController {

    private final RecebivelService recebivelService;

    @PostMapping("/processarRecebivel")
    public ResponseEntity<String> processarRecebivel(@RequestBody List<Recebivel> recebiveis) {
        String simulacaoId = recebivelService.processarRecebiveis(recebiveis);

        if (simulacaoId != null) {
            return new ResponseEntity<>("Processamento bem-sucedido. ID da simulação: " + simulacaoId, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Erro no processamento", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
