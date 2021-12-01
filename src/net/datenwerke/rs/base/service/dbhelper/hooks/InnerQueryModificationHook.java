package net.datenwerke.rs.base.service.dbhelper.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public interface InnerQueryModificationHook extends Hook {

	public String modifyQuery(String currentQuery, QueryBuilder queryBuilder);
}
