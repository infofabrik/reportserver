package net.datenwerke.security.service.eventlogger.jpa;


public class AfterMergeEntityEvent extends JpaEvent {

	public AfterMergeEntityEvent(Object entity, Object... properties) {
		super(entity, properties);
	}

	@Override
	public String getLoggedAction() {
		return "AFTER_MERGE_ENTITY";
	}

}
