package net.datenwerke.scheduler.service.scheduler.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobHistory;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AbstractJob.class)
public abstract class AbstractJob_ {

	public static volatile SingularAttribute<AbstractJob, AbstractJob> linkToPrevious;
	public static volatile SingularAttribute<AbstractJob, Outcome> lastOutcome;
	public static volatile SingularAttribute<AbstractJob, JobExecutionStatus> executionStatus;
	public static volatile SingularAttribute<AbstractJob, String> description;
	public static volatile SingularAttribute<AbstractJob, AbstractTrigger> trigger;
	public static volatile SingularAttribute<AbstractJob, JobHistory> history;
	public static volatile SingularAttribute<AbstractJob, Long> id;
	public static volatile SingularAttribute<AbstractJob, String> title;
	public static volatile ListAttribute<AbstractJob, AbstractAction> actions;
	public static volatile SingularAttribute<AbstractJob, Date> createdOn;
	public static volatile SingularAttribute<AbstractJob, Long> version;

}

