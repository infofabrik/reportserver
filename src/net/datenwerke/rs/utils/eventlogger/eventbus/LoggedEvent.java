package net.datenwerke.rs.utils.eventlogger.eventbus;

import java.util.Date;
import java.util.Map;

import net.datenwerke.rs.utils.eventbus.Event;

public interface LoggedEvent extends Event {

	public abstract Long getLoggedUserId();

	public abstract String getLoggedAction();
	
	public abstract Map<String, String> getLoggedProperties();

	public abstract void aboutToBeLogged();

	public abstract Date getDate();

}
