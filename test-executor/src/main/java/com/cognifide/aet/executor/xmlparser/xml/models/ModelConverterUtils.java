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
package com.cognifide.aet.executor.xmlparser.xml.models;

import com.cognifide.aet.executor.model.ExtendedUrl;
import com.cognifide.aet.executor.xmlparser.api.ParseException;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

public final class ModelConverterUtils {

  private static final String EMPTY_URL_MESSAGE = "Empty url";
  private static final String DUPLICATED_URL_MESSAGE = "Duplicated url";
  private static final Integer DESCRIPTION_MAX_LENGTH = 40;
  private static final String DESCRIPTION_PATTERN = "^[a-zA-Z0-9\\_\\- ]+$";
  private static final String TOO_LONG_DESCRIPTION_MSG = String
      .format("URL attribute description is longer than max %d chars", DESCRIPTION_MAX_LENGTH);
  private static final String INVALID_DESCRIPTION_MSG = "Invalid URL description provided";

  private ModelConverterUtils() {
    // empty utils constructor
  }

  static List<ExtendedUrl> extendUrlsList(List<Url> urls)
      throws ParseException, UnsupportedEncodingException {
    List<ExtendedUrl> extendedUrls = Lists.newArrayList();
    List<ExtendedUrl> duplicatedUrls = Lists.newArrayList();
    Set<String> names = Sets.newHashSet();
    StringBuilder builder = new StringBuilder();

    for (Url url : urls) {
      String urlName = extractUrlName(url);
      ExtendedUrl extendedUrl = new ExtendedUrl(url.getHref(), urlName, url.getDescription());
      if (StringUtils.isBlank(extendedUrl.getUrl())) {
        buildErrorMessage(builder, EMPTY_URL_MESSAGE, extendedUrl);
      }
      if (!names.add(urlName)) {
        duplicatedUrls.add(extendedUrl);
        buildErrorMessage(builder, DUPLICATED_URL_MESSAGE, extendedUrl);
      } else {
        extendedUrls.add(extendedUrl);
      }
      validateUrlDescription(builder, extendedUrl);
    }
    if (builder.length() > 0) {
      throw new ParseException(builder.toString());
    }
    return extendedUrls;
  }

  private static String extractUrlName(Url url) throws UnsupportedEncodingException {
    String urlName;
    if (StringUtils.isBlank(url.getName())) {
      urlName = url.getHref().trim();
    } else {
      urlName = URLEncoder.encode(url.getName().trim(), StandardCharsets.UTF_8.displayName());
    }
    return urlName;
  }

  private static void validateUrlDescription(StringBuilder builder, ExtendedUrl url) {
    String description = url.getDescription();
    if (StringUtils.isNotEmpty(description) && description.length() > DESCRIPTION_MAX_LENGTH) {
      buildErrorMessage(builder, TOO_LONG_DESCRIPTION_MSG, url);
    }
    if (StringUtils.isNotEmpty(description) && !description.matches(DESCRIPTION_PATTERN)) {
      buildErrorMessage(builder, INVALID_DESCRIPTION_MSG, url);
    }
  }

  private static void buildErrorMessage(StringBuilder builder, String baseMessage,
      ExtendedUrl url) {
    builder.append(baseMessage);
    formatIfNotBlank(builder, ", with URL: %s", url.getUrl());
    formatIfNotBlank(builder, ", with name: %s", url.getName());
    formatIfNotBlank(builder, ", with description: %s", url.getDescription());
    builder.append(String.format("%n"));
  }

  private static void formatIfNotBlank(StringBuilder builder, String message, String string) {
    if (StringUtils.isNotBlank(string)) {
      builder.append(String.format(message, string));
    }
  }
}
