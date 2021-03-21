package br.com.tinnova.avaliacao.exercicio5.veiculos.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity.Brand;
import br.com.tinnova.avaliacao.exercicio5.veiculos.db.repository.BrandRepository;
import br.com.tinnova.avaliacao.exercicio5.veiculos.exception.ResourceNotFoundException;
import br.com.tinnova.avaliacao.exercicio5.veiculos.util.AppConstants;

@Service
public class BrandService {
	
	private final String SORT_COLUMN = "brand";

	@Autowired
	private BrandRepository brandRepository;

	public Brand getBrandById(Long id) {
		return brandRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrada a marca com id " + id));
	}
	
	public Page<Brand> getBrandByFilter(Map<String, String> filters) {
		int page = Integer
				.parseInt(filters.get("page") != null ? filters.get("page") : AppConstants.DEFAULT_PAGE_NUMBER);
		int size = Integer.parseInt(filters.get("size") != null ? filters.get("size") : AppConstants.DEFAULT_DROPDOWN_SIZE);
		String search = filters.get("search") != null ? filters.get("search") : "";
		
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, SORT_COLUMN);
		
		return this.brandRepository.findByBrandContainingIgnoreCase(search, pageable);
	}
}
