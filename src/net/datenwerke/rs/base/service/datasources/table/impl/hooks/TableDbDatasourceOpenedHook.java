package net.datenwerke.rs.base.service.datasources.table.impl.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.base.service.datasources.table.impl.TableDBDataSource;

public interface TableDbDatasourceOpenedHook extends Hook {

   void datasourceOpenend(TableDBDataSource tableDBDataSource, String executorToken);

}
