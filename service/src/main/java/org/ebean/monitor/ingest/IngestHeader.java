package org.ebean.monitor.ingest;

import org.ebean.monitor.api.MetricData;
import org.ebean.monitor.domain.DApp;
import org.ebean.monitor.domain.DEnv;
import org.ebean.monitor.domain.DMetricEntry;

import java.time.Instant;
import java.util.List;

class IngestHeader {

  private final Instant eventTime;
  private final DEnv env;
  private final DApp app;
  private final List<IngestDbData> dbData;

  IngestHeader(Instant eventTime, DEnv env, DApp app, List<IngestDbData> dbData) {
    this.eventTime = eventTime;
    this.env = env;
    this.app = app;
    this.dbData = dbData;
  }

  List<IngestDbData> getDbData() {
    return dbData;
  }

  DMetricEntry createMetricEntry(IngestDbData dbDatum, IngestEntry ingestEntry) {

    DMetricEntry entry = new DMetricEntry(ingestEntry.getMetric(), env, app, eventTime, dbDatum.getDDatabase());
    final MetricData data = ingestEntry.getData();

    entry.setCount(data.count);
    entry.setMax(data.max);
    entry.setMean(data.mean);
    entry.setTotal(data.total);

    return entry;
  }
}