package org.ebean.monitor.domain;

import org.ebean.monitor.domain.finder.DAppFinder;
import io.ebean.annotation.Cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The "Application" metrics relate to.
 */
@Cache(nearCache = true, naturalKey = "name")
@Entity
@Table(name = "app")
public class DApp extends BaseDomain {

  public static final DAppFinder find = new DAppFinder();

  @Column(unique = true, nullable = false, length = 200)
  private String name;

  public DApp(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
