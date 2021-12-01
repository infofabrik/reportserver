package net.datenwerke.rs.utils.man;


public interface ManPageService {

	String getManPage(String identifier);
	
	String getManPage(String identifier, boolean useCache);

	void clearCache();

	void clearCache(String identifier);

	String getManPageFailsafe(String identifier);
}
