package net.datenwerke.rs.base.service.parameters.datasource;

import java.util.List;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

public interface DatasourceParameterService {

	public List<DatasourceParameterData> getParameterData(
			DatasourceParameterDefinition parameter, Report report) throws ReportExecutorException;
	
	List<DatasourceParameterData> getParameterData(DatasourceParameterDefinition parameter,
			ParameterSet parameterSet) throws ReportExecutorException;

	List<DatasourceParameterDefinition> getParametersWithDatasource(
			DatasourceDefinition ds);

	public boolean isAllowDatasourceParameterPostProcessing();

}
