package net.datenwerke.rs.base.service.parameters.string;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.core.service.parameters.entities.Datatype;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TextParameterDefinition.class)
public abstract class TextParameterDefinition_ extends net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition_ {

	public static volatile SingularAttribute<TextParameterDefinition, Boolean> returnNullWhenEmpty;
	public static volatile SingularAttribute<TextParameterDefinition, String> validatorRegex;
	public static volatile SingularAttribute<TextParameterDefinition, String> defaultValue;
	public static volatile SingularAttribute<TextParameterDefinition, Integer> width;
	public static volatile SingularAttribute<TextParameterDefinition, Datatype> returnType;
	public static volatile SingularAttribute<TextParameterDefinition, Integer> height;

}

