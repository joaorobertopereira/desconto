package br.com.helpcsistemas.api.v1.repository;


import br.com.helpcsistemas.api.v1.model.SimulacaoRecebivel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulacaoRecebivelRepository extends CrudRepository<SimulacaoRecebivel, String> {
}

