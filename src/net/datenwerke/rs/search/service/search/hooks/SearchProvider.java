package net.datenwerke.rs.search.service.search.hooks;

import java.util.List;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.search.service.search.results.SearchFilter;
import net.datenwerke.rs.search.service.search.results.SearchResultEntry;

public interface SearchProvider extends Hook {

   public List<SearchResultEntry> search(String query, SearchFilter filter);
}
