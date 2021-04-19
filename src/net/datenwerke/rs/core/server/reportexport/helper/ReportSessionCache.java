package net.datenwerke.rs.core.server.reportexport.helper;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.servlet.SessionScoped;

@SessionScoped
public class ReportSessionCache {

	private Cache<String, ReportSessionCacheEntry> cache;
	
	private int maximumSize = 100;
	private int expiresAfter = 120;
	
	public ReportSessionCache(){
		cache = CacheBuilder.newBuilder()
			       .maximumSize(getMaximumSize())
			       .expireAfterWrite(getExpiresAfter(), TimeUnit.MINUTES)
			       .build();
	}
	
	public void put(String key, ReportSessionCacheEntry entry){
		cache.put(key, entry);
	}
	
	public ReportSessionCacheEntry get(String key){
		return cache.getIfPresent(key);
	}
	
	public void setMaximumSize(int maximumSize) {
		this.maximumSize = maximumSize;
	}
	
	public int getMaximumSize() {
		return maximumSize;
	}
	
	public void setExpiresAfter(int expiresAfter) {
		this.expiresAfter = expiresAfter;
	}
	
	public int getExpiresAfter() {
		return expiresAfter;
	}
	
}
