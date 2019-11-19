package org.ebean.monitor.ingest;

import org.ebean.monitor.api.MetricRequest;

import javax.inject.Singleton;

@Singleton
public class Ingest {

  private final ProcessHeader lookup;

  private final ProcessMetrics lookupMetrics;

  Ingest(ProcessHeader lookup, ProcessMetrics lookupMetrics) {
    this.lookup = lookup;
    this.lookupMetrics = lookupMetrics;
  }

  /**
   * Ingest a request persisting it to the DB.
   */
  public void ingest(MetricRequest request) {

    // first ingest the header
    final IngestHeader header = lookup.ingestHeader(request);

    // now ingest all the metrics
    lookupMetrics.ingestMetrics(header);
  }

}
