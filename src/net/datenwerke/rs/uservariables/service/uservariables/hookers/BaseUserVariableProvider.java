package net.datenwerke.rs.uservariables.service.uservariables.hookers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.hooks.UserVariableProviderHook;
import net.datenwerke.rs.uservariables.service.variabletypes.list.ListUserVariableDefinition;
import net.datenwerke.rs.uservariables.service.variabletypes.string.StringUserVariableDefinition;

public class BaseUserVariableProvider implements UserVariableProviderHook {

	@Inject private Provider<StringUserVariableDefinition> stringProvider;
	@Inject private Provider<ListUserVariableDefinition> listProvider;
	
	@Override
	public Collection<? extends UserVariableDefinition> getVariables() {
		List<UserVariableDefinition> list = new ArrayList<UserVariableDefinition>();
		
		list.add(stringProvider.get());
		list.add(listProvider.get());
		
		return list;
	}

}
