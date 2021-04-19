package net.datenwerke.rs.base.service.dbhelper.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

@HookConfig
public interface DatabaseHelperProviderHook extends Hook {

	public Collection<DatabaseHelper> provideDatabaseHelpers();
}
