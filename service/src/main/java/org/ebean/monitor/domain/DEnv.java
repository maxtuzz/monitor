package org.ebean.monitor.domain;

import org.ebean.monitor.domain.finder.DEnvFinder;
import io.ebean.annotation.Cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The "Environment" the metrics relate to.
 * <p>
 * For example "prod", "dev", "sand-pit".
 * </p>
 */
@Cache(nearCache = true, naturalKey = "name")
@Entity
@Table(name = "env")
public class DEnv extends BaseDomain {

  public static final DEnvFinder find = new DEnvFinder();

  /**
   * The unique environment name.
   */
  @Column(unique = true, nullable = false, length = 50)
  private final String name;

  public DEnv(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
