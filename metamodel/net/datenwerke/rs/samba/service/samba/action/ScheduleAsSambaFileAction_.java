package net.datenwerke.rs.samba.service.samba.action;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleAsSambaFileAction.class)
public abstract class ScheduleAsSambaFileAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<ScheduleAsSambaFileAction, String> folder;
	public static volatile SingularAttribute<ScheduleAsSambaFileAction, String> name;
	public static volatile SingularAttribute<ScheduleAsSambaFileAction, SambaDatasink> sambaDatasink;
	public static volatile SingularAttribute<ScheduleAsSambaFileAction, Boolean> compressed;

}

