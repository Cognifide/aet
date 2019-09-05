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
package com.cognifide.aet.job.common.comparators.layout;

import com.cognifide.aet.communication.api.metadata.Comparator;
import com.cognifide.aet.job.api.comparator.ComparatorFactory;
import com.cognifide.aet.job.api.comparator.ComparatorJob;
import com.cognifide.aet.job.api.comparator.ComparatorProperties;
import com.cognifide.aet.job.api.datafilter.DataFilterJob;
import com.cognifide.aet.job.api.exceptions.ParametersException;
import com.cognifide.aet.job.api.info.FeatureMetadata;
import com.cognifide.aet.job.api.info.ParameterMetadata;
import com.cognifide.aet.vs.ArtifactsDAO;
import java.util.List;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class LayoutComparatorFactory implements ComparatorFactory {

  @Reference
  private ArtifactsDAO artifactsDAO;

  @Override
  public String getType() {
    return LayoutComparator.COMPARATOR_TYPE;
  }

  @Override
  public String getName() {
    return LayoutComparator.COMPARATOR_NAME;
  }

  @Override
  public int getRanking() {
    return DEFAULT_COMPARATOR_RANKING;
  }

  @Override
  public ComparatorJob createInstance(Comparator comparator,
      ComparatorProperties comparatorProperties, List<DataFilterJob> dataFilterJobs)
      throws ParametersException {
    LayoutComparator layoutComparator = new LayoutComparator(comparatorProperties, artifactsDAO);
    layoutComparator.setParameters(comparator.getParameters());
    return layoutComparator;
  }

  @Override
  public FeatureMetadata getInformation() {
    return FeatureMetadata.builder()
            .type("Screen")
            .tag("screen")
            .withParameters()
            .addParameter(
                    ParameterMetadata.builder()
                            .name("Comparator")
                            .tag("comparator")
                            .withValues()
                            .addValue("layout")
                            .and().defaultValue("layout")
                            .isMandatory(true)
                            .description("Layout Comparator is responsible for collecting compared screenshot.")
                            .current("layout")
                            .build()
            )
            .addParameter(
                    ParameterMetadata.builder()
                            .name("Pixel Threshold")
                            .tag("pixelThreshold")
                            .withoutValues()
                            .isMandatory(false)
                            .description("The value to set the error threshold in pixels e.g if difference between photos is smaller or equal to pixel threshold, the test will pass.")
                            .build()
            )
            .addParameter(
                    ParameterMetadata.builder()
                            .name("Percentage Threshold")
                            .tag("percentageThreshold")
                            .withoutValues()
                            .isMandatory(false)
                            .description("The value to set the error threshold in percentages e.g if difference between photos is smaller or equal to pixel threshold, the test will pass.")
                            .build()
            )
            .and()
            .withDeps("screen-collectors").depType("Error")
            .dropTo("Comparators")
            .group("Comparators")
            .proxy(false)
            .wiki("https://github.com/Cognifide/aet/wiki/LayoutComparator")
            .build();
  }
}
