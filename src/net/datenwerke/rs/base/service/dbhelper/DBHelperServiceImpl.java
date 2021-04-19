package net.datenwerke.rs.base.service.dbhelper;

import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowPredicate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * 
 *
 */
@Singleton
public class DBHelperServiceImpl implements DBHelperService {

	final private Provider<Set<DatabaseHelper>> helperProvider;
	
	@Inject
	public DBHelperServiceImpl(
		Provider<Set<DatabaseHelper>> helperProvider
		){
		this.helperProvider = helperProvider;
	}
	
	public DatabaseHelper getDatabaseHelper(final String descriptor) {
		if(null == descriptor)
			return null;
		
		return helperProvider.get()
			.stream()
			.filter(helper -> descriptor.toLowerCase().equals(helper.getDescriptor().toLowerCase()))
			.findAny()
			.orElse(null);
	}

	public Set<DatabaseHelper> getDatabaseHelpers() {
		return helperProvider.get();
	}

	@Override
	public DatabaseHelper getDatabaseHelper(final Connection conn) {
		if(null == conn)
			return null;
		
		try {
			return helperProvider.get()
				.stream()
				.filter(rethrowPredicate(
						helper -> conn.getMetaData().getDatabaseProductName().toLowerCase().startsWith((helper.getName().toLowerCase()))))
				.findAny()
				.orElse(null);
		} catch (SQLException e) {
		}
		
		return null;
	}

}
