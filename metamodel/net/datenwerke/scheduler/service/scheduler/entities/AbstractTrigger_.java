package net.datenwerke.scheduler.service.scheduler.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AbstractTrigger.class)
public abstract class AbstractTrigger_ {

	public static volatile SingularAttribute<AbstractTrigger, Integer> nrOfSuccessfulExecutions;
	public static volatile SingularAttribute<AbstractTrigger, Integer> nrOfFailedExecutions;
	public static volatile SingularAttribute<AbstractTrigger, MisfireInstruction> misfireInstruction;
	public static volatile SingularAttribute<AbstractTrigger, Date> nextScheduledFireTime;
	public static volatile SingularAttribute<AbstractTrigger, Date> firstFireTime;
	public static volatile SingularAttribute<AbstractTrigger, Long> id;
	public static volatile SingularAttribute<AbstractTrigger, Integer> nrOfVetoedExecutions;
	public static volatile SingularAttribute<AbstractTrigger, Boolean> executeOnce;
	public static volatile SingularAttribute<AbstractTrigger, Long> version;

}

