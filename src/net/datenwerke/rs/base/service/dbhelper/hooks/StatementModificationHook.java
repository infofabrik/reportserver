package net.datenwerke.rs.base.service.dbhelper.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

@HookConfig
public interface StatementModificationHook extends Hook {

	public String modifyStatement(String stmt, QueryBuilder queryBuilder);
}