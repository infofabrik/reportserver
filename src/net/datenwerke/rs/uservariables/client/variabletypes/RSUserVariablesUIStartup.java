package net.datenwerke.rs.uservariables.client.variabletypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.uservariables.client.uservariables.UserVariableConfigurator;
import net.datenwerke.rs.uservariables.client.uservariables.hooks.UserVariableProviderHook;
import net.datenwerke.rs.uservariables.client.variabletypes.list.ListConfigurator;
import net.datenwerke.rs.uservariables.client.variabletypes.string.StringConfigurator;

public class RSUserVariablesUIStartup implements UserVariableProviderHook {

   private final Provider<StringConfigurator> stringConfigurator;
   private final Provider<ListConfigurator> listConfigurator;

   @Inject
   public RSUserVariablesUIStartup(HookHandlerService hookHandler,

         Provider<StringConfigurator> stringConfigurator, Provider<ListConfigurator> listConfigurator) {

      this.stringConfigurator = stringConfigurator;
      this.listConfigurator = listConfigurator;

      hookHandler.attachHooker(UserVariableProviderHook.class, this);
   }

   public Collection<UserVariableConfigurator> userVariableProviderHook_getConfigurators() {
      List<UserVariableConfigurator> list = new ArrayList<UserVariableConfigurator>();

      list.add(stringConfigurator.get());
      list.add(listConfigurator.get());

      return list;
   }
}
