package net.datenwerke.scheduler.service.scheduler.triggers.complex.config;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyRepeatType;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.EndTypes;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.TimeUnits;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DateTriggerConfig.class)
public abstract class DateTriggerConfig_ {

	public static volatile SingularAttribute<DateTriggerConfig, Time> atTime;
	public static volatile SingularAttribute<DateTriggerConfig, Time> timeRangeStart;
	public static volatile SingularAttribute<DateTriggerConfig, EndTypes> endType;
	public static volatile SingularAttribute<DateTriggerConfig, Time> timeRangeEnd;
	public static volatile SingularAttribute<DateTriggerConfig, Date> lastExecution;
	public static volatile SingularAttribute<DateTriggerConfig, DailyRepeatType> dailyRepeatType;
	public static volatile SingularAttribute<DateTriggerConfig, Integer> timeRangeInterval;
	public static volatile SingularAttribute<DateTriggerConfig, Integer> numberOfExecutions;
	public static volatile SingularAttribute<DateTriggerConfig, Long> id;
	public static volatile SingularAttribute<DateTriggerConfig, Long> version;
	public static volatile SingularAttribute<DateTriggerConfig, Date> firstExecution;
	public static volatile SingularAttribute<DateTriggerConfig, TimeUnits> timeRangeUnit;

}

