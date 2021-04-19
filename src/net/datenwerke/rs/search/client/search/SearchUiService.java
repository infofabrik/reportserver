package net.datenwerke.rs.search.client.search;

public interface SearchUiService {

	void runSearch(String searchStr);

	void displaySearchModule();

	String enhanceQuery(String query);

}
