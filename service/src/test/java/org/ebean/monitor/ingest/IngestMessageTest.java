package org.ebean.monitor.ingest;

import org.ebean.monitor.domain.DMetric;
import org.ebean.monitor.domain.DMetricEntry;
import org.ebean.monitor.domain.query.QDMetric;
import org.ebean.monitor.domain.query.QDMetricEntry;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ebean.monitor.ResourceHelp.metricRequest;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

public class IngestMessageTest {

  private final IngestMessage ingest;

  public IngestMessageTest() {
    ProcessHeader header = new ProcessHeader();
    ProcessMetrics lookupMetrics = new ProcessMetrics();
    this.ingest = new IngestMessage(header, lookupMetrics);
  }

  @Test
  public void ingest() {

    ingest.ingest(metricRequest("/request/req-1.json"));

    final DMetric met1 = new QDMetric()
      .name.eq("met1")
      .type.eq("TXN")
      .findOne();

    assertNotNull(met1);

    List<DMetricEntry> met1Entries = new QDMetricEntry()
      .metric.eq(met1)
      .findList();

    assertThat(met1Entries).hasSize(1);
    assertThat(met1Entries.get(0).getMean()).isEqualTo(42);

    ingest.ingest(metricRequest("/request/req-1b.json"));
    ingest.ingest(metricRequest("/request/req-1c.json"));

    met1Entries = new QDMetricEntry()
      .metric.eq(met1)
      .findList();

    assertThat(met1Entries).as("different loc in req-1b").hasSize(2);

    final List<DMetric> met1Hashes = new QDMetric()
      .name.eq("met1")
      .type.eq("TXN")
      .findList();

    assertThat(met1Hashes).hasSize(2);
  }

  @Test
  public void ingest2() {
    ingest.ingest(metricRequest("/request/req-2.json"));
  }
}
