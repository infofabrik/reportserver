package net.datenwerke.rs.birt.service.datasources;

import java.util.Collection;

import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public interface BirtDatasourceService {

	Collection<BirtReportDatasourceConfig> getDatasourceConfigsWith(
			Report report);

	BirtReportDatasourceConfig merge(BirtReportDatasourceConfig config);

}
