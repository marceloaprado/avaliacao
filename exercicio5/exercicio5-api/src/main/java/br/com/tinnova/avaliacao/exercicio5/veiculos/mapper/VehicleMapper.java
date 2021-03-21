package br.com.tinnova.avaliacao.exercicio5.veiculos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity.Vehicle;
import br.com.tinnova.avaliacao.exercicio5.veiculos.dto.VehicleDTO;

@Mapper(componentModel = "spring", uses = BrandMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class VehicleMapper {
	public abstract Vehicle dtoToEntity(VehicleDTO dto);

	public abstract VehicleDTO entityToDto(Vehicle entity);	
}
