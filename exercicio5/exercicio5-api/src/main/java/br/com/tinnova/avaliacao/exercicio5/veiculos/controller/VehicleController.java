package br.com.tinnova.avaliacao.exercicio5.veiculos.controller;

import java.net.URI;

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
import lombok.extern.log4j.Log4j2;

/**
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

	@GetMapping
	public ResponseEntity<?> getAllVehicles() {
		log.info("Operation getAllVehicles");

		return ResponseEntity.ok(vehicleService.getAllVehicles().stream().map(vehicleMapper::entityToDto));
	}

	@GetMapping("/find")
	public ResponseEntity<?> getVehiclesByFilter(FindVehicleFilterRequest filter) {
		log.info(
				"Operation getVehiclesByFilter: vehicle {}, brand {}, year {}, description {}, is sold {}, startCreatedInterval {}, endCreatedInterval {}",
				filter.getVehicle(), filter.getBrand(), filter.getYear(), filter.getDescription(), filter.getIsSold(),
				filter.getStartCreatedInterval(), filter.getEndCreatedInterval());

		return ResponseEntity.ok(vehicleService.getVehiclesByFilter(filter).stream().map(vehicleMapper::entityToDto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getVehicleById(@PathVariable("id") Long id) {
		log.info("Operation getVehicleById: id {}", id);

		return ResponseEntity.ok(vehicleMapper.entityToDto(vehicleService.getVehicleById(id)));
	}

	@PostMapping
	public ResponseEntity<?> saveVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
		log.info("Operation saveVehicle: vehicle {}", vehicleDTO);

		vehicleService.saveVehicle(vehicleDTO);

		return ResponseEntity.created(URI.create("/veiculos")).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateVehicle(@Valid @RequestBody VehicleDTO vehicleDTO, @PathVariable("id") Long id) {
		log.info("Operation updateVehicle: vehicle {}, id {}", vehicleDTO, id);

		vehicleService.updateVehicle(vehicleDTO, id);

		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updatePartialVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable("id") Long id) {
		log.info("Operation updatePartialVehicle: vehicle data {}, id {}", vehicleDTO, id);

		vehicleService.updateVehicle(vehicleDTO, id);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable("id") Long id) {
		log.info("Operation deleteVehicle: id {}", id);

		vehicleService.deleteVehicle(id);

		return ResponseEntity.noContent().build();
	}
}
