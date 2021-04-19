package net.datenwerke.dbpool.config;

public interface ConnectionPoolConfigFactory {

	public ConnectionPoolConfigImpl create(Long id);
}
