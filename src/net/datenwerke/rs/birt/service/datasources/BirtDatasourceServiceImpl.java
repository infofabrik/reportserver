package net.datenwerke.rs.birt.service.datasources;

import java.util.Collection;

import javax.persistence.EntityManager;

import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceConfig;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceConfig__;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.security.service.eventlogger.annotations.FireMergeEntityEvents;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class BirtDatasourceServiceImpl implements BirtDatasourceService {

	private final Provider<EntityManager> entityManagerProvider;
	
	@Inject
	public BirtDatasourceServiceImpl(
			Provider<EntityManager> entityManagerProvider) {
		this.entityManagerProvider = entityManagerProvider;
	}

	@Override
	@QueryByAttribute(where=BirtReportDatasourceConfig__.report)
	public Collection<BirtReportDatasourceConfig> getDatasourceConfigsWith(Report report) {
		return null; // magic
	}
	
	@Override
	@FireMergeEntityEvents
	public BirtReportDatasourceConfig merge(BirtReportDatasourceConfig config) {
		return entityManagerProvider.get().merge(config);
	}
	
}
