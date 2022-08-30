package net.datenwerke.rs.scp.service.scp.action;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleAsScpFileAction.class)
public abstract class ScheduleAsScpFileAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<ScheduleAsScpFileAction, ScpDatasink> scpDatasink;
	public static volatile SingularAttribute<ScheduleAsScpFileAction, String> folder;
	public static volatile SingularAttribute<ScheduleAsScpFileAction, String> name;
	public static volatile SingularAttribute<ScheduleAsScpFileAction, Boolean> compressed;

}

