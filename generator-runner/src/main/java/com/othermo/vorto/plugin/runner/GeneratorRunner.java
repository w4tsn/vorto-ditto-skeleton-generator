package com.othermo.vorto.plugin.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages={"org.eclipse.vorto.codegen.spi.config",
		"org.eclipse.vorto.codegen.spi.controllers",
		"org.eclipse.vorto.codegen.spi.repository",
		"org.eclipse.vorto.codegen.spi.service","com.othermo.vorto.plugin.runner"},excludeFilters = {@ComponentScan.Filter(
type = FilterType.ASSIGNABLE_TYPE)})
public class GeneratorRunner {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GeneratorRunner.class, args);
	}
}