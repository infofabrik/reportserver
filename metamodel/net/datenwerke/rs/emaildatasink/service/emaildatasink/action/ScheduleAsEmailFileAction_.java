package net.datenwerke.rs.emaildatasink.service.emaildatasink.action;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleAsEmailFileAction.class)
public abstract class ScheduleAsEmailFileAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<ScheduleAsEmailFileAction, String> subject;
	public static volatile SingularAttribute<ScheduleAsEmailFileAction, String> name;
	public static volatile SingularAttribute<ScheduleAsEmailFileAction, Boolean> compressed;
	public static volatile SingularAttribute<ScheduleAsEmailFileAction, String> message;
	public static volatile SingularAttribute<ScheduleAsEmailFileAction, EmailDatasink> emailDatasink;

}

