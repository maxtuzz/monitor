package org.ebean.monitor.ingest;

import io.ebean.DB;
import org.ebean.monitor.domain.DMetric;
import org.ebean.monitor.domain.DMetricEntry;
import org.ebean.monitor.domain.query.QDMetric;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
      final Map<String, DMetric> metricMap = lookupAllMetrics(dbDatum);
      final List<IngestEntry> ingestEntries = dbDatum.assignMetrics(metricMap);

      persistEntries(dbDatum, ingestEntries);
    }
  }

  private void persistEntries(IngestDbData dbDatum, List<IngestEntry> ingestEntries) {

    List<DMetricEntry> entries = new ArrayList<>();
    for (IngestEntry ingestEntry : ingestEntries) {
      entries.add(createMetricEntry(dbDatum, ingestEntry));
    }

    DB.saveAll(entries);
  }

  private DMetricEntry createMetricEntry(IngestDbData dbDatum, IngestEntry ingestEntry) {

    return dbDatum.createMetricEntry(ingestEntry);
  }

  private Map<String, DMetric> lookupAllMetrics(IngestDbData dbDatum) {

    final Set<String> keys = dbDatum.metricKeys();
    final Map<String, DMetric> metricMap = lookupExistingMetrics(keys);

    if (keys.size() > metricMap.size()) {
      Set<String> missingKeys = missingKeys(keys, metricMap);

      final Map<String, DMetric> newMetrics = dbDatum.createMissing(missingKeys);
      DB.saveAll(newMetrics.values());
      metricMap.putAll(newMetrics);
    }

    return metricMap;
  }

  private Set<String> missingKeys(Set<String> keys, Map<String, DMetric> metricMap) {
    Set<String> missingKeys = new HashSet<>();
    for (String key : keys) {
      if (!metricMap.containsKey(key)) {
        missingKeys.add(key);
      }
    }
    return missingKeys;
  }

  private Map<String, DMetric> lookupExistingMetrics(Set<String> keys) {

    return new QDMetric()
      .key.in(keys)
      .key.asMapKey()
      .findMap();
  }

}
