package org.ebean.monitor.ingest;

import io.ebean.DB;
import org.ebean.monitor.domain.DMetric;
import org.ebean.monitor.domain.DMetricEntry;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Process the metric level ingestion - metrics and entries.
 */
@Singleton
class ProcessMetrics {

  /**
   * Ingest the metric level.
   * <p>
   * Creates metrics if needed and ingests all the entries.
   */
  void ingestMetrics(IngestHeader header) {

    for (IngestDbData dbDatum : header.getDbData()) {
      final Map<String, DMetric> metricMap = dbDatum.buildMap();
      final List<IngestEntry> ingestEntries = dbDatum.assignMetrics(metricMap);

      persistEntries(dbDatum, ingestEntries);
    }
  }

  private void persistEntries(IngestDbData dbDatum, List<IngestEntry> ingestEntries) {

    List<DMetricEntry> entries = new ArrayList<>();
    for (IngestEntry ingestEntry : ingestEntries) {
      entries.add(dbDatum.createMetricEntry(ingestEntry));
    }

    DB.saveAll(entries);
  }

}
