package net.datenwerke.rs.birt.service.datasources.eventhandler;

import com.google.inject.Inject;

import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceDefinition;
import net.datenwerke.rs.resultcache.ResultCacheKeyDatasource;
import net.datenwerke.rs.resultcache.ResultCacheService;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.MergeEntityEvent;

public class HandleBirtDatasourceMergeEvents implements EventHandler<MergeEntityEvent> {

	private ResultCacheService resultCacheService;

	@Inject
	public HandleBirtDatasourceMergeEvents(ResultCacheService resultCacheService) {
		this.resultCacheService = resultCacheService;
	}

	@Override
	public void handle(MergeEntityEvent event) {
		BirtReportDatasourceDefinition ds = (BirtReportDatasourceDefinition) event.getObject();
		resultCacheService.removeFromResultCache(new ResultCacheKeyDatasource(ds));
	}

}
