package net.datenwerke.scheduler.service.scheduler.entities.history;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.scheduler.service.scheduler.entities.Outcome;
import net.datenwerke.scheduler.service.scheduler.helper.VetoJobExecutionMode;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExecutionLogEntry.class)
public abstract class ExecutionLogEntry_ {

	public static volatile SingularAttribute<ExecutionLogEntry, String> badErrorDescription;
	public static volatile SingularAttribute<ExecutionLogEntry, String> vetoExplanation;
	public static volatile SingularAttribute<ExecutionLogEntry, Date> start;
	public static volatile SingularAttribute<ExecutionLogEntry, Date> end;
	public static volatile ListAttribute<ExecutionLogEntry, ActionEntry> actionEntries;
	public static volatile SingularAttribute<ExecutionLogEntry, Long> id;
	public static volatile SingularAttribute<ExecutionLogEntry, Long> version;
	public static volatile SingularAttribute<ExecutionLogEntry, Outcome> outcome;
	public static volatile SingularAttribute<ExecutionLogEntry, VetoJobExecutionMode> vetoMode;
	public static volatile SingularAttribute<ExecutionLogEntry, JobEntry> jobEntry;
	public static volatile SingularAttribute<ExecutionLogEntry, Date> scheduledStart;

}

