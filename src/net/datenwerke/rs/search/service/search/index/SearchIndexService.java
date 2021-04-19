package net.datenwerke.rs.search.service.search.index;

import java.util.List;

public interface SearchIndexService {

	public void addToIndex(Object o);

	public List<?> locate(Class<?> clazz, String query, int limit);

	public void commit();

	void flushIndex();

}
