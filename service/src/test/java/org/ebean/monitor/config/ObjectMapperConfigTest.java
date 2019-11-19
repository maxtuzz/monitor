package org.ebean.monitor.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ebean.monitor.ResourceHelp;
import org.ebean.monitor.api.MetricRequest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectMapperConfigTest {

  @Test
  public void get() throws JsonProcessingException {

    final ObjectMapper mapper = ObjectMapperConfig.get();

    final String json = ResourceHelp.read("/request/header-1.json");

    final MetricRequest request1 = mapper.readValue(json, MetricRequest.class);
    final String asJson = mapper.writeValueAsString(request1);

    assertThat(asJson).isEqualTo(json);
  }
}