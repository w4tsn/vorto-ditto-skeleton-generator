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

import org.eclipse.vorto.codegen.api.GenerationResultZip;
import org.eclipse.vorto.codegen.api.GeneratorInfo;
import org.eclipse.vorto.codegen.api.GeneratorTaskFromFileTemplate;
import org.eclipse.vorto.codegen.api.IGenerationResult;
import org.eclipse.vorto.codegen.api.IVortoCodeGenProgressMonitor;
import org.eclipse.vorto.codegen.api.IVortoCodeGenerator;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.api.VortoCodeGeneratorException;
import org.eclipse.vorto.codegen.utils.GenerationResultBuilder;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import com.othermo.vorto.plugin.dittoskeleton.templates.ThingSkeletonTemplate;

/**
 * Vorto Generator which generates JSON skeleton files for Eclipse Ditto in order to create Things
 * with sane default values.
 */
class EclipseDittoSkeletonGenerator implements IVortoCodeGenerator {

  override generate(InformationModel infomodel, InvocationContext invocationContext,
      IVortoCodeGenProgressMonitor monitor) throws VortoCodeGeneratorException {

    var output = new GenerationResultZip(infomodel, getServiceKey());

    var template = new GeneratorTaskFromFileTemplate(new ThingSkeletonTemplate())
    template.generate(infomodel, invocationContext, output)

    return output
  }

  override getServiceKey() {
    return "eclipsedittoskeleton";
  }

  override getInfo() {
    return GeneratorInfo.basicInfo("Eclipse Ditto Skeleton",
        "Creates JSON skeleton files in order to get skeleton Things with sane defaults for creation in Eclipse Ditto.",
        "othermo Team").production();
  }
}
