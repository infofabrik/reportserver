package net.datenwerke.scheduler.service.scheduler.triggers.complex.config;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyPattern;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DailyConfig.class)
public abstract class DailyConfig_ extends net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DateTriggerConfig_ {

	public static volatile SingularAttribute<DailyConfig, DailyPattern> pattern;
	public static volatile SingularAttribute<DailyConfig, Integer> dailyN;

}

