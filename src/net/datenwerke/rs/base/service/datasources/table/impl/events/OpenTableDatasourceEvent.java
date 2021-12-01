package net.datenwerke.rs.base.service.datasources.table.impl.events;

import java.sql.PreparedStatement;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class OpenTableDatasourceEvent extends DwLoggedEvent {

	public OpenTableDatasourceEvent(PreparedStatement stmt, String uuid) {
		super("statement", stmt, "uuid", uuid);
	}

	@Override
	public String getLoggedAction() {
		return "OPEN_TABLE_DB_DATASOURCE";
	}

}
