package net.datenwerke.rs.search.service.search;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.search.service.search.index.LuceneSearchIndexServiceImpl;
import net.datenwerke.rs.search.service.search.index.SearchIndexService;

public class SearchModule extends AbstractReportServerModule{

	@Override
	protected void configure() {
		
		bind(SearchService.class).to(SearchServiceImpl.class);
		bind(SearchIndexService.class).to(LuceneSearchIndexServiceImpl.class);
		
		bind(SearchStartup.class).asEagerSingleton();
	}

}
