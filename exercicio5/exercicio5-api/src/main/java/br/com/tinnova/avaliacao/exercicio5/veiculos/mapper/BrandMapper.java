package br.com.tinnova.avaliacao.exercicio5.veiculos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity.Brand;
import br.com.tinnova.avaliacao.exercicio5.veiculos.dto.BrandDTO;

/**
 * Classe responsável pelo mapeamento de entidades para dto's relacionado a Marca
 * 
 * @author Marcelo Alves Prado
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class BrandMapper {
	public abstract Brand dtoToEntity(BrandDTO dto);

	public abstract BrandDTO entityToDto(Brand entity);
}
