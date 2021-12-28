package net.datenwerke.rs.uservariables.client.uservariables.hookers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.client.parameters.config.ParameterConfigurator;
import net.datenwerke.rs.core.client.parameters.hooks.ParameterProviderHook;
import net.datenwerke.rs.uservariables.client.parameters.UserVariableParameterConfigurator;

public class ParameterProviderHooker implements ParameterProviderHook {

   @Inject
   Provider<UserVariableParameterConfigurator> parameter;

   @Override
   public Collection<ParameterConfigurator> parameterProviderHook_getConfigurators() {
      List<ParameterConfigurator> list = new ArrayList<ParameterConfigurator>();

      list.add(parameter.get());

      return list;
   }

}
