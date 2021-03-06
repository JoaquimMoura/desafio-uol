package com.solo.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.build()
				.useDefaultResponseMessages(false)
		        .globalResponseMessage(RequestMethod.POST, responseMessageForPOST());
		// @formatter:on
	}

	private ApiInfo apiInfo() {
		return new  ApiInfoBuilder()
				.title("Teste De Conhecimentos para o perfil de Engenheiro de Software")
				.description("Listar os responsáveis por resolução de incidentes. ")
				.version("V1.0.0")
				.build();
	}
	
	@SuppressWarnings("serial")
	private List<ResponseMessage> responseMessageForPOST()
	{
	    return new ArrayList<ResponseMessage>() {{
	        add(new ResponseMessageBuilder()
	            .code(500)
	            .message("500 message")
	            .responseModel(new ModelRef("Error"))
	            .build());
	        add(new ResponseMessageBuilder()
	            .code(404)
	            .message("Not Found!")
	            .build());
	    }};
	}
}