/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 * 
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package com.othermo.vorto.plugin.dittoskeleton;

import com.othermo.vorto.plugin.dittoskeleton.templates.ThingSkeletonTemplate;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.vorto.plugin.generator.GeneratorException;
import org.eclipse.vorto.plugin.generator.GeneratorPluginInfo;
import org.eclipse.vorto.plugin.generator.ICodeGenerator;
import org.eclipse.vorto.plugin.generator.IGenerationResult;
import org.eclipse.vorto.plugin.generator.InvocationContext;
import org.eclipse.vorto.plugin.generator.utils.GeneratorTaskFromFileTemplate;
import org.eclipse.vorto.plugin.generator.utils.IGeneratedWriter;
import org.eclipse.vorto.plugin.generator.utils.SingleGenerationResult;

/**
 * Vorto Generator which generates JSON skeleton files for Eclipse Ditto in order to create Things
 * with sane default values.
 */
@SuppressWarnings("all")
public class EclipseDittoSkeletonGenerator implements ICodeGenerator {
  private static final String KEY = "eclipsedittoskeleton";
  
  @Override
  public IGenerationResult generate(final InformationModel infomodel, final InvocationContext invocationContext) throws GeneratorException {
    IGenerationResult output = null;
    SingleGenerationResult _singleGenerationResult = new SingleGenerationResult("application/schema+json");
    output = _singleGenerationResult;
    ThingSkeletonTemplate _thingSkeletonTemplate = new ThingSkeletonTemplate();
    new GeneratorTaskFromFileTemplate<InformationModel>(_thingSkeletonTemplate).generate(infomodel, invocationContext, ((IGeneratedWriter) output));
    return output;
  }
  
  @Override
  public GeneratorPluginInfo getMeta() {
    return GeneratorPluginInfo.Builder(EclipseDittoSkeletonGenerator.KEY).withName("Eclipse Ditto Skeleton").withDescription("Creates JSON skeleton files in order to get skeleton Things with sane defaults for creation in Eclipse Ditto.").withVendor("othermo Team").build();
  }
}
