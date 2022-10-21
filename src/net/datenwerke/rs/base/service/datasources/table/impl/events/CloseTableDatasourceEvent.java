package net.datenwerke.rs.base.service.datasources.table.impl.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class CloseTableDatasourceEvent extends DwLoggedEvent {

   public CloseTableDatasourceEvent(String uuid) {
      super("uuid", uuid);
   }

   @Override
   public String getLoggedAction() {
      return "CLOSE_TABLE_DB_DATASOURCE";
   }

}
