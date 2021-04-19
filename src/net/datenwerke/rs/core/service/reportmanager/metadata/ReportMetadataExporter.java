package net.datenwerke.rs.core.service.reportmanager.metadata;

import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledReportMetadata;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
public interface ReportMetadataExporter {
	
	/**
	 * 
	 * @return The generator's format
	 */
	public String[] getFormats();
	
	/**
	 * Should be used to collect basic information such as name, etc.
	 */
	public void visitReport(Report report);
	
	/**
	 * The user the report "was" created with
	 */
	public void visitUser(User user);
	
	public void beginParameterSection();
	
	public void visitParameter(ParameterInstance instance, ParameterDefinition definition, User user);
	
	public CompiledReportMetadata getMetadata();
	
	public void cleanUp();
}
