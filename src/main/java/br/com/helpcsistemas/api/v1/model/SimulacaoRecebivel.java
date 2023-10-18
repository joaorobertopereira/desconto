package br.com.helpcsistemas.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.List;

@RedisHash("SimulacaoRecebivel")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulacaoRecebivel {
        @Id
        private String id;
        private LocalDateTime data;
        private int qtdRecebiveis;
        @Reference
        private List<Recebivel> recebivel;
}
