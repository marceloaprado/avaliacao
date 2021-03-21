package br.com.tinnova.avaliacao.exercicio5.veiculos.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Marcelo Alves Prado
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(schema = "tinnova", name = "veiculo")
public class Vehicle implements Serializable {
	private static final long serialVersionUID = -7213325046655661842L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name = "veiculo")
	private String vehicle;
	
	@ManyToOne(targetEntity = Brand.class)
	@JoinColumn(name = "marca_id")
	private Brand brand;
	
	@Column(name = "ano")
	private Integer year;
	
	@Column(name = "descricao")
	private String description;
	
	@Column(name = "vendido")
	private Boolean isSold;
	
	@Column(name = "created", nullable = false, updatable = false)
	@CreatedDate	
	private LocalDateTime created;
	
	@Column(name = "updated")
	@LastModifiedDate
	private LocalDateTime updated;
}
