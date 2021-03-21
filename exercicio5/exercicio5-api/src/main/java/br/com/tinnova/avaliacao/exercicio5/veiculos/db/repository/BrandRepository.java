package br.com.tinnova.avaliacao.exercicio5.veiculos.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, PagingAndSortingRepository<Brand, Long> {
	Page<Brand> findByBrandContainingIgnoreCase(String search, Pageable pageable);
}
