package net.datenwerke.rs.birt.service.reportengine.sandbox;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import javax.annotation.Nullable;
import javax.inject.Inject;

import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterData;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterInstance;
import net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterDefinition;
import net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterInstance;
import net.datenwerke.rs.birt.service.reportengine.BirtHelper;
import net.datenwerke.rs.birt.service.reportengine.BirtReportEngine;
import net.datenwerke.rs.birt.service.reportengine.output.generator.BirtOutputGenerator;
import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledRSBirtReport;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.assistedinject.Assisted;

public class BirtEngineEnvironment implements Callable<CompiledRSBirtReport> {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private final IReportEngine reportEngine;
	private final byte[] reportBytes;
	
	private final Connection connection;
	private final String outputFormat;
	private final ReportExecutionConfig[] configs;
	private final BirtOutputGenerator outputGenerator;

	private ParameterSet parameters;

	private BirtHelper birtHelper;

	@Inject
	public BirtEngineEnvironment(
			@Assisted IReportEngine reportEngine,
			@Assisted byte[] reportBytes,
			@Assisted ParameterSet parameters,
			@Assisted @Nullable Connection connection,
			@Assisted String outputFormat,
			@Assisted BirtOutputGenerator outputGenerator,
			@Assisted ReportExecutionConfig[] configs,
			
			BirtHelper birtHelper
			) {
		this.reportEngine = reportEngine;
		this.reportBytes = reportBytes;
		this.parameters = parameters;
		
		this.connection = connection;
		this.outputFormat = outputFormat;
		this.outputGenerator = outputGenerator;
		this.configs = configs;
		this.birtHelper = birtHelper;
	}
	
	@Override
	public CompiledRSBirtReport call() throws Exception {
		try{
			ByteArrayInputStream bis = new ByteArrayInputStream(reportBytes);
			
			IReportRunnable reportDesign = reportEngine.openReportDesign(bis);

			IRunAndRenderTask runAndRenderTask = reportEngine.createRunAndRenderTask(reportDesign);
			
			runAndRenderTask.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, BirtReportEngine.class.getClassLoader());
			ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
			try{
				Thread.currentThread().setContextClassLoader(BirtReportEngine.class.getClassLoader());

				/* create map of parameter instances */
				Map<String, ParameterInstance<?>> parameterInstances = new HashMap<String, ParameterInstance<?>>();
				for(ParameterInstance<?> pi : parameters.getParameterList()){
					parameterInstances.put(pi.getKey(), pi);
				}
				
				
				/* prepare parameters with special classes */
				Map<String, Object> parameterMap = parameters.getParameterMapSimple();
				for(ParameterInstance pi : parameters.getParameterList()){
					if(pi instanceof DateTimeParameterInstance){
						DateTimeParameterDefinition def = (DateTimeParameterDefinition) pi.getDefinition();
						switch(def.getMode()){
						case Date:{
							Date paraVal = (Date) parameterMap.get(pi.getKey());
							java.sql.Date sqlDate = (null==paraVal)?null:new java.sql.Date(paraVal.getTime()); 
							parameterMap.put(pi.getKey(), sqlDate);
							} break;
						case Time:{
							Date paraVal = (Date) parameterMap.get(pi.getKey());
							java.sql.Time sqlTime = (null==paraVal)?null:new java.sql.Time(paraVal.getTime()); 
							parameterMap.put(pi.getKey(), sqlTime);
							} break;
						case DateTime:
							break;
						}
					}
				}
				
				// Set parameter values and validate
				for(Entry<String, Object> parameterEntry : parameterMap.entrySet()){
					String parameterName = parameterEntry.getKey();
					Object parameterValue = parameterEntry.getValue();
					ParameterInstance<?> parameterInstance = parameterInstances.get(parameterName);
					
					/* As birt other than jasper does not handle multiselect parameters as List, but 
					 * expects Object[] we need to convert them */
					if(parameterValue instanceof List){
						runAndRenderTask.setParameterValue(parameterName, ((List)parameterValue).toArray());
						
						if(null != parameterInstance && parameterInstance instanceof DatasourceParameterInstance){
							
							List<DatasourceParameterData> selectedValue;
							if(parameterInstance.getDefinition().isEditable() && !parameterInstance.isStillDefault()){
								selectedValue = ((DatasourceParameterInstance)parameterInstance).getMultiValue();
							}else{
								selectedValue = ((DatasourceParameterInstance)parameterInstance).getMultiDefaultValue(parameters);
							}
							
							if(null != selectedValue){
								ArrayList<String> keys = new ArrayList<String>(selectedValue.size());
								for(DatasourceParameterData dpd : selectedValue){
									keys.add(dpd.getKey());
								}

								runAndRenderTask.setParameterDisplayText(parameterName, keys.toArray(new String[0]));
							}
						}
						
					}else{
						runAndRenderTask.setParameterValue(parameterName, parameterValue);
						
						if(null != parameterInstance && parameterInstance instanceof DatasourceParameterInstance){
							DatasourceParameterData selectedValue;
							if(parameterInstance.getDefinition().isEditable() && !parameterInstance.isStillDefault()){
								selectedValue = ((DatasourceParameterInstance)parameterInstance).getSingleValue();
							}else{
								selectedValue = ((DatasourceParameterInstance)parameterInstance).getSingleDefaultValue(parameters);
							}
							if(null != selectedValue){
								runAndRenderTask.setParameterDisplayText(parameterName, selectedValue.getKey());
							}
						}
					}
				}
				
				runAndRenderTask.validateParameters();
	
				/* set locale */
				birtHelper.setLocale(parameterMap, runAndRenderTask);
				
				//put in datasource
				if(null != connection)
					runAndRenderTask.getAppContext().put("OdaJDBCDriverPassInConnection", connection);
	
				CompiledRSBirtReport exportReport = outputGenerator.exportReport(runAndRenderTask, outputFormat, configs);
			
				runAndRenderTask.close();
				
				return exportReport;
			} finally {
				Thread.currentThread().setContextClassLoader(contextLoader);
			}
		}catch(BirtException e){
			throw new ReportExecutorRuntimeException(e);
		} finally {
			try {
				if(null != connection && ! connection.isClosed())
					connection.close();
			} catch (SQLException e) {
				logger.info( "Could not close connection after birt report execution", e);
			}
		}
	}

}
