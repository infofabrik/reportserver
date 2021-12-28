package net.datenwerke.rs.base.service.dbhelper.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ManagedQuery;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.utils.juel.SimpleJuel;

@HookConfig
public interface FilterReplacementProviderHook extends Hook {

   void enhance(SimpleJuel juel, Column column, QueryBuilder queryBuilder, ManagedQuery query);

}
