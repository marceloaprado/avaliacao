package br.com.tinnova.avaliacao.exercicio5.veiculos.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity.Brand;
import br.com.tinnova.avaliacao.exercicio5.veiculos.dto.BrandDTO;
import br.com.tinnova.avaliacao.exercicio5.veiculos.mapper.BrandMapper;
import br.com.tinnova.avaliacao.exercicio5.veiculos.payload.response.PagedResponse;
import br.com.tinnova.avaliacao.exercicio5.veiculos.service.BrandService;
import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author Marcelo Alves Prado
 * 
 */
@Log4j2
@Validated
@RestController
@RequestMapping("/marcas")
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private BrandMapper brandMapper;
	
	@GetMapping
	public ResponseEntity<?> getAllBrands(@RequestParam(required = false) Map<String, String> filters) {		
		log.info("Operation getAllBrands: search {}", filters.get("search"));
		
		Page<Brand> brandPages = this.brandService.getBrandByFilter(filters);
		
		List<BrandDTO> brandsDTO = brandPages.getContent().stream().map(brandMapper::entityToDto)
				.collect(Collectors.toList());

		PagedResponse<BrandDTO> pagedResponse = new PagedResponse<>(brandsDTO, brandPages.getNumber(),
				brandPages.getSize(), brandPages.getTotalElements(), brandPages.getTotalPages(), brandPages.isLast());
		
		return ResponseEntity.ok(pagedResponse);
	}
}
