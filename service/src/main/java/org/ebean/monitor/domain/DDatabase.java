package org.ebean.monitor.domain;

import io.ebean.annotation.Cache;
import io.ebean.annotation.Length;
import io.ebean.annotation.NotNull;
import org.ebean.monitor.domain.finder.DDatabaseFinder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The "Database" of the Application the metrics relate to.
 * <p>
 * Most Applications will have 1 database (eg. "db")
 * </p>
 */
@Cache(nearCache = true, naturalKey = {"fullName"})
@Entity
@Table(name = "app_db")
public class DDatabase extends BaseDomain {

  public static final DDatabaseFinder find = new DDatabaseFinder();

  /**
   * The Application this database belongs to.
   */
  @ManyToOne
  @NotNull
  private final DApp app;

  /**
   * The full database name (eg. "myapp.db")
   */
  @Column(unique = true, nullable = false, length = 200)
  private final String name;

  @Length(40)
  @NotNull
  private String shortName;

  public DDatabase(DApp app, String shortName) {
    this.name = deriveName(app, shortName);
    this.app = app;
    this.shortName = shortName;
  }

  public static String deriveName(DApp app, String shortName) {
    return app.getName() + "." + shortName;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public DApp getApp() {
    return app;
  }
}
