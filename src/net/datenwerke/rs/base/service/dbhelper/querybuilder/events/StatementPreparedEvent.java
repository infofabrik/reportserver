package net.datenwerke.rs.base.service.dbhelper.querybuilder.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class StatementPreparedEvent extends DwLoggedEvent {

	public StatementPreparedEvent(String prelim, String statement){
		super("prelim_statement", prelim, "statement", statement);
	}
	
	@Override
	public String getLoggedAction() {
		return "STATEMENT_PREPARED";
	}

}
