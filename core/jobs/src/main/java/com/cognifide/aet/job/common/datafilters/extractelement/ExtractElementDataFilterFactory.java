/**
 * AET
 *
 * Copyright (C) 2013 Cognifide Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.cognifide.aet.job.common.datafilters.extractelement;

import com.cognifide.aet.job.api.datafilter.DataFilterFactory;
import com.cognifide.aet.job.api.datafilter.DataFilterJob;
import com.cognifide.aet.job.api.exceptions.ParametersException;
import java.util.Map;

import com.cognifide.aet.job.api.info.FeatureMetadata;
import com.cognifide.aet.job.api.info.ParameterMetadata;
import org.osgi.service.component.annotations.Component;

/**
 * @author magdalena.biala
 */
@Component
public class ExtractElementDataFilterFactory implements DataFilterFactory {

  @Override
  public String getName() {
    return ExtractElementDataModifier.NAME;
  }

  @Override
  public DataFilterJob<String> createInstance(Map<String, String> params)
      throws ParametersException {
    ExtractElementDataModifier dm = new ExtractElementDataModifier();
    dm.setParameters(params);
    return dm;
  }

  @Override
  public FeatureMetadata getInformation() {
    return FeatureMetadata.builder()
            .type("Extract Element")
            .tag(getName())
            .withParameters()
            .addParameter(
                    ParameterMetadata.builder()
                            .name("Element ID")
                            .tag("elementId")
                            .withoutValues()
                            .isMandatory(false)
                            .description("Id for the element to extract. One of these parameters is required.")
                            .build()
            )
            .addParameter(
                    ParameterMetadata.builder()
                            .name("Class")
                            .tag("class")
                            .withoutValues()
                            .isMandatory(false)
                            .description("Class name for the element to extract. One of these parameters is required.")
                            .build()
            )
            .and()
            .withDeps("Source").depType(null)
            .dropTo("source-comparators")
            .group("DataFilters")
            .proxy(false)
            .wiki("https://github.com/Cognifide/aet/wiki/ExtractElementDataFilter")
            .build();
  }
}
