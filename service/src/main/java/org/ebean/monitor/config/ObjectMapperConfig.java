package org.ebean.monitor.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ObjectMapperConfig {

  private static ObjectMapper mapper = create();

  public static ObjectMapper get() {
    return mapper;
  }

  private static ObjectMapper create() {
    return newObjectMapper()
      .setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY)
      //.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .enable(SerializationFeature.INDENT_OUTPUT)
      .registerModule(new JavaTimeModule());

  }

  private static ObjectMapper newObjectMapper() {
    SimpleModule extraModules = new SimpleModule();
    extraModules.addAbstractTypeMapping(Map.class, LinkedHashMap.class);
    extraModules.addAbstractTypeMapping(Set.class, LinkedHashSet.class);
    return new ObjectMapper().registerModule(extraModules);
  }
}
