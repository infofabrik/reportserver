package net.datenwerke.rs.base.service.dbhelper;

import java.sql.Connection;
import java.util.Set;


/**
 * 
 *
 */
public interface DBHelperService {

	/**
	 * Returns all defined database helpers
	 * 
	 * @return A {@link Set} of {@link DatabaseHelper}s
	 */
	public Set<DatabaseHelper> getDatabaseHelpers();
	
	/**
	 * Returns the associated database helper.
	 * 
	 * @param descriptor The descriptor for the database
	 * @return The associated {@link DatabaseHelper}
	 */
	public DatabaseHelper getDatabaseHelper(String descriptor);
	
	public DatabaseHelper getDatabaseHelper(Connection conn);
}
