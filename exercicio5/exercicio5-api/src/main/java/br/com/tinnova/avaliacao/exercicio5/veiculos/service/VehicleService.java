package br.com.tinnova.avaliacao.exercicio5.veiculos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity.Brand;
import br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity.Vehicle;
import br.com.tinnova.avaliacao.exercicio5.veiculos.db.repository.VehicleRepository;
import br.com.tinnova.avaliacao.exercicio5.veiculos.db.specification.VehicleSpec;
import br.com.tinnova.avaliacao.exercicio5.veiculos.dto.VehicleDTO;
import br.com.tinnova.avaliacao.exercicio5.veiculos.exception.ResourceNotFoundException;
import br.com.tinnova.avaliacao.exercicio5.veiculos.mapper.VehicleMapper;
import br.com.tinnova.avaliacao.exercicio5.veiculos.payload.request.FindVehicleFilterRequest;

/**
 * Serviço contendo as funcionalidades e regras de negócio relacionadas a Veículos
 * 
 * @author Marcelo Alves Prado
 * 
 */
@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private VehicleMapper vehicleMapper;

	@Autowired
	private BrandService brandService;

	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.findAll().stream().collect(Collectors.toList());
	}

	public List<Vehicle> getVehiclesByFilter(FindVehicleFilterRequest filter) {
		VehicleSpec vehicleSpec = new VehicleSpec(filter);
		return vehicleRepository.findAll(vehicleSpec).stream().collect(Collectors.toList());
	}

	public Vehicle getVehicleById(Long id) {
		return vehicleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado o veículo com id " + id));
	}

	@Transactional
	public void saveVehicle(VehicleDTO vehicleDTO) {
		Vehicle vehicle = vehicleMapper.dtoToEntity(vehicleDTO);

		Brand brand = brandService.getBrandById(vehicleDTO.getBrand().getId());
		vehicle.setBrand(brand);
		
		vehicleRepository.save(vehicle);
	}

	@Transactional
	public void updateVehicle(VehicleDTO vehicleDTO, Long id) {
		Vehicle vehicle = getVehicleById(id);

		if (StringUtils.isNotBlank(vehicleDTO.getVehicle()))
			vehicle.setVehicle(vehicleDTO.getVehicle());

		if (vehicleDTO.getBrand() != null && vehicleDTO.getBrand().getId() != null) {
			Brand brand = brandService.getBrandById(vehicleDTO.getBrand().getId());
			vehicle.setBrand(brand);
		}

		if (vehicleDTO.getYear() != null)
			vehicle.setYear(vehicleDTO.getYear());

		if (StringUtils.isNotBlank(vehicleDTO.getDescription()))
			vehicle.setDescription(vehicleDTO.getDescription());

		if (vehicleDTO.getIsSold() != null)
			vehicle.setIsSold(vehicleDTO.getIsSold());

		vehicleRepository.save(vehicle);
	}

	@Modifying
	@Transactional
	public void deleteVehicle(Long id) {
		vehicleRepository.deleteById(id);
	}
}
