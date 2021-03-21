package br.com.tinnova.avaliacao.exercicio5.veiculos.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity.Brand;
import br.com.tinnova.avaliacao.exercicio5.veiculos.db.repository.BrandRepository;
import br.com.tinnova.avaliacao.exercicio5.veiculos.exception.ResourceNotFoundException;
import br.com.tinnova.avaliacao.exercicio5.veiculos.mapper.BrandMapperImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BrandService.class, BrandMapperImpl.class })
public class BrandServiceTest {

	@Autowired
	private BrandService brandService;

	@MockBean
	private BrandRepository brandRepository;

	@TestConfiguration
	static class BrandServiceTestConfiguration {

		@Bean
		public BrandService brandService() {
			return new BrandService();
		}
	}

	private Brand brand;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);

		brand = Brand.builder().id(1L).brand("Marca").build();
	}

	@Test
	public void shouldGetBrandById() {
		Mockito.when(brandRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(brand));

		Assert.assertNotNull(brandService.getBrandById(1L));
	}

	@Test(expected = ResourceNotFoundException.class)
	public void shouldNotGetBrandById_brandNotFound() {
		Mockito.when(brandRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		brandService.getBrandById(1L);
	}

	@Test
	public void shouldGetBrandByFilter() {
		Mockito.when(brandRepository.findByBrandContainingIgnoreCase(Mockito.any(), Mockito.any()))
				.thenReturn(new PageImpl<>(Arrays.asList(brand)));

		Map<String, String> filters = new HashMap<>();
		filters.put("page", "0");
		filters.put("size", "10");
		filters.put("search", "Marca");

		Assert.assertNotNull(brandService.getBrandByFilter(filters));
	}

	@Test
	public void shouldGetBrandByFilter_empty() {
		Mockito.when(brandRepository.findByBrandContainingIgnoreCase(Mockito.any(), Mockito.any()))
				.thenReturn(Page.empty());

		Assert.assertNotNull(brandService.getBrandByFilter(new HashMap<>()));
	}
}
