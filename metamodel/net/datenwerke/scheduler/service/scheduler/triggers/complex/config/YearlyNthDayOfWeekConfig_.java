package net.datenwerke.scheduler.service.scheduler.triggers.complex.config;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Months;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Nth;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(YearlyNthDayOfWeekConfig.class)
public abstract class YearlyNthDayOfWeekConfig_ extends net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DateTriggerConfig_ {

	public static volatile SingularAttribute<YearlyNthDayOfWeekConfig, Months> yearlyMonth;
	public static volatile SingularAttribute<YearlyNthDayOfWeekConfig, Nth> yearlyNth;
	public static volatile SingularAttribute<YearlyNthDayOfWeekConfig, Days> yearlyDay;

}

