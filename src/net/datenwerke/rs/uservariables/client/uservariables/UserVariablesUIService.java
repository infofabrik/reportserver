package net.datenwerke.rs.uservariables.client.uservariables;

import java.util.Collection;

import net.datenwerke.gxtdto.client.stores.LoadableListStore;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto;

import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;

public interface UserVariablesUIService {

	public Collection<UserVariableConfigurator> getAllVariableConfigurators();

	public UserVariableConfigurator getConfigurator(UserVariableInstanceDto instance);
	
	public UserVariableConfigurator getConfigurator(UserVariableDefinitionDto definition);

	public ListLoader<ListLoadConfig, ListLoadResult<UserVariableDefinitionDto>> getDefinedVariableDefinitionsLoader();
	
	public LoadableListStore<ListLoadConfig, UserVariableDefinitionDto, ListLoadResult<UserVariableDefinitionDto>> getDefinedVariableDefinitionsStore();
}
