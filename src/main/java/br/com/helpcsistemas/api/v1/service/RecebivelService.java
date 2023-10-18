package br.com.helpcsistemas.api.v1.service;

import br.com.helpcsistemas.api.v1.model.Recebivel;
import br.com.helpcsistemas.api.v1.model.SimulacaoRecebivel;
import br.com.helpcsistemas.api.v1.repository.SimulacaoRecebivelRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecebivelService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final SimulacaoRecebivelRepository simulacaoRecebivelRepository;

    public String processarRecebiveis(List<Recebivel> recebiveis) {

        SimulacaoRecebivel simulacaoRecebivel = new SimulacaoRecebivel();
        simulacaoRecebivel.setData(LocalDateTime.now());
        simulacaoRecebivel.setQtdRecebiveis(recebiveis.size());
        simulacaoRecebivel.setRecebivel(recebiveis);
        simulacaoRecebivelRepository.save(simulacaoRecebivel);

        // Retorne o ID da simulação
        return simulacaoRecebivel.getId();
    }
}
