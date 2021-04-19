package net.datenwerke.scheduler.service.scheduler.entities.history;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.scheduler.service.scheduler.entities.Outcome;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JobEntry.class)
public abstract class JobEntry_ {

	public static volatile SingularAttribute<JobEntry, String> errorDescription;
	public static volatile SingularAttribute<JobEntry, Long> id;
	public static volatile SingularAttribute<JobEntry, Long> version;
	public static volatile SingularAttribute<JobEntry, Outcome> outcome;
	public static volatile SetAttribute<JobEntry, HistoryEntryProperty> historyProperties;

}

