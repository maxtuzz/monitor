package org.ebean.monitor.ingest;

import org.ebean.monitor.api.MetricData;
import org.ebean.monitor.api.MetricDbData;
import org.ebean.monitor.domain.DApp;
import org.ebean.monitor.domain.DDatabase;
import org.ebean.monitor.domain.DMetric;
import org.ebean.monitor.domain.DMetricEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class IngestDbData {

  private static final Logger log = LoggerFactory.getLogger(IngestDbData.class);

  private final IngestHeader header;

  private final MetricDbData metricDbData;

  private final DDatabase mdb;

  private final Map<String, IngestEntry> entryMap = new LinkedHashMap<>();

  IngestDbData(IngestHeader header, MetricDbData metricDbData, DDatabase mdb) {
    this.header = header;
    this.metricDbData = metricDbData;
    this.mdb = mdb;
  }


  DDatabase getDDatabase() {
    return mdb;
  }

  Set<String> metricKeys() {

    for (MetricData data : metricDbData.metrics) {
      final String key = MetricKey.of(data);
      final IngestEntry dup = entryMap.put(key, new IngestEntry(key, data));
      if (dup != null) {
        log.error("Lost metric due to duplicate metric key? " + key);
      }
    }
    return entryMap.keySet();
  }


  List<IngestEntry> entriesFor(Set<String> missingKeys) {
    List<IngestEntry> list = new ArrayList<>(missingKeys.size());
    for (String key : missingKeys) {
      list.add(entryMap.get(key));
    }
    return list;
  }

  /**
   * Assign DMetric and return list for persisting.
   */
  List<IngestEntry> assignMetrics(Map<String, DMetric> metricMap) {

    List<IngestEntry> list = new ArrayList<>(entryMap.size());

    for (IngestEntry ingestEntry : entryMap.values()) {
      final DMetric metric = metricMap.get(ingestEntry.getKey());
      if (metric == null) {
        log.error("Failed metric lookup for key: " + ingestEntry.getKey());
      } else {
        list.add(ingestEntry.assignMetric(metric));
      }
    }
    return list;
  }

  DMetricEntry createMetricEntry(IngestEntry ingestEntry) {
    return header.createMetricEntry(this, ingestEntry);
  }

  Map<String, DMetric> createMissing(Set<String> missingKeys) {
      return createNewMetrics(entriesFor(missingKeys));
  }

  private Map<String, DMetric> createNewMetrics(List<IngestEntry> missingEntries) {

    Map<String, DMetric> map = new HashMap<>();

    for (IngestEntry entry : missingEntries) {
      final DMetric metric = createMetric(entry);
      map.put(metric.getKey(), metric);
    }
    return map;
  }

  private DMetric createMetric(IngestEntry entry) {

    final MetricData data = entry.getData();
    DMetric metric = new DMetric(entry.getKey(), data.name, data.type);
    metric.setSql(data.sql);
    if (data.loc != null) {
      metric.setLoc(data.loc);
      final DApp app = header.getApp();
    }

    return metric;
  }
}
