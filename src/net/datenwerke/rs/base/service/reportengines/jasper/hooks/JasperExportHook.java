package net.datenwerke.rs.base.service.reportengines.jasper.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledRSJasperReport;
import net.datenwerke.security.service.usermanager.entities.User;
import net.sf.jasperreports.engine.JRExporter;

/**
 * Interface for the export callbacks.
 * 
 */
public interface JasperExportHook extends Hook {

	/**
	 * Provides a list of supported export formats.
	 * 
	 * @return A string array with each element holding one supported export
	 *         format.
	 */
	public Collection<String> getFormats();

	/**
	 * Method to be called just before exportReport() method.
	 * 
	 * @param type
	 *            The type of the exporter.
	 * @param exporter
	 *            The JRExporter to operate on.
	 */
	public void beforeExport(String type, JRExporter exporter, JasperReport report, User user);

	/**
	 * Method to be called after the call to the exportReport() method.
	 * 
	 * @param type
	 *            The type of the exporter.
	 * @param exporter
	 *            The JRExporter to operate on.
	 */
	public void afterExport(String type, JRExporter exporter, JasperReport report, CompiledRSJasperReport compiledReport, User user);
}
