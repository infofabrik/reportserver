package net.datenwerke.rs.incubator.service.exportmetadata;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.service.usermanager.entities.User;

public interface ExportMetadataService {

	/**
	 * Returns the title of the given {@link Report}
	 * 
	 * @param report The {@link Report} to get the title from
	 * @return The title of the given report
	 */
	public abstract String getTitle(Report report, User user);

	/**
	 * Returns the creator of the given {@link Report}
	 * 
	 * @param report The {@link Report} to get the creator from
	 * @return The creator of the given report
	 */
	public abstract String getCreator(Report report, User user);

	/**
	 * Returns the author of the given {@link Report}
	 * 
	 * @param report The {@link Report} to get the author from
	 * @return The author of the given report
	 */
	public abstract String getAuthor(Report report, User user);

	/**
	 * Returns the configured title of the to be generated PDF file.
	 * 
	 * @return The configured title of the to be generated PDF file
	 */
	public abstract String getTitle();

	/**
	 * Returns the configured creator of the to be generated PDF file.
	 * 
	 * @return The configured creator of the to be generated PDF file
	 */
	public abstract String getCreator();

	/**
	 * Returns the configured author of the to be generated PDF file.
	 * 
	 * @return The configured author of the to be generated PDF file
	 */
	public abstract String getAuthor();

}
