package net.datenwerke.rs.core.service.contexthelp.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;

@HookConfig
public interface ContextHelpAdapterHook extends Hook {

	public String adaptContextHelp(String text, ContextHelpInfo info);
	
}
