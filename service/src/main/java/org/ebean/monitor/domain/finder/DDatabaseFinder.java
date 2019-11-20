package org.ebean.monitor.domain.finder;

import io.ebean.Finder;
import org.ebean.monitor.api.AppDatabase;
import org.ebean.monitor.domain.DDatabase;
import org.ebean.monitor.domain.query.QDDatabase;

import java.util.List;

import static org.ebean.monitor.domain.query.QDDatabase.Alias.name;

public class DDatabaseFinder extends Finder<Long, DDatabase> {

  /**
   * Construct using the default Database.
   */
  public DDatabaseFinder() {
    super(DDatabase.class);
  }

  public List<AppDatabase> allDatabase() {

    return new QDDatabase()
      .select(name)
      .asDto(AppDatabase.class)
      .findList();
  }
}
