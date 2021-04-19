package net.datenwerke.scheduler.service.scheduler.entities.history;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.scheduler.service.scheduler.entities.Outcome;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ActionEntry.class)
public abstract class ActionEntry_ {

	public static volatile SingularAttribute<ActionEntry, String> errorDescription;
	public static volatile SingularAttribute<ActionEntry, Long> id;
	public static volatile SingularAttribute<ActionEntry, Long> version;
	public static volatile SingularAttribute<ActionEntry, Outcome> outcome;
	public static volatile SetAttribute<ActionEntry, HistoryEntryProperty> historyProperties;
	public static volatile SingularAttribute<ActionEntry, String> actionName;

}

