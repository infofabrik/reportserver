package net.datenwerke.dbpool.config.predefined;

import java.util.Date;
import java.util.Properties;

import net.datenwerke.dbpool.config.ConnectionPoolConfig;


public class InternalH2ConnectionPool implements ConnectionPoolConfig {

	@Override
	public String getDriver() {
		return "org.h2.Driver";
	}

	@Override
	public String getUsername() {
		return "sa";
	}

	@Override
	public String getPassword() {
		return "";
	}

	@Override
	public String getJdbcUrl() {
		return "jdbc:h2:mem:;LOG=0;LOCK_MODE=0;UNDO_LOG=0";
	}

	@Override
	public boolean isPoolable() {
		return false;
	}

	@Override
	public boolean isMightChange() {
		return false;
	}
	
	@Override
	public Properties getProperties() {
		return new Properties();
	}

	@Override
	public Date getLastUpdated() {
		return null;
	}
	
	@Override
	public Properties getJdbcProperties() {
		return null;
	}

}
