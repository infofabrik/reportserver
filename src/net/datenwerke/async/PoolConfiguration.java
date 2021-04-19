package net.datenwerke.async;

public interface PoolConfiguration {

	public enum PoolType{
		SINGLE,
		CACHED,
		FIXED
	}
	
	PoolType getType();
	
	int getCoreSize(); 
}
