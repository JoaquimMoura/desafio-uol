package com.solo.api.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Joaquim
 * 
 * @class Classe que representa um ResponsavelModel
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsavelModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dia;
	private String nomeResponsavel;

}
