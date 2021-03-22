package br.com.tinnova.avaliacao.exercicio5.veiculos.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tinnova.avaliacao.exercicio5.veiculos.dto.VehicleDTO;
import br.com.tinnova.avaliacao.exercicio5.veiculos.mapper.VehicleMapper;
import br.com.tinnova.avaliacao.exercicio5.veiculos.payload.request.FindVehicleFilterRequest;
import br.com.tinnova.avaliacao.exercicio5.veiculos.service.VehicleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;

/**
 * Controller responsável pelas operações de CRUD relacionadas a Veículos
 * 
 * @author Marcelo Alves Prado
 * 
 */
@Log4j2
@Validated
@RestController
@RequestMapping("/veiculos")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private VehicleMapper vehicleMapper;

	@ApiOperation(value = "Retorna todos os veículos")
	@GetMapping
	public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
		log.info("Operation getAllVehicles");

		return ResponseEntity.ok(
				vehicleService.getAllVehicles().stream().map(vehicleMapper::entityToDto).collect(Collectors.toList()));
	}

	@ApiOperation(value = "Retorna os veículos de acordo com o termo passado por parâmetro")
	@GetMapping("/find")
	public ResponseEntity<List<VehicleDTO>> getVehiclesByFilter(FindVehicleFilterRequest filter) {
		log.info(
				"Operation getVehiclesByFilter: vehicle {}, brand {}, year {}, description {}, is sold {}, startCreatedInterval {}, endCreatedInterval {}",
				filter.getVehicle(), filter.getBrand(), filter.getYear(), filter.getDescription(), filter.getIsSold(),
				filter.getStartCreatedInterval(), filter.getEndCreatedInterval());

		return ResponseEntity.ok(vehicleService.getVehiclesByFilter(filter).stream().map(vehicleMapper::entityToDto)
				.collect(Collectors.toList()));
	}

	@ApiOperation(value = "Retorna os detalhes do veículo")
	@GetMapping("/{id}")
	public ResponseEntity<VehicleDTO> getVehicleById(@ApiParam("Id do veículo") @PathVariable("id") Long id) {
		log.info("Operation getVehicleById: id {}", id);

		return ResponseEntity.ok(vehicleMapper.entityToDto(vehicleService.getVehicleById(id)));
	}

	@ApiOperation(value = "Adiciona um novo veículo")
	@PostMapping
	public ResponseEntity<?> saveVehicle(@ApiParam("Dados do veículo") @Valid @RequestBody VehicleDTO vehicleDTO) {
		log.info("Operation saveVehicle: vehicle {}", vehicleDTO);

		vehicleService.saveVehicle(vehicleDTO);

		return ResponseEntity.created(URI.create("/veiculos")).build();
	}

	@ApiOperation(value = "Atualiza os dados de um veículo")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateVehicle(@ApiParam("Dados do veículo") @Valid @RequestBody VehicleDTO vehicleDTO,
			@ApiParam("Id do veículo") @PathVariable("id") Long id) {
		log.info("Operation updateVehicle: vehicle {}, id {}", vehicleDTO, id);

		vehicleService.updateVehicle(vehicleDTO, id);

		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Atualiza os dados do veículo conforme os campos informados")
	@PatchMapping("/{id}")
	public ResponseEntity<?> updatePartialVehicle(@ApiParam("Dados do veículo") @RequestBody VehicleDTO vehicleDTO,
			@ApiParam("Id do veículo") @PathVariable("id") Long id) {
		log.info("Operation updatePartialVehicle: vehicle data {}, id {}", vehicleDTO, id);

		vehicleService.updateVehicle(vehicleDTO, id);

		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Apaga um veículo")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVehicle(@ApiParam("Id do veículo") @PathVariable("id") Long id) {
		log.info("Operation deleteVehicle: id {}", id);

		vehicleService.deleteVehicle(id);

		return ResponseEntity.noContent().build();
	}
}
