package net.datenwerke.rs.base.service.parameters.datasource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import net.datenwerke.rs.base.service.datasources.table.impl.config.TableDatasourceConfig;
import net.datenwerke.rs.base.service.parameters.locale.BaseParameterMessages;
import net.datenwerke.rs.base.service.reportengines.table.SimpleDataSupplier;
import net.datenwerke.rs.base.service.reportengines.table.SimpleDataSupplier.DataConsumer;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer__;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.utils.scripting.GroovyScript;
import net.datenwerke.rs.utils.scripting.GroovyScriptingService;
import net.datenwerke.rs.utils.simplequery.annotations.Join;
import net.datenwerke.rs.utils.simplequery.annotations.Predicate;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;

/**
 * 
 *
 */
public class DatasourceParameterServiceImpl implements DatasourceParameterService{

	public static final String CONFIG_FILE = "datasources/parameter.cf";
	public static final String PARAMETER_QUERY_TIMEOUT = "parameter.datasource.querytimeout";
	public static final String PARAMETER_POSTPROCESSING_ENABLE = "parameter.datasource.postprocessing.enable";
	
	private final SimpleDataSupplier simpleDataSupplyer;
	private final ParameterSetFactory parameterSetFactory;
	private final GroovyScriptingService scriptEngine;
	private final ConfigService configService;
	private final LicenseService licenseService;

	
	@Inject
	public DatasourceParameterServiceImpl(
		SimpleDataSupplier simpleDataSupplyer,
		ParameterSetFactory parameterSetFactory,
		GroovyScriptingService scriptEngine, 
		LicenseService licenseService,
		ConfigService configService
		){
		
		/* store objects */
		this.simpleDataSupplyer = simpleDataSupplyer;
		this.parameterSetFactory = parameterSetFactory;
		this.scriptEngine = scriptEngine;
		this.licenseService = licenseService;
		this.configService = configService;
		
	}
	
	@Override
	public List<DatasourceParameterData> getParameterData(
			DatasourceParameterDefinition parameter, Report report) throws ReportExecutorException {
		ParameterSet parameterSet = parameterSetFactory.create(report);
		
		for(ParameterDefinition dependee : (List<ParameterDefinition>) parameter.getAllDependents())
			parameterSet.add(dependee.createParameterInstance());
		
		return getParameterData(parameter, parameterSet);
	}
	
	@Override
	@SimpleQuery(from=DatasourceParameterDefinition.class, join=@Join(joinAttribute=DatasourceParameterDefinition__.datasourceContainer, where=@Predicate(attribute=DatasourceContainer__.datasource,value="ds")))
	public List<DatasourceParameterDefinition> getParametersWithDatasource(@Named("ds") DatasourceDefinition ds) {
		return null; // by magic
	}
	
	@Override
	public List<DatasourceParameterData> getParameterData(
			final DatasourceParameterDefinition parameter, ParameterSet parameterSet) throws ReportExecutorException {
		final Collection<DatasourceParameterData> dataList = new LinkedHashSet<DatasourceParameterData>();

		final GroovyScript script = ! isAllowDatasourceParameterPostProcessing() || StringUtils.isBlank(parameter.getPostProcess()) ? null : scriptEngine.compile(parameter.getPostProcess());
		
		try{
			DataConsumer consumer = new DataConsumer(){
				
				private int cnt = 0;
				
				@Override
				public void consumeRow(Object[] row) {
					if(null != script){
						try{
							script.newBinding();
							script.setVariable("data", row);
							script.setVariable("cnt", cnt++);
							
							Object data = scriptEngine.evaluate(script);
							if(null == data)
								return;

							if(data instanceof Collection && ((List)data).isEmpty())
								return;
							
							if(data instanceof List && ! ((List)data).isEmpty()){  
								for(Object entry : ((List)data))
									if(entry instanceof List || entry instanceof Object[])
										add(entry);
									else
										add(new Object[]{entry});
								return;
							} else
								add(data);
						} catch(RuntimeException e){
							throw new ReportExecutorRuntimeException(BaseParameterMessages.INSTANCE.scriptPostProcessingFailed() + e.getMessage() + " (" + e.getClass().getName() + ") ",e);
						}
					} else 
						add(row);
				}
				
				private void add(Object data) {
					if(data instanceof List)
						data = ((List)data).toArray();
					if(! (data instanceof Object[]) || ((Object[])data).length < 1)
						throw new IllegalArgumentException("Expected array");
					add((Object[]) data);
				}

				private void add(Object[] row) {
					DatasourceParameterData dataObject = new DatasourceParameterData();
					dataObject.setValue(String.valueOf(row[0]));
					
					if(row.length == 1)
						dataObject.setKey(String.valueOf(row[0]));
					else
						dataObject.setKey(String.valueOf(row[1]));
					
					/* we are fine .. add and continue */
					dataList.add(dataObject);
				}

				@Override
				public void allConsumed() {
				}
				
			};
			
			/* only do postprocessing if no datasource is given */
			if(null == parameter.getDatasourceContainer() || null == parameter.getDatasourceContainer().getDatasource()){
				consumer.consumeRow(null);
			} else {
				simpleDataSupplyer.getData(parameter, parameterSet, null, null, new TableDatasourceConfig() {
					@Override
					public Integer getQueryTimeout() {
						return DatasourceParameterServiceImpl.this.getQueryTimeout();
					}
				}, consumer);
			}
		} catch (RuntimeException e){
			throw new ReportExecutorException(e);
		}
		
		return new ArrayList<DatasourceParameterData>(dataList);
	}

	private int getQueryTimeout() {
		return configService.getConfigFailsafe(CONFIG_FILE).getInt(PARAMETER_QUERY_TIMEOUT, 10);
	}
	
	public boolean isAllowDatasourceParameterPostProcessing() {
		return licenseService.isEnterprise() && configService.getConfigFailsafe(CONFIG_FILE).getBoolean(PARAMETER_POSTPROCESSING_ENABLE, false);
	}

}
