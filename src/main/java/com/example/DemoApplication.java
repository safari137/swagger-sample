package com.example;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.*;
import static com.google.common.base.Predicates.*;
import static springfox.documentation.schema.AlternateTypeRules.*;
import static springfox.documentation.builders.PathSelectors.*;

@SpringBootApplication
@EnableSwagger2
@ComponentScan
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	  @Bean
	  public Docket petApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	          .apis(RequestHandlerSelectors.any())
	          .paths(paths())
	          .build()
	        .pathMapping("/")
	        .directModelSubstitute(LocalDate.class,
	            String.class)
	        .genericModelSubstitutes(ResponseEntity.class)
	        .alternateTypeRules(
	            newRule(typeResolver.resolve(DeferredResult.class,
	                    typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
	                typeResolver.resolve(WildcardType.class)))
	        .useDefaultResponseMessages(false)
	        .globalResponseMessage(RequestMethod.GET,
	            newArrayList(new ResponseMessageBuilder()
	                .code(500)
	                .message("500 message")
	                .responseModel(new ModelRef("Error"))
	                .build()))
	        .enableUrlTemplating(true);
	  }	  
	    
	    @Autowired
	    private TypeResolver typeResolver;
	    
	    @SuppressWarnings("unchecked")
		private Predicate<String> paths() {
	        return or(
	            regex("/people.*"));
	      }
	    
	    @Bean
	    UiConfiguration uiConfig() {
	      return new UiConfiguration(
	          "validatorUrl",// url
	          "none",       // docExpansion          => none | list
	          "alpha",      // apiSorter             => alpha
	          "schema",     // defaultModelRendering => schema
	          UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
	          false,        // enableJsonEditor      => true | false
	          true);        // showRequestHeaders    => true | false
	    }
}
