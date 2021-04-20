package net.datenwerke.rs.search.client.search;

import java.util.List;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.rs.search.client.search.dto.SearchFilterDto;

public interface SearchUiService {

   void runSearch(String searchStr);

   void displaySearchModule();

   String enhanceQuery(String query);

   /**
    * Creates a filter which only allows results of the given types, e.g. report
    * variants, or specific report types.
    * 
    * @param allowedTypes the allowed types to filter
    * @return a filter containing default values and only allowing the given types
    */
   SearchFilterDto createFilterFor(List<Class<? extends RsDto>> allowedTypes);

}
