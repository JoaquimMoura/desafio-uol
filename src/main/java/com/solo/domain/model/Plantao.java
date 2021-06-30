package com.solo.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Joaquim
 * 
 * @class Classe que representa um Responsavel
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Plantao")
@Table(name = "plantao")
public class Plantao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String dataPlantao;

}
