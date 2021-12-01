package net.datenwerke.rs.resultcache;

public interface CacheableResult {

	public boolean validate();
	public boolean cleanup();
	public boolean cleanup(boolean force);
	public Long getTimeout();
	public void setTimeout(Long timeout);
	public Long getGracePeriod();
	public void setGracePeriod(Long grace);
	public boolean consumes(ResultCacheKey cacheKey);
}
