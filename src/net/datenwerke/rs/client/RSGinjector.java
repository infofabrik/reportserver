package net.datenwerke.rs.client;


import net.datenwerke.gf.client.dispatcher.DispatcherService;
import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.rs.client.dispatcher.DispatcherModule;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(DispatcherModule.class)
public interface RSGinjector extends Ginjector{
	public DispatcherService getDispatcherService();
	public LoginService getLoginService();
}
