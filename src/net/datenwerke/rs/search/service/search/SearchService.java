package net.datenwerke.rs.search.service.search;

import java.util.List;

import net.datenwerke.rs.search.service.search.results.SearchFilter;
import net.datenwerke.rs.search.service.search.results.SearchResultList;

/**
 * Service that exposes search functionality of ReportServer.
 */
public interface SearchService {

   public String rebuildIndex();

   public List<?> locate(String query);

   public List<?> locate(Class<?> clazz, String query);

   public SearchResultList findAsResultList(String searchStr);

   public SearchResultList findAsResultList(String query, SearchFilter filter);

   String enhanceQuery(String query);

}
