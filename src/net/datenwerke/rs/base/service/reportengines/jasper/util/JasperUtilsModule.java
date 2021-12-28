package net.datenwerke.rs.base.service.reportengines.jasper.util;

import com.google.inject.Scopes;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.utils.xml.XMLUtilsService;

/**
 * The jasper utils module provides some jasper related utility methods (e.g.
 * for converting).
 * 
 * <h1>Description</h1> The Jasper utils provides the user with methods for
 * converting JRXML files and acting on them.
 * 
 * <h1>Content</h1>
 * <h2>Services</h2>
 * <ul>
 * <li>{@link JasperUtilsService}</li>
 * </ul>
 * 
 * <h2>Client Module</h2>
 * <ul>
 * <li>{@link net.datenwerke.rs.incubator.client.jasperutils.JasperUIModule}</li>
 * </ul>
 * 
 * <h2>Singletons</h2>
 * <ul>
 * <li>{@link JasperUtilsService}</li>
 * </ul>
 * 
 * <h1>Dependencies</h1>
 * 
 * <h2>Services</h2>
 * <ul>
 * <li>{@link XMLUtilsService}</li>
 * </ul>
 * 
 * <h2>3rd-Party</h2>
 * <ul>
 * <li><a href="http://code.google.com/p/google-guice/">Google Guice</a></li>
 * </ul>
 */
public class JasperUtilsModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(JasperUtilsService.class).to(JasperUtilsServiceImpl.class).in(Scopes.SINGLETON);
   }

}
