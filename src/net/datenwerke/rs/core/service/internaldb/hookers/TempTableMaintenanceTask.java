package net.datenwerke.rs.core.service.internaldb.hookers;

import javax.inject.Inject;

import net.datenwerke.gf.service.maintenance.hooks.MaintenanceTask;
import net.datenwerke.rs.resultcache.ResultCacheService;

public class TempTableMaintenanceTask implements MaintenanceTask {
	
	private ResultCacheService resultCacheService;

	@Inject
	public TempTableMaintenanceTask(ResultCacheService resultCacheService) {
		this.resultCacheService = resultCacheService;
	}

	@Override
	public void performMaintenance() {
		resultCacheService.maintainCache();
	}

}
