package net.datenwerke.security.service.eventlogger.jpa;


public class ForceRemoveEntityEvent extends RemoveEntityEvent {

	public ForceRemoveEntityEvent(Object entity, Object... properties) {
		super(entity, properties);
	}

	@Override
	public String getLoggedAction() {
		return "FORCE_REMOVE_ENTITY";
	}

}
