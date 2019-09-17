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

import org.eclipse.vorto.plugin.generator.GeneratorException
import org.eclipse.vorto.plugin.generator.GeneratorPluginInfo
import org.eclipse.vorto.plugin.generator.ICodeGenerator
import org.eclipse.vorto.plugin.generator.IGenerationResult
import org.eclipse.vorto.plugin.generator.InvocationContext
import org.eclipse.vorto.plugin.generator.utils.GeneratorTaskFromFileTemplate
import org.eclipse.vorto.plugin.generator.utils.IGeneratedWriter
import org.eclipse.vorto.plugin.generator.utils.SingleGenerationResult
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel
import com.othermo.vorto.plugin.dittoskeleton.templates.ThingSkeletonTemplate

/**
 * Vorto Generator which generates JSON skeleton files for Eclipse Ditto in order to create Things
 * with sane default values.
 */
class EclipseDittoSkeletonGenerator implements ICodeGenerator {

  val static String KEY = "eclipsedittoskeleton"

  override generate(InformationModel infomodel, InvocationContext invocationContext) throws GeneratorException {
    var IGenerationResult output = null
    
    output = new SingleGenerationResult("application/schema+json")

    new GeneratorTaskFromFileTemplate(new ThingSkeletonTemplate())
      .generate(infomodel, invocationContext, output as IGeneratedWriter)

    return output
  }

  override getMeta() {
    return GeneratorPluginInfo.Builder(KEY)
      .withName("Eclipse Ditto Skeleton")
      .withDescription("Creates JSON skeleton files in order to get skeleton Things with sane defaults for creation in Eclipse Ditto.")
      .withVendor("othermo Team")
      .build
  }
}
