package net.datenwerke.rs.utils.filename.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;

@HookConfig
public interface FileNameSanitizerHook extends Hook {

	public String sanitizeFileName(String name);
}
