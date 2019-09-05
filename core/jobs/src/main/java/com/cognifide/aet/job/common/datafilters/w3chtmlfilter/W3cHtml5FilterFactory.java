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
package com.cognifide.aet.job.common.datafilters.w3chtmlfilter;

import com.cognifide.aet.job.api.datafilter.DataFilterFactory;
import com.cognifide.aet.job.api.datafilter.DataFilterJob;
import com.cognifide.aet.job.api.exceptions.ParametersException;
import java.util.Map;

import com.cognifide.aet.job.api.info.FeatureMetadata;
import com.cognifide.aet.job.api.info.ParameterMetadata;
import org.osgi.service.component.annotations.Component;

@Component
public class W3cHtml5FilterFactory implements DataFilterFactory {

  @Override
  public String getName() {
    return W3cHtml5IssuesFilter.NAME;
  }

  @Override
  public DataFilterJob createInstance(Map<String, String> params) throws ParametersException {
    W3cHtml5IssuesFilter w3cIssuesFilter = new W3cHtml5IssuesFilter();
    w3cIssuesFilter.setParameters(params);
    return w3cIssuesFilter;
  }

  @Override
  public FeatureMetadata getInformation() {
    return FeatureMetadata.builder()
            .type("W3CHTML5 Issues")
            .tag(getName())
            .withParameters()
            .addParameter(
                    ParameterMetadata.builder()
                            .name("Message")
                            .tag("message")
                            .withoutValues()
                            .isMandatory(false)
                            .description("Exact message text of the issue to be filtered out. *see notes below. At least one parameter is required.")
                            .build()
            )
            .addParameter(
                    ParameterMetadata.builder()
                            .name("Message Pattern")
                            .tag("messagePattern")
                            .withoutValues()
                            .isMandatory(false)
                            .description("A regular expression that matches message text of the issue to be filtered out. At least one parameter is required.")
                            .build()
            )
            .addParameter(
                    ParameterMetadata.builder()
                            .name("Line")
                            .tag("line")
                            .withoutValues()
                            .isMandatory(false)
                            .description("A line in the source file where the issue appears. At least one parameter is required.")
                            .build()
            )
            .addParameter(
                    ParameterMetadata.builder()
                            .name("Column")
                            .tag("column")
                            .withoutValues()
                            .isMandatory(false)
                            .description("A column in the source file where the issue appears.. At least one parameter is required.")
                            .build()
            )
            .and()
            .withDeps("Source").depType(null)
            .dropTo("source-comparators")
            .group("DataFilters")
            .proxy(false)
            .wiki("https://github.com/Cognifide/aet/wiki/W3CHTML5IssuesFilter")
            .build();
  }
}
