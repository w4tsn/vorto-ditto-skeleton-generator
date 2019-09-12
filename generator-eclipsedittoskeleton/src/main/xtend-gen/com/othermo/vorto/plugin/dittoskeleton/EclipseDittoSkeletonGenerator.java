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
import org.eclipse.vorto.codegen.api.GenerationResultZip;
import org.eclipse.vorto.codegen.api.GeneratorInfo;
import org.eclipse.vorto.codegen.api.GeneratorTaskFromFileTemplate;
import org.eclipse.vorto.codegen.api.IGenerationResult;
import org.eclipse.vorto.codegen.api.IVortoCodeGenProgressMonitor;
import org.eclipse.vorto.codegen.api.IVortoCodeGenerator;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.api.VortoCodeGeneratorException;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;

/**
 * Vorto Generator which generates JSON skeleton files for Eclipse Ditto in order to create Things
 * with sane default values.
 */
@SuppressWarnings("all")
public class EclipseDittoSkeletonGenerator implements IVortoCodeGenerator {
  @Override
  public IGenerationResult generate(final InformationModel infomodel, final InvocationContext invocationContext, final IVortoCodeGenProgressMonitor monitor) throws VortoCodeGeneratorException {
    String _serviceKey = this.getServiceKey();
    GenerationResultZip output = new GenerationResultZip(infomodel, _serviceKey);
    ThingSkeletonTemplate _thingSkeletonTemplate = new ThingSkeletonTemplate();
    GeneratorTaskFromFileTemplate<InformationModel> template = new GeneratorTaskFromFileTemplate<InformationModel>(_thingSkeletonTemplate);
    template.generate(infomodel, invocationContext, output);
    return output;
  }
  
  @Override
  public String getServiceKey() {
    return "eclipsedittoskeleton";
  }
  
  @Override
  public GeneratorInfo getInfo() {
    GeneratorInfo _basicInfo = GeneratorInfo.basicInfo("Eclipse Ditto Skeleton", 
      "Creates JSON skeleton files in order to get skeleton Things with sane defaults for creation in Eclipse Ditto.", 
      "othermo Team");
    return _basicInfo.production();
  }
}
