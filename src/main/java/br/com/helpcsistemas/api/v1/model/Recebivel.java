package br.com.helpcsistemas.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;

@RedisHash("Recebivel")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recebivel {
        @Id
        private String id;
        private String chaveAcesso;
        private String numeroNotaFiscal;
        private Double valorRecebivel;
        private LocalDate dataVencimento;
        private Double valorAnteciparEditado;
}
