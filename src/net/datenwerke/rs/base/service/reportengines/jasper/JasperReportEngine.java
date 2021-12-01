package net.datenwerke.rs.base.service.reportengines.jasper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.rs.base.service.datasources.transformers.DatasourceTransformationService;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportJRXMLFile;
import net.datenwerke.rs.base.service.reportengines.jasper.hooks.JasperReportPostCompileHook;
import net.datenwerke.rs.base.service.reportengines.jasper.output.generator.JasperOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.jasper.output.generator.JasperOutputGeneratorManager;
import net.datenwerke.rs.base.service.reportengines.jasper.output.metadata.JasperMetadataExporter;
import net.datenwerke.rs.base.service.reportengines.jasper.output.metadata.JasperMetadataExporterManager;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledRSJasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.util.JasperUtilsService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.security.service.usermanager.entities.User;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRClassLoader;
import net.sf.jasperreports.engine.util.ProtectionDomainFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.extensions.ExtensionsEnvironment;
import net.sf.jasperreports.extensions.ExtensionsRegistry;
import net.sf.jasperreports.repo.DefaultRepositoryService;
import net.sf.jasperreports.repo.RepositoryService;

/**
 * 
 *
 */
@Singleton
public class JasperReportEngine extends ReportEngine<JRDataSource, JasperOutputGenerator, JasperMetadataExporter> {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	public static final String OUTPUT_FORMAT_TABLE_EXCEL = "TABLE_EXCEL"; //$NON-NLS-1$

	private final JasperUtilsService jasperUtilsService;


	@Inject
	public JasperReportEngine(
			JasperOutputGeneratorManager outputGeneratorManager,
			JasperMetadataExporterManager metadataExporterManager,
			JasperUtilsService jasperUtilsService, 
			DatasourceTransformationService datasourceTransformationService
			){
		super(outputGeneratorManager, metadataExporterManager, datasourceTransformationService);
		this.jasperUtilsService = jasperUtilsService;
		
	}

	@Override
	public boolean consumes(Report report) {
		return report instanceof JasperReport;
	}

	@Override
	protected CompiledReport doExecute(OutputStream os, Report report, User user, ParameterSet parameters, String outputFormat, ReportExecutionConfig... configs) throws ReportExecutorException {
		if(! jasperUtilsService.isJasperEnabled())
			throw new ReportExecutorException("JasperReports is disabled");
		
		/* validate arguments and cast them */
		if(!(report instanceof JasperReport))
			throw new IllegalArgumentException("Need a report of type JasperReport."); //$NON-NLS-1$
		JasperReport jReport = (JasperReport) report;
		
		if(null == jReport.getMasterFile()){
			throw new IllegalArgumentException("No file has been uploaded.");
		}
		
		JRDataSource ds = transformDatasource(JRDataSource.class, jReport, parameters);
		
		Connection con = transformDatasource(Connection.class, report, parameters);
		
		try{
			CompiledReport result = executeReport(con, ds, jReport, parameters, outputFormat, user, configs);
			return result;
		} finally {
			try {
				if(ds instanceof JasperDBDataSource){
					if(! ((JasperDBDataSource)ds).getConnection().isClosed())
						((JasperDBDataSource)ds).getConnection().close();
				}
				if (null != con && ! con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				logger.info( "Could not close connection after jasper execution", e);
			}
		}

	}
	
	private CompiledRSJasperReport executeReport(Connection connection, JRDataSource datasource, final JasperReport report, ParameterSet parameters, String outputFormat,  User user, ReportExecutionConfig... configs) {
		CompiledRSJasperReport resultObject = null;		
		
		/* get output format generator */
		JasperOutputGenerator outputGenerator = outputGeneratorManager.getOutputGenerator(outputFormat);
		if(null == outputGenerator)
			throw new IllegalArgumentException("Could not find output generator for format: " + outputFormat + ". This is very strange and probably a bug in ReportServer."); //$NON-NLS-1$ //$NON-NLS-2$

		/* get jasperdesign */
		ByteArrayInputStream is;
		try {
			is = new ByteArrayInputStream(report.getMasterFile().getContent().getBytes("UTF-8")); //$NON-NLS-1$
		} catch (UnsupportedEncodingException e1) {
			IllegalStateException ise = new IllegalStateException("Could not load JRXML as UTF-8: " + e1.getMessage()); //$NON-NLS-1$
			ise.initCause(e1);
			throw ise;
		}
		try {
			JasperDesign jasperDesign;
			jasperDesign = JRXmlLoader.load(is);
			
			/* adjust design? */
			outputGenerator.adjustDesign(jasperDesign, outputFormat, configs);

			/* get parameter map */
			Map<String, Object> parameterMap = parameters.getParameterMapSimple();

			/* final context */
			final DefaultJasperReportsContext context = DefaultJasperReportsContext.getInstance();
			
			/* prepare rsfs url handler */
			final URLStreamHandlerFactory urlStreamHandlerFactory = new URLStreamHandlerFactory() {

				@Override
				public URLStreamHandler createURLStreamHandler(String protocol) {
					if(!"rsfsx".equals(protocol))
						return null;
					
					return new URLStreamHandler() {
						@Override
						protected URLConnection openConnection(URL u) throws IOException {
							String subreport = u.getPath().substring(u.getPath().lastIndexOf("/") + 1);
							subreport = subreport.replaceFirst("(?i)\\.jrxml$", "");
							
							if(null != report.getSubFiles()){
								for(JasperReportJRXMLFile jrxml : report.getSubFiles()){
									if(jrxml.getName().replaceFirst("(?i)\\.jrxml$", "").equals(subreport)){
										try {
											final ByteArrayOutputStream bos = new ByteArrayOutputStream();
											
											JasperDesign design = JRXmlLoader.load(new ByteArrayInputStream(jrxml.getContent().getBytes()));
											validate(design);
											
											JasperCompileManager.getInstance(context).compileToStream(design, bos);
											return new URLConnection(u) {

												@Override
												public InputStream getInputStream() throws IOException {
													if(!connected)
														connect();
													return new ByteArrayInputStream(bos.toByteArray());
												}

												@Override
												public int getContentLength() {
													return bos.size();
												}

												@Override
												public String getContentType() {
													return "application/octet-stream";
												}

												@Override
												public long getLastModified() {
													return Calendar.getInstance().getTimeInMillis();
												}

												@Override
												public void connect() throws IOException {
												}
											};
										} catch (JRException e) {
											throw new IllegalArgumentException("Problem with subreport " + jrxml.getName() + ": " + e);
										}
									}
								}
							}
							throw new IllegalArgumentException("No subreport of name " + u +  " found.");
						}

					};
				}
			};
			
			final ClassLoader classLoader = new ClassLoader() {
				public java.net.URL getResource(String name) {
					URL classloaderResource = net.sf.jasperreports.engine.JasperReport.class.getClassLoader().getResource(name);
					if(null != classloaderResource)
						return classloaderResource;
					
					if(!isUrl(name)){
						try {
							if(name.lastIndexOf("/") > -1){
								name = name.substring(name.lastIndexOf("/") + 1);
							}
							if(name.lastIndexOf("\\") > -1){
								name = name.substring(name.lastIndexOf("\\") + 1);
							}
							if(name.lastIndexOf(".") > -1){
								name = name.substring(0, name.lastIndexOf("."));
							}
							
							return new URL("rsfsx", "jaspersubreport", -1, "/" + report.getUuid() + "/" + name, urlStreamHandlerFactory.createURLStreamHandler("rsfsx"));
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
					}
					return null;
				};
			};
			
			final ExtensionsRegistry extensionsRegistry = ExtensionsEnvironment.getExtensionsRegistry();
			ExtensionsEnvironment.setThreadExtensionsRegistry(new ExtensionsRegistry() {
				@Override
				public <T> List<T> getExtensions(Class<T> extensionType) {
					List<T> extensions = extensionsRegistry.getExtensions(extensionType);
					if(RepositoryService.class.equals(extensionType)){
						for(T repoSvc : extensions){
							if(repoSvc instanceof DefaultRepositoryService){
								((DefaultRepositoryService)repoSvc).setURLStreamHandlerFactory(urlStreamHandlerFactory);
								((DefaultRepositoryService)repoSvc).setClassLoader(classLoader);
							}
						}
					}

					return extensions; 
				}
			});

			/* set protection domain */
			JRClassLoader.setProtectionDomainFactory(new ProtectionDomainFactory() {
				@Override
				public ProtectionDomain getProtectionDomain(ClassLoader classloader) {
					try{
						CodeSource cs = new CodeSource(new URL("file://jasperreports/defaultcodesource"), (java.security.cert.Certificate[]) null);
						ProtectionDomain pd = new ProtectionDomain(cs, null, classloader, null);
						return pd;
					} catch(Exception e){
						throw new IllegalStateException(e);
					}
				}
			});
			
			/* validate design */
			validate(jasperDesign);

			/* compile report */
			net.sf.jasperreports.engine.JasperReport jasperReport = JasperCompileManager.getInstance(context).compile(jasperDesign);
			
			/* JasperReportPostprocessHook */
			for(JasperReportPostCompileHook hooker: hookHandler.getHookers(JasperReportPostCompileHook.class)){
				hooker.postprocessReport(jasperReport);
			}

			/* get jasper print */
			JasperPrint jasperPrint = JasperFillManager.getInstance(context).fill(jasperReport, parameterMap, connection);
			
//			if(datasource instanceof JasperDBDataSource){
//				jasperPrint = JasperFillManager.getInstance(context).fill(jasperReport, parameterMap, ((JasperDBDataSource) datasource).getConnection());
//			}else{
//				jasperPrint = JasperFillManager.getInstance(context).fill(jasperReport, parameterMap, datasource);
//			}
			/* export to specified format */
			resultObject = outputGenerator.exportReport(jasperPrint, outputFormat, report, user, configs);
		} catch (JRException e) {
			IllegalArgumentException iae = new IllegalArgumentException("Could not execute report: " + e.getMessage()); //$NON-NLS-1$
			iae.initCause(e);
			throw iae;
		}

		return resultObject;
	}
	
	private boolean isUrl(String url){
		try{
			URL u = new URL(url);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	private void validate(JasperDesign design) {
		List<String> jasperAllowedLanguages = jasperUtilsService.getJasperAllowedLanguages();
		if(null == jasperAllowedLanguages || jasperAllowedLanguages.isEmpty())
			return;
	}
}
