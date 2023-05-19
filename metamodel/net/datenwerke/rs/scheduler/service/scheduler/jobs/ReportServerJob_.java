package net.datenwerke.rs.scheduler.service.scheduler.jobs;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.security.service.usermanager.entities.User;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReportServerJob.class)
public abstract class ReportServerJob_ extends net.datenwerke.scheduler.service.scheduler.jobs.BaseJob_ {

	public static volatile SingularAttribute<ReportServerJob, User> executor;
	public static volatile SingularAttribute<ReportServerJob, User> scheduledBy;

}

