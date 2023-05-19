package net.datenwerke.scheduler.service.scheduler.entities.history;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JobHistory.class)
public abstract class JobHistory_ {

	public static volatile ListAttribute<JobHistory, ExecutionLogEntry> executionLogEntries;
	public static volatile SingularAttribute<JobHistory, Long> id;
	public static volatile SingularAttribute<JobHistory, Long> version;

}

