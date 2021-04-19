package net.datenwerke.gf.client.treedb;

import javax.inject.Inject;

import net.datenwerke.gf.client.treedb.simpleform.TreeNodeDtoProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

import com.google.inject.Provider;

public class DwGwtTreeDbUiStartup {
	
	@Inject
	public DwGwtTreeDbUiStartup(
		HookHandlerService hookHandler,
		Provider<TreeNodeDtoProvider> genericNodeProvider	
		){
	
		hookHandler.attachHooker(FormFieldProviderHook.class, genericNodeProvider, HookHandlerService.PRIORITY_LOW);
	}
}
