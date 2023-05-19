package net.datenwerke.scheduler.service.scheduler.triggers.complex.config;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Months;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(YearlyAtDateConfig.class)
public abstract class YearlyAtDateConfig_ extends net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DateTriggerConfig_ {

	public static volatile SingularAttribute<YearlyAtDateConfig, Months> yearlyMonth;
	public static volatile SingularAttribute<YearlyAtDateConfig, Integer> yearlyNDay;

}

