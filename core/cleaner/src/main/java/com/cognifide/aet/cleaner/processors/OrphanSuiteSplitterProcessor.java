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
package com.cognifide.aet.cleaner.processors;

import com.cognifide.aet.cleaner.context.CleanerContext;
import com.cognifide.aet.cleaner.context.SuiteAggregationCounter;
import com.cognifide.aet.cleaner.processors.exchange.AllProjectSuitesMessageBody;
import com.cognifide.aet.cleaner.processors.exchange.SuiteMessageBody;
import com.cognifide.aet.communication.api.metadata.Suite;
import com.cognifide.aet.vs.DBKey;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = OrphanSuiteSplitterProcessor.class)
public class OrphanSuiteSplitterProcessor implements Processor {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(OrphanSuiteSplitterProcessor.class);

  @Override
  @SuppressWarnings("unchecked")
  public void process(Exchange exchange) throws Exception {
    final CleanerContext cleanerContext = exchange.getIn()
        .getHeader(CleanerContext.KEY_NAME, CleanerContext.class);
    final AllProjectSuitesMessageBody allSuites = exchange.getIn().getBody(
        AllProjectSuitesMessageBody.class);
    final DBKey dbKey = allSuites.getDbKey();

    final ImmutableList<SuiteMessageBody> body =
        FluentIterable.from(allSuites.getData()).transform(new Function<Suite, SuiteMessageBody>() {
          @Override
          public SuiteMessageBody apply(Suite suite) {
            return new SuiteMessageBody(suite, dbKey, false);
          }
        }).toList();

    exchange.getOut().setBody(body);
    exchange.getOut().setHeader(SuiteAggregationCounter.NAME_KEY,
        new SuiteAggregationCounter(allSuites.getData().size()));
    exchange.getOut().setHeader(CleanerContext.KEY_NAME, cleanerContext);
  }
}
