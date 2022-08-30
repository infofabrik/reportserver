package net.datenwerke.rs.box.service.box.action;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.box.service.box.definitions.BoxDatasink;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleAsBoxFileAction.class)
public abstract class ScheduleAsBoxFileAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<ScheduleAsBoxFileAction, String> folder;
	public static volatile SingularAttribute<ScheduleAsBoxFileAction, String> name;
	public static volatile SingularAttribute<ScheduleAsBoxFileAction, BoxDatasink> boxDatasink;
	public static volatile SingularAttribute<ScheduleAsBoxFileAction, Boolean> compressed;

}

