package net.datenwerke.rs.incubator.service.jaspertotable;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportVariant;
import net.datenwerke.rs.base.service.reportengines.jasper.util.JasperUtilsService;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.incubator.service.jaspertotable.entities.JasperToTableConfig;

public class JasperToTableServiceImpl implements JasperToTableService {
	private final JasperUtilsService jasperUtils;
	
	@Inject
	public JasperToTableServiceImpl(
		JasperUtilsService jasperUtils	
		){
		
		/* store objects */
		this.jasperUtils = jasperUtils;
	}
	
	@Override
	public TableReport transformToTableReport(JasperReport report) {
		ReportProperty property = report.getReportProperty(JasperToTableModule.PROPERTY_NAME);
		if(null == property && report instanceof JasperReportVariant){
			JasperReport parent = (JasperReport) report.getParent();
			property = parent.getReportProperty(JasperToTableModule.PROPERTY_NAME);
		}

		if(null == property)
			throw new IllegalArgumentException("Report is not to be transformed to a table report");

		
		TableReport tReport = new TableReport();
		
		/* connection */
		if(null != report.getMasterFile()){
			DatasourceContainer dsContainer = new DatasourceContainer();
			tReport.setDatasourceContainer(dsContainer);
			
			dsContainer.setDatasource(report.getDatasourceContainer().getDatasource());

			if(null == ((JasperToTableConfig)property).getDatasourceContainer() || null ==  ((JasperToTableConfig)property).getDatasourceContainer().getDatasource() ){
				String query = jasperUtils.getQueryFromJRXML(report.getMasterFile().getContent());
				if(null != query)
					query = StringUtils.chomp(query);
				DatasourceDefinitionConfig config = report.getDatasourceContainer().createDatasourceConfig();
				if(config instanceof DatabaseDatasourceConfig)
					((DatabaseDatasourceConfig)config).setQuery(query);
				dsContainer.setDatasourceConfig(config);
			} else {
				tReport.setDatasourceContainer(((JasperToTableConfig)property).getDatasourceContainer());
			}
		}
		
		/* parameters */
		tReport.setParameterDefinitions(report.getParameterDefinitions());
		tReport.setParameterInstances(report.getParameterInstances());
		tReport.setSelectAllColumns(true);
		
		return tReport;
	}

}
