package net.datenwerke.async.configurations;

import net.datenwerke.async.PoolConfiguration;

public class CachedThreadPoolConfig implements PoolConfiguration {

	@Override
	public PoolType getType() {
		return PoolType.CACHED;
	}

	@Override
	public int getCoreSize() {
		return 0;
	}

}
