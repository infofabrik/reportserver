package net.datenwerke.rs.core.service.reportmanager.parameters;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;

public class ParameterSetFactory {
	
	private final Provider<AuthenticatorService> authenticatorServiceProvider;
	private final Provider<Injector> injectorProvider;
	
	@Inject
	public ParameterSetFactory(
		Provider<AuthenticatorService> authenticatorServiceProvider,
		Provider<Injector> injectorProvider){
		this.authenticatorServiceProvider = authenticatorServiceProvider;
		this.injectorProvider = injectorProvider;
	}
	
	public ParameterSet create(User user, Report report){
		return doPrepare(new ParameterSet(authenticatorServiceProvider.get(), user, report));
	}
	public ParameterSet create(Report report){
		return doPrepare(new ParameterSet(authenticatorServiceProvider.get(), report));
	}
	
	public ParameterSet create(){
		return doPrepare(new ParameterSet(authenticatorServiceProvider.get()));
	}
	
	public ParameterSet safeCreate(){
		User user = null;
		try{
			AuthenticatorService authenticatorService = authenticatorServiceProvider.get();
			user = authenticatorService.getCurrentUser();
		}catch(Exception e){}
		return doPrepare(new ParameterSet(user));
	}
	
	public ParameterSet safeCreate(User user) {
		return doPrepare(new ParameterSet(user));
	}
	
	private ParameterSet doPrepare(ParameterSet parameterSet) {
		injectorProvider.get().injectMembers(parameterSet);
		return parameterSet;
	}

}
