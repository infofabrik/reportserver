package net.datenwerke.dbpool.config;

public interface ConnectionConfig {

   public boolean isReadOnly();

   public Integer getIsolationLevel();
}
