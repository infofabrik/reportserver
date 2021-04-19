package net.datenwerke.rs.base.service.datasources.table.hookers;

import net.datenwerke.rs.base.service.datasources.table.impl.TableDBDataSource;
import net.datenwerke.rs.base.service.datasources.table.impl.hooks.TableDbDatasourceOpenedHook;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class QueryCommentAdderHooker implements TableDbDatasourceOpenedHook {

	private final Provider<AuthenticatorService> authenticatorServiceProvider;
	
	@Inject
	public QueryCommentAdderHooker(
			Provider<AuthenticatorService> authenticatorServiceProvider) {
		this.authenticatorServiceProvider = authenticatorServiceProvider;
	}

	@Override
	public void datasourceOpenend(TableDBDataSource tableDBDataSource,
			String executorToken) {
		try{
			tableDBDataSource.addQueryComment("token: " + String.valueOf(executorToken));
			
			User currentUser = authenticatorServiceProvider.get().getCurrentUser();
			if(null != currentUser)
				tableDBDataSource.addQueryComment("currentuser: " + String.valueOf(currentUser.getId()));
		}catch(Exception e){
		}
	}

}
