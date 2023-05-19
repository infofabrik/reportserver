package net.datenwerke.scheduler.service.scheduler.triggers.complex.config;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Nth;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MonthlyNthDayOfWeekConfig.class)
public abstract class MonthlyNthDayOfWeekConfig_ extends net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DateTriggerConfig_ {

	public static volatile SingularAttribute<MonthlyNthDayOfWeekConfig, Nth> monthlyNth;
	public static volatile SingularAttribute<MonthlyNthDayOfWeekConfig, Integer> month;
	public static volatile SingularAttribute<MonthlyNthDayOfWeekConfig, Days> monthlyDay;

}

