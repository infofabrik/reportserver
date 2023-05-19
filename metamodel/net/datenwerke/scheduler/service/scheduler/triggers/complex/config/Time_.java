package net.datenwerke.scheduler.service.scheduler.triggers.complex.config;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Time.class)
public abstract class Time_ {

	public static volatile SingularAttribute<Time, Date> date;
	public static volatile SingularAttribute<Time, Integer> hour;
	public static volatile SingularAttribute<Time, Integer> minutes;

}

