package net.datenwerke.rs.reportdoc.service;

import com.google.inject.Singleton;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.reportdoc.client.ReportDocumentationUIModule;

/**
 * 
 * <h1>Content</h1>
 * <h2>Services:</h2>
 * <ul>
 * <li>{@link ReportDocumentationService}</li>
 * </ul>
 * 
 * <h2>Entities:</h2>
 * <ul>
 * </ul>
 * 
 * 
 * <h2>ClientModule:</h2>
 * <ul>
 * <li>{@link ReportDocumentationUIModule}</li>
 * </ul>
 * 
 * 
 * <h1>Dependencies:</h1>
 * 
 * <h2>Modules:</h2>
 * <ul>
 * <li>{@link net.datenwerke.rs.core.service.guice.AbstractReportServerModule}</li>
 * <li>{@link net.datenwerke.rs.reportdoc.service.ReportDocumentationModule}</li>
 * </ul>
 * 
 * <h2>Entities:</h2>
 * <ul>
 * <li>{@link net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer}</li>
 * <li>{@link net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition}</li>
 * <li>{@link net.datenwerke.rs.core.service.reportmanager.entities.ReportFolder}</li>
 * <li>{@link net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport}</li>
 * <li>{@link net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportJRXMLFile}</li>
 * <li>{@link net.datenwerke.rs.core.service.reportmanager.entities.reports.Report}</li>
 * <li>{@link net.datenwerke.gf.service.properties.entities.Property}</li>
 * </ul>
 * 
 * <h2>Services:</h2>
 * <ul>
 * <li>{@link net.datenwerke.rs.core.service.reportmanager.ReportService}</li>
 * <li>{@link net.datenwerke.rs.base.service.reportengines.jasper.util.JasperUtilsService}</li>
 * <li>{@link net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService}</li>
 * </ul>
 * 
 * <h2>Others:</h2>
 * <ul>
 * <li>{@link net.datenwerke.rs.core.service.parameters.entities.Datatype}</li>
 * <li>{@link net.datenwerke.rs.base.service.parameters.string.TextParameterDefinition}</li>
 * <li>{@link org.apache.commons.io.IOUtils}</li>
 * <li>{@link com.google.inject.Inject}</li>
 * <li>{@link com.google.inject.Provides}</li>
 * <li>{@link com.google.inject.Singleton}</li>
 * <li>{@link com.google.inject.Provider}</li>
 * <li>{@link com.google.inject.BindingAnnotation}</li>
 * </ul>
 */
public class ReportDocumentationModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(ReportDocumentationStartup.class).asEagerSingleton();

      bind(ReportDocumentationService.class).to(ReportDocumentationServiceImpl.class).in(Singleton.class);
   }

}
