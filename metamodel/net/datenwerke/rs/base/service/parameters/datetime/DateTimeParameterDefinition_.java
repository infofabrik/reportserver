package net.datenwerke.rs.base.service.parameters.datetime;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DateTimeParameterDefinition.class)
public abstract class DateTimeParameterDefinition_ extends net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition_ {

	public static volatile SingularAttribute<DateTimeParameterDefinition, Mode> mode;
	public static volatile SingularAttribute<DateTimeParameterDefinition, Date> defaultValue;
	public static volatile SingularAttribute<DateTimeParameterDefinition, String> formula;
	public static volatile SingularAttribute<DateTimeParameterDefinition, Boolean> useNowAsDefault;

}

