package net.datenwerke.rs.reportdoc.service;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportserver.ServerInfoContainer;
import net.datenwerke.rs.reportdoc.client.ReportDocumentationUIModule;
import net.datenwerke.rs.reportdoc.service.helper.ReportDocGroovyHelper;
import net.datenwerke.rs.reportdoc.service.helper.ReportDocPdfRenderer;
import net.datenwerke.rs.scripting.service.scripting.ScriptingModule;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
@Singleton
public class ReportDocumentationServiceImpl implements ReportDocumentationService {



	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private static final String GROOVY = "groovy";

	private final static String BASE_DOCUMENTATION_LOCATION = "reports/report_documentation.groovy";
	private final static String REVISION_DOCUMENTATION_LOCATION = "reports/report_documentation_rev.groovy";
	private final static String REVISIONS_LOCATION = "reports/revisions.groovy";
	private final static String DEPLOY_ANALYZE_DOCUMENTATION_LOCATION = "reports/report_documentation_deploy_analyze.groovy";
	private final static String VARIANT_TEST_DOCUMENTATION_LOCATION = "reports/report_variant_tester.groovy";
	
	private final Provider<ReportDocGroovyHelper> groovyHelperProvider;
	private final Provider<ReportDocPdfRenderer> pdfRendererProvider;
	private final Provider<ServerInfoContainer> serverInfoProvider; 
	private final Provider<AuthenticatorService> authenticatorServiceProvider; 
	
	private CompiledScript baseDocumentation;
	private CompiledScript revisions;
	private CompiledScript revDocumentation;
	private CompiledScript deployAnalyzeDocumentation;
	
	@Inject
	public ReportDocumentationServiceImpl(
			Provider<ReportDocGroovyHelper> groovyHelperProvider,
			Provider<ReportDocPdfRenderer> pdfRendererProvider,
			Provider<ServerInfoContainer> serverInfoProvider,
			Provider<AuthenticatorService> authenticatorServiceProvider
		){
			this.groovyHelperProvider = groovyHelperProvider;
			this.pdfRendererProvider = pdfRendererProvider;
			this.serverInfoProvider = serverInfoProvider;
			this.authenticatorServiceProvider = authenticatorServiceProvider;
		
	}

	@Override
	public CompiledReport executeDocumentation(Report report, Long tsId, String format) throws ScriptException {
		CompiledScript compiled = getCompiledDocumentation();
		
		Map<String, Object> binding = new HashMap<String,Object>();
		binding.put("outputFormat", null != format ? format.toLowerCase() : "html");
		
		/* simulate parameter map */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		binding.put("parameterMap", paramMap);
		
		paramMap.put("reportId", report.getId());
		paramMap.put("tsObjectId", tsId);
		
		Object result = execute(compiled, binding);
		
		return (CompiledReport) result;
	}
	
	@Override
	public CompiledReport executeDocumentationDeployAnalyze(Report leftReport, Report rightReport, String format, boolean ignoreCase) throws ScriptException {
		CompiledScript compiled = getCompiledDocumentationDeployAnalyze();
		
		Map<String, Object> binding = new HashMap<String,Object>();
		binding.put("outputFormat", null != format ? format.toLowerCase() : "html");
		
		/* simulate parameter map */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		binding.put("parameterMap", paramMap);
		
		paramMap.put(ReportDocumentationUIModule.LEFT_REPORT_PROPERTY_ID, leftReport.getId());
		paramMap.put(ReportDocumentationUIModule.RIGHT_REPORT_PROPERTY_ID, rightReport.getId());
		paramMap.put(ReportDocumentationUIModule.IGNORE_CASE_PROPERTY_ID, ignoreCase);
		
		Object result = execute(compiled, binding);
		
		return (CompiledReport) result;
	}
	
	@Override
	public CompiledReport executeDocumentationVariantTest(Report report, List<DatasourceDefinition> datasources, String format) throws ScriptException {
		CompiledScript compiled = getCompiledDocumentationVariantTest();
		
		Map<String, Object> binding = new HashMap<>();
		binding.put("outputFormat", null != format ? format.toLowerCase() : "html");
		
		/* simulate parameter map */
		Map<String, Object> paramMap = new HashMap<>();
		binding.put("parameterMap", paramMap);
		
		paramMap.put(ReportDocumentationUIModule.REPORT_PROPERTY_ID, report.getId());
		
		List<Long> datasourceIds = datasources
			.stream()
			.map(DatasourceDefinition::getId)
			.collect(toList());
		
		paramMap.put(ReportDocumentationUIModule.DATASOURCE_PROPERTY_ID, datasourceIds);
		
		Object result = execute(compiled, binding);
		
		return (CompiledReport) result;
	}
	
	@Override
	public CompiledReport executeDocumentationRev(Report report, Long revision, String format) throws ScriptException {
		CompiledScript compiled = getCompiledDocumentationRev();
		
		Map<String, Object> binding = new HashMap<String,Object>();
		binding.put("outputFormat", null != format ? format.toLowerCase() : "html");
		
		/* simulate parameter map */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		binding.put("parameterMap", paramMap);
		
		paramMap.put("reportId", report.getId());
		paramMap.put("revId", revision);
		
		Object result = execute(compiled, binding);
		
		return (CompiledReport) result;
	}
	
	@Override
	public CompiledReport executeRevisions(Report report, String format) throws ScriptException {
		CompiledScript compiled = getCompiledRevisions();
		
		Map<String, Object> binding = new HashMap<String,Object>();
		binding.put("outputFormat", null != format ? format.toLowerCase() : "html");
		
		/* simulate parameter map */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		binding.put("parameterMap", paramMap);
		
		paramMap.put("reportId", report.getId());

		Object result = execute(compiled, binding);
		
		return (CompiledReport) result;
	}

	protected Object execute(CompiledScript script, Map<String, Object> bindingMap) throws ScriptException {
		SimpleBindings binding = new SimpleBindings(bindingMap);
		binding.put("GLOBALS", groovyHelperProvider.get());
		
		Map<String,Object> rendererMap = new HashMap<String,Object>();
		rendererMap.put("pdf", pdfRendererProvider.get());
		binding.put("renderer", rendererMap);
		
		/* base url */
		String baseUrl = "";
		try {
			baseUrl = serverInfoProvider.get().getBaseURL() + "/";
		} catch(Exception e){}
		binding.put("baseurl", baseUrl);
		
		/* user */
		try{
			User currentUser = authenticatorServiceProvider.get().getCurrentUser();
			binding.put("user", currentUser);
		}catch(Exception e){}
		
		return script.eval(binding);
	}
	
	protected CompiledScript getCompiledRevisions() {
		if(null != revisions)
			return revisions;
		
		try {
			revisions = compile(REVISIONS_LOCATION);
		} catch (Exception e) {
			logger.warn("Could not load report revisions", e);
			throw new IllegalStateException(e);
		}
			
		return revisions;
	}

	protected synchronized CompiledScript getCompiledDocumentation() {
		if(null != baseDocumentation)
			return baseDocumentation;
		
		try {
			baseDocumentation = compile(BASE_DOCUMENTATION_LOCATION);
		} catch (Exception e) {
			logger.warn("Could not load report documentation", e);
			throw new IllegalStateException(e);
		}
			
		return baseDocumentation;
	}
	
	protected synchronized CompiledScript getCompiledDocumentationDeployAnalyze() {
		if(null != deployAnalyzeDocumentation)
			return deployAnalyzeDocumentation;
		
		try {
			deployAnalyzeDocumentation = compile(DEPLOY_ANALYZE_DOCUMENTATION_LOCATION);
		} catch (Exception e) {
			logger.warn("Could not load report deploy analyze documentation", e);
			throw new IllegalStateException(e);
		}
			
		return deployAnalyzeDocumentation;
	}
	
	protected synchronized CompiledScript getCompiledDocumentationVariantTest() {
		if(null != deployAnalyzeDocumentation)
			return deployAnalyzeDocumentation;
		
		try {
			deployAnalyzeDocumentation = compile(VARIANT_TEST_DOCUMENTATION_LOCATION);
		} catch (Exception e) {
			logger.warn("Could not load report variant test documentation", e);
			throw new IllegalStateException(e);
		}
			
		return deployAnalyzeDocumentation;
	}
	
	protected synchronized CompiledScript getCompiledDocumentationRev() {
		if(null != revDocumentation)
			return revDocumentation;
		
		try {
			revDocumentation = compile(REVISION_DOCUMENTATION_LOCATION);
		} catch (Exception e) {
			logger.warn("Could not load report documentation", e);
			throw new IllegalStateException(e);
		}
			
		return revDocumentation;
	}

	protected CompiledScript compile(String location) throws IOException, ScriptException {
		String report = IOUtils.toString(getClass().getResourceAsStream(ScriptingModule.RESOURCES_DIR + location));
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine groovy = manager.getEngineByName(GROOVY);
		Compilable compGroovy = (Compilable) groovy;
		return compGroovy.compile(report);
	}
	
	

}
