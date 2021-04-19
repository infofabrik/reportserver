package net.datenwerke.dbpool.hooks;

import java.sql.Connection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;

@HookConfig
public interface DbPoolConnectionHook extends Hook {

	void onAcquire(Connection connection);

	void onCheckIn(Connection connection);

	void onCheckOut(Connection connection);

	void onDestroy(Connection connection);

}
