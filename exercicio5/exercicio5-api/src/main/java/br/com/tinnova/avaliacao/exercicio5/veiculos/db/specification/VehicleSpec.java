package br.com.tinnova.avaliacao.exercicio5.veiculos.db.specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity.Vehicle;
import br.com.tinnova.avaliacao.exercicio5.veiculos.payload.request.FindVehicleFilterRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class VehicleSpec implements Specification<Vehicle> {
	private static final long serialVersionUID = 4976755529267070912L;

	private FindVehicleFilterRequest filter;

	@Override
	public Predicate toPredicate(Root<Vehicle> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		final Collection<Predicate> predicates = new ArrayList<>();

		if (StringUtils.isNotBlank(filter.getVehicle()))
			predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("vehicle")),
					"%" + filter.getVehicle().toUpperCase() + "%"));

		if (StringUtils.isNotBlank(filter.getBrand()))
			predicates.add(criteriaBuilder.equal(root.get("brand").get("brand"), filter.getBrand()));

		if (StringUtils.isNotBlank(filter.getDescription()))
			predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("description")),
					"%" + filter.getDescription().toUpperCase() + "%"));

		if (filter.getIsSold() != null)
			predicates.add(criteriaBuilder.equal(root.get("isSold"), filter.getIsSold()));

		if (filter.getYear() != null)
			predicates.add(criteriaBuilder.equal(root.get("year"), filter.getYear()));

		if (filter.getStartCreatedInterval() != null && filter.getEndCreatedInterval() != null) {
			LocalDateTime startCreatedDate = filter.getStartCreatedInterval().atTime(0, 0, 0);
			LocalDateTime endCreatedDate = filter.getEndCreatedInterval().atTime(23, 59, 59);
			predicates.add(criteriaBuilder.between(root.get("created"), startCreatedDate, endCreatedDate));
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
