package org.ebean.monitor.ingest;

import org.ebean.monitor.api.MetricData;
import org.ebean.monitor.api.MetricDbData;
import org.ebean.monitor.domain.DApp;
import org.ebean.monitor.domain.DDatabase;
import org.ebean.monitor.domain.DEnv;
import org.ebean.monitor.domain.DMetricEntry;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Header level data for processing the metrics request.
 */
class IngestHeader {

  private final Instant eventTime;
  private final DEnv env;
  private final DApp app;
  private final List<IngestDbData> dbData = new ArrayList<>();

  IngestHeader(Instant eventTime, DEnv env, DApp app) {
    this.eventTime = truncate(eventTime);
    this.env = env;
    this.app = app;
  }

  /**
   * Truncate the event time to the minute - expected ingestion per minute for DB metrics.
   */
  static Instant truncate(Instant eventTime) {
    return eventTime.truncatedTo(ChronoUnit.MINUTES);
  }

  void add(MetricDbData db, DDatabase lookupDb) {
    dbData.add(new IngestDbData(this, db, lookupDb));
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

  DApp getApp() {
    return app;
  }
}
