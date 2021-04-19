package net.datenwerke.rs.uservariables.client.uservariables;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.gxtdto.client.stores.LoadableListStore;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.pa.UserVariableDefinitionDtoPA;
import net.datenwerke.rs.uservariables.client.uservariables.hooks.UserVariableProviderHook;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;

/**
 * 
 *
 */
public class UserVariablesUIServiceImpl implements UserVariablesUIService {

	private static UserVariableDefinitionDtoPA uvDefPa = GWT.create(UserVariableDefinitionDtoPA.class);
	
	final private HookHandlerService hookHandler;
	final private UserVariableDao userVaroaneDao;
	
	@Inject
	public UserVariablesUIServiceImpl(
		HookHandlerService hookHandler,
		UserVariableDao rpcService
		){
		
		this.hookHandler = hookHandler;
		this.userVaroaneDao = rpcService;
	}
	
	public Collection<UserVariableConfigurator> getAllVariableConfigurators(){
		Set<UserVariableConfigurator> configurators = new HashSet<UserVariableConfigurator>();
		
		Collection<UserVariableProviderHook> hookers = hookHandler.getHookers(UserVariableProviderHook.class);
		for(UserVariableProviderHook hooker : hookers)
			configurators.addAll(hooker.userVariableProviderHook_getConfigurators());
		
		return configurators;
	}
	
	
	public UserVariableConfigurator getConfigurator(UserVariableDefinitionDto uvd) {
		for(UserVariableConfigurator configurator : getAllVariableConfigurators())
			if(configurator.createDTOInstance().getClass().equals(uvd.getClass()))
				return configurator;

		return null;
	}

	public UserVariableConfigurator getConfigurator(UserVariableInstanceDto instance) {
		return getConfigurator(instance.getDefinition());
	}
	
	public ListLoader<ListLoadConfig, ListLoadResult<UserVariableDefinitionDto>> getDefinedVariableDefinitionsLoader(){
		/* create store */
		RpcProxy<ListLoadConfig, ListLoadResult<UserVariableDefinitionDto>> proxy = new RpcProxy<ListLoadConfig, ListLoadResult<UserVariableDefinitionDto>>() {

			@Override
			public void load(
					ListLoadConfig loadConfig,
					AsyncCallback<ListLoadResult<UserVariableDefinitionDto>> callback) {
				userVaroaneDao.getDefinedUserVariableDefinitions(callback);
			}
		};
	
		return new ListLoader<ListLoadConfig, ListLoadResult<UserVariableDefinitionDto>>(proxy);
	}

	@Override
	public LoadableListStore<ListLoadConfig, UserVariableDefinitionDto, ListLoadResult<UserVariableDefinitionDto>> getDefinedVariableDefinitionsStore() {
		return new LoadableListStore<ListLoadConfig, UserVariableDefinitionDto, ListLoadResult<UserVariableDefinitionDto>>(uvDefPa.dtoId(), getDefinedVariableDefinitionsLoader());
	}
}
