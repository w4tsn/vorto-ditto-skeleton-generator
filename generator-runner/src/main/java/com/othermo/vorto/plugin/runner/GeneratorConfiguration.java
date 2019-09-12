package com.othermo.vorto.plugin.runner;

import com.othermo.vorto.plugin.dittoskeleton.EclipseDittoSkeletonGenerator;
import org.eclipse.vorto.codegen.spi.config.AbstractGeneratorConfiguration;
import org.eclipse.vorto.codegen.spi.model.Generator;
import org.springframework.stereotype.Component;

@Component
public class GeneratorConfiguration extends AbstractGeneratorConfiguration {
	
	@Override
	protected void doSetup() {
		addGenerator(Generator.create("/generators/eclipsedittoskeletongenerator.properties", EclipseDittoSkeletonGenerator.class));
	}
}
