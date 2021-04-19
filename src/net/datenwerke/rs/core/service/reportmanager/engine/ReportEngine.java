package net.datenwerke.rs.core.service.reportmanager.engine;

import java.io.OutputStream;
import java.util.UUID;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.datasources.transformers.DatasourceTransformationService;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledReportMetadata;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportExecutorToken;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECSetExecutionUUID;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.engine.events.BeginReportExecutionEvent;
import net.datenwerke.rs.core.service.reportmanager.engine.events.EndReportExecutionEvent;
import net.datenwerke.rs.core.service.reportmanager.engine.hooks.ReportEngineTakeOverExecutionHook;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.UnsupportedFormatException;
import net.datenwerke.rs.core.service.reportmanager.metadata.AbstractReportMetadataExporterManager;
import net.datenwerke.rs.core.service.reportmanager.metadata.ReportMetadataExporter;
import net.datenwerke.rs.core.service.reportmanager.output.AbstractReportOutputGeneratorManager;
import net.datenwerke.rs.core.service.reportmanager.output.ReportOutputGenerator;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.usermanager.entities.User;

import org.apache.commons.lang.ArrayUtils;

import com.google.inject.Inject;


/**
 * 
 *
 */
public abstract class ReportEngine<D, G extends ReportOutputGenerator, E extends ReportMetadataExporter> {

	final protected AbstractReportOutputGeneratorManager<G> outputGeneratorManager;
	final protected AbstractReportMetadataExporterManager<E> metadataExporterManager;

	protected HookHandlerService hookHandler;
	protected EventBus eventBus;
	private DatasourceTransformationService datasourceTransformationService;
	
	@Inject
	protected ParameterSetFactory parameterSetFactory;
	
	public ReportEngine(
		AbstractReportOutputGeneratorManager<G> outputGeneratorManager,
		AbstractReportMetadataExporterManager<E> metadataExporterManager, 
		DatasourceTransformationService datasourceTransformationService
		){
		
		/* store objects */
		this.outputGeneratorManager = outputGeneratorManager;
		this.metadataExporterManager = metadataExporterManager;
		this.datasourceTransformationService = datasourceTransformationService;
	}
	
	
	@Inject
	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	@Inject
	public void setHookHandler(HookHandlerService hookHandler) {
		this.hookHandler = hookHandler;
	}
	
	/**
	 * Defines which output formats are supported
	 */
	public String[] getSupportedOutputFormats() {
		return outputGeneratorManager.getRegisteredOutputFormats();
	}
	
	public boolean hasCatchAllOutputGen() {
		return outputGeneratorManager.hasCatchAllOutputGen();
	}
	
	public String[] getSupportedMetadataFormats() {
		return metadataExporterManager.getRegisteredExporterFormats();
	}

	public void isExecutable(Report report, ParameterSet additionalParameters, User user, String outputFormat, ReportExecutionConfig... configs) throws ReportExecutorException {
		
	}
	
	/**
	 * Executes a given report.
	 */
	public CompiledReport execute(OutputStream os, Report report, ParameterSet parameterSet, User user, String outputFormat, ReportExecutionConfig... configs) throws ReportExecutorException, ExpectedException{
		
		/* Use supplied uuid or create own */
		String uuid = UUID.randomUUID().toString();
		if(null != configs)
			for(ReportExecutionConfig cfg : configs)
				if(cfg instanceof RECSetExecutionUUID)
					uuid = ((RECSetExecutionUUID) cfg).getUuid();
		
		/* Use supplied executor token or create own */
		String executorToken = null;
		if(null != configs){
			for(ReportExecutionConfig cfg : configs){
				if(cfg instanceof RECReportExecutorToken)
					executorToken = ((RECReportExecutorToken) cfg).getToken();
			}
		}
		if(null == executorToken){
			executorToken = UUID.randomUUID().toString();
			configs = (ReportExecutionConfig[]) ArrayUtils.add(configs, new RECReportExecutorToken(executorToken));
		}
		
		/* fire begin event */
		eventBus.fireEvent(new BeginReportExecutionEvent(report, uuid));
		
		boolean success = false;
		try{
			/* handle config */
			if(null == configs)
				configs = new ReportExecutionConfig[0];
			
			for(ReportEngineTakeOverExecutionHook engineHooker : hookHandler.getHookers(ReportEngineTakeOverExecutionHook.class)){
				if(engineHooker.takesOver(this, report, parameterSet, user, outputFormat, configs)){
					return engineHooker.executeReport(this, os, report, parameterSet, user, outputFormat, configs);
				}
			}
			
			if(! ArrayUtils.contains(getSupportedOutputFormats(), outputFormat) && ! hasCatchAllOutputGen())
				throw new UnsupportedFormatException(getSupportedOutputFormats(), outputFormat);
			
			ParameterSet ps = parameterSetFactory.create(user, report);
			ps.addAll(report.getParameterInstances());
			ps.addVariable("_RS_REX_TOKEN", executorToken);

			if(null != parameterSet){
				ps.add(parameterSet);
			}
		
			CompiledReport result = doExecute(os, report, user, ps, outputFormat, configs);
			success = true;
			
			return result;
		} finally {
			eventBus.fireEvent(new EndReportExecutionEvent(report, uuid, success));
		}
	}
	
	public CompiledReport executeDry(OutputStream os, Report report,
			ParameterSet parameterSet, User user, String outputFormat,
			ReportExecutionConfig[] configs) throws ReportExecutorException, ExpectedException {
		for(ReportEngineTakeOverExecutionHook engineHooker : hookHandler.getHookers(ReportEngineTakeOverExecutionHook.class)){
			if(engineHooker.takesOver(this, report, parameterSet, user, outputFormat, configs)){
				return engineHooker.executeReportDry(this, report, parameterSet, user, outputFormat, configs);
			}
		}
		return outputGeneratorManager.getOutputGenerator(outputFormat).getFormatInfo();
	}
	
	public boolean supportsStreaming(Report report, ParameterSet parameterSet, User user, String outputFormat, ReportExecutionConfig... configs){
		return false;
	}
	
	/**
	 * Executes a given report
	 */
	protected abstract CompiledReport doExecute(OutputStream os, Report report, User user, ParameterSet parameters, String outputFormat, ReportExecutionConfig... configs) throws ReportExecutorException;
	
	
	
	/**
	 * Defines the possible output formats
	 */
	public abstract boolean consumes(Report report);
	
	public boolean hasConfig(Class<? extends ReportExecutionConfig> type, ReportExecutionConfig... configs){
		return null != getConfig(type, configs);
	}
	
	public <C extends ReportExecutionConfig> C getConfig(Class<C> type, ReportExecutionConfig... configs){
		if(null == configs || configs.length == 0)
			return null;
		
		for(ReportExecutionConfig config : configs)
			if(type.isAssignableFrom(config.getClass()))
				return (C) config;
			
		return null;
	}
	
	public CompiledReportMetadata exportMetadata(Report report, ParameterSet additionalParameters, User user, String outputFormat) throws ReportExecutorException{
		if(! ArrayUtils.contains(getSupportedMetadataFormats(), outputFormat))
			throw new UnsupportedFormatException(getSupportedMetadataFormats(), outputFormat);
		
		ParameterSet ps = parameterSetFactory.create(user, report);
		ps.addAll(report.getParameterInstances());
		if(null != additionalParameters)
			ps.add(additionalParameters);
		
		E exporter = metadataExporterManager.getMetadataExporter(outputFormat);
		exportBaseMetadata(report, user, ps, exporter);
		exportAdditionalMetadata(report, user, ps, exporter);
		exporter.cleanUp();
		
		return exporter.getMetadata();
	}

	protected void exportBaseMetadata(Report report, User user, ParameterSet ps,
			E exporter) {
		exporter.visitReport(report);
		exporter.visitUser(user);
		
		exporter.beginParameterSection();
		for(ParameterInstance instance : report.getParameterInstances())
			exporter.visitParameter(instance, instance.getDefinition(), user);
	}
	
	protected void exportAdditionalMetadata(Report report, User user, ParameterSet ps, E exporter){
	}


	protected <T> T transformDatasource(Class<T> returnType, Report report, ParameterSet parameters){
		return datasourceTransformationService.transform(returnType, report, parameters);
	}
	
}
