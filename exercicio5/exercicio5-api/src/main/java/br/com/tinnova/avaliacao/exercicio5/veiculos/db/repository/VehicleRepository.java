package br.com.tinnova.avaliacao.exercicio5.veiculos.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity.Vehicle;

/**
 * Interface de acesso à persistência de dados da entidade Veículo
 * 
 * @author Marcelo Alves Prado
 * 
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {

}
