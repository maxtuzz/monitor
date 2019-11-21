package org.ebean.monitor.ingest;

import org.ebean.monitor.api.MetricDbData;
import org.ebean.monitor.api.MetricRequest;
import org.ebean.monitor.domain.DApp;
import org.ebean.monitor.domain.DDatabase;
import org.ebean.monitor.domain.DEnv;
import org.ebean.monitor.domain.query.QDApp;
import org.ebean.monitor.domain.query.QDDatabase;
import org.ebean.monitor.domain.query.QDEnv;

import javax.inject.Singleton;
import java.time.Instant;

import static org.ebean.monitor.domain.DDatabase.deriveName;

/**
 * Process the header level properties like App, Environment etc.
 */
@Singleton
class ProcessHeader {

  /**
   * Process header level properties returning the IngestHeader.
   */
  IngestHeader ingestHeader(MetricRequest request) {

    final Instant eventTime = toInstant(request.eventTime);
    final DEnv env = lookupEnv(request.environment);
    final DApp app = lookupApp(request.appName);

    IngestHeader header = new IngestHeader(eventTime, env, app);
    for (MetricDbData db : request.dbs) {
      header.add(db, lookupDb(app, dbName(db.db)));
    }
    return header;
  }

  private static String dbName(String db) {
    return (db == null || db.trim().isEmpty()) ? "db" : db;
  }


  private Instant toInstant(long eventTime) {
    return Instant.ofEpochMilli(eventTime);
  }

  private DDatabase lookupDb(DApp app, String dbName) {

    DDatabase db = new QDDatabase()
      .name.eq(deriveName(app, dbName))
      .findOne();

    if (db == null) {
      db = new DDatabase(app, dbName);
      db.save();
    }

    return db;
  }

  private DApp lookupApp(String appName) {

    DApp app = new QDApp()
      .name.eq(appName)
      .findOne();

    if (app == null) {
      app = new DApp(appName);
      app.save();
    }

    return app;
  }


  private DEnv lookupEnv(String environment) {

    DEnv env = new QDEnv()
      .name.eq(environment)
      .findOne();

    if (env == null) {
      env = new DEnv(environment);
      env.save();
    }

    return env;
  }
}
