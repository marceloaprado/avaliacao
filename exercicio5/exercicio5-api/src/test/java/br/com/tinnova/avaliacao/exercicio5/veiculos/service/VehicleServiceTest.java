package br.com.tinnova.avaliacao.exercicio5.veiculos.service;

import java.time.LocalDateTime;
import java.util.Arrays;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity.Brand;
import br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity.Vehicle;
import br.com.tinnova.avaliacao.exercicio5.veiculos.db.repository.VehicleRepository;
import br.com.tinnova.avaliacao.exercicio5.veiculos.dto.BrandDTO;
import br.com.tinnova.avaliacao.exercicio5.veiculos.dto.VehicleDTO;
import br.com.tinnova.avaliacao.exercicio5.veiculos.exception.ResourceNotFoundException;
import br.com.tinnova.avaliacao.exercicio5.veiculos.mapper.BrandMapperImpl;
import br.com.tinnova.avaliacao.exercicio5.veiculos.mapper.VehicleMapperImpl;
import br.com.tinnova.avaliacao.exercicio5.veiculos.payload.request.FindVehicleFilterRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { VehicleService.class, VehicleMapperImpl.class, BrandMapperImpl.class })
public class VehicleServiceTest {

	@Autowired
	private VehicleService vehicleService;

	@MockBean
	private VehicleRepository vehicleRepository;

	@MockBean
	private BrandService brandService;

	@TestConfiguration
	static class VehicleServiceTestConfiguration {

		@Bean
		public VehicleService vehicleService() {
			return new VehicleService();
		}
	}

	private Vehicle vehicle;
	private VehicleDTO vehicleDto;
	private Brand brand;
	private BrandDTO brandDto;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);

		brand = Brand.builder().id(1L).brand("Marca").build();		
		brandDto = BrandDTO.builder().id(1L).brand("Marca").build();

		vehicle = Vehicle.builder().vehicle("Veiculo").brand(brand).year(2010).description("Descricao")
				.created(LocalDateTime.now()).build();		
		vehicleDto = VehicleDTO.builder().vehicle("Veiculo").brand(brandDto).year(2010).description("Descricao").build();
	}

	@Test
	public void shouldListAllVehicles() {
		Mockito.when(vehicleRepository.findAll()).thenReturn(Arrays.asList(vehicle));
		Assert.assertFalse(vehicleService.getAllVehicles().isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetVehiclesByFilter() {
		FindVehicleFilterRequest filter = FindVehicleFilterRequest.builder().vehicle("Veiculo").build();
		Mockito.when(vehicleRepository.findAll(Mockito.any(Specification.class))).thenReturn(Arrays.asList(vehicle));
		
		Assert.assertFalse(vehicleService.getVehiclesByFilter(filter).isEmpty());
	}
	
	@Test
	public void shouldGetVehiclesById() {		
		Mockito.when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.of(vehicle));
		
		Assert.assertNotNull(vehicleService.getVehicleById(vehicle.getId()));
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void shouldNotGetVehiclesById_vehicleNotFound() {		
		Mockito.when(vehicleRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		vehicleService.getVehicleById(5L);
	}
	
	@Test
	public void shouldSaveVehicle() {
		Mockito.when(brandService.getBrandById(Mockito.anyLong())).thenReturn(brand);		
		Mockito.when(vehicleRepository.save(Mockito.any())).thenReturn(vehicle);
		
		try {
			vehicleService.saveVehicle(vehicleDto);
		} catch(Exception ex) {
			Assert.fail();
		}
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void shouldNotSaveVehicle_brandNotFound() {
		Mockito.when(brandService.getBrandById(Mockito.anyLong())).thenThrow(new ResourceNotFoundException(""));
		Mockito.when(vehicleRepository.save(Mockito.any())).thenReturn(vehicle);
		
		vehicleService.saveVehicle(vehicleDto);
	}
	
	@Test
	public void shouldUpdateVehicle() {
		Mockito.when(vehicleRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(vehicle));
		Mockito.when(vehicleRepository.save(Mockito.any())).thenReturn(vehicle);
		
		try {
			vehicleService.updateVehicle(vehicleDto, 1L);
		} catch(Exception ex) {
			Assert.fail();
		}
	}

	@Test(expected = ResourceNotFoundException.class)
	public void shouldNotUpdateVehicle_vehicleNotFound() {
		Mockito.when(vehicleRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());		
		
		vehicleService.updateVehicle(vehicleDto, 1L);
	}
	
	@Test
	public void shouldDeleteVehicle() {		
		Mockito.doNothing().when(vehicleRepository).deleteById(vehicle.getId());
		
		try {
			vehicleService.deleteVehicle(vehicle.getId());
		} catch(Exception ex) {
			Assert.fail();
		}
	}
}
