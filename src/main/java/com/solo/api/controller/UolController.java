package com.solo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.solo.api.model.ResponsavelModel;
import com.solo.domain.exception.BusinessException;
import com.solo.domain.exception.NotfoundException;
import com.solo.domain.model.Plantao;
import com.solo.domain.service.UolService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = {"http://localhost:8081/"})
@Api(value = "UolController", description = "Listar a ordem de resolução dos incidentes.")
@RestController
@RequestMapping("/im-day")
public class UolController {

	private final UolService service;

	@Autowired
	public UolController(UolService service) {
		this.service = service;
	}

	@ApiOperation(value = "Listar planto~es. ", response = ResponsavelModel.class, notes = "Essa API deverá retornar a Lista dos plantões.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma Plantao com uma mensagem de sucesso", response = BusinessException.class),
			@ApiResponse(code = 500, message = "Caso tenhamos algum erro vamos retornar um BusinessException com a Exception", response = BusinessException.class)

	})
	@GetMapping
	public ResponseEntity<?> getlistaPlatonista() {

		try {

			List<Plantao> listNetWorkStockModel = this.service.findListPrantao();

			return ResponseEntity.ok(listNetWorkStockModel);

		} catch (NotfoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@ApiOperation(value = "Adicionar um plantão")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Plantao save(@RequestBody @Validated Plantao plantao) {
		return service.savePrantao(plantao);
	}

}
