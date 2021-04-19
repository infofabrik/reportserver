package net.datenwerke.rs.base.service.datasources.eventhandler;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasource;
import net.datenwerke.rs.resultcache.ResultCacheKeyDatasource;
import net.datenwerke.rs.resultcache.ResultCacheService;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.MergeEntityEvent;

public class HandleCsvDatasourceMergeEvents implements EventHandler<MergeEntityEvent> {

	private ResultCacheService resultCacheService;

	@Inject
	public HandleCsvDatasourceMergeEvents(ResultCacheService resultCacheService) {
		this.resultCacheService = resultCacheService;
	}

	@Override
	public void handle(MergeEntityEvent event) {
		CsvDatasource ds = (CsvDatasource) event.getObject();
		resultCacheService.removeFromResultCache(new ResultCacheKeyDatasource(ds));
	}

}
