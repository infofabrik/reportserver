package net.datenwerke.rs.core.service.parameters.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ParameterDefinition.class)
public abstract class ParameterDefinition_ {

	public static volatile ListAttribute<ParameterDefinition, ParameterDefinition> dependsOn;
	public static volatile SingularAttribute<ParameterDefinition, Boolean> hidden;
	public static volatile SingularAttribute<ParameterDefinition, Boolean> displayInline;
	public static volatile SingularAttribute<ParameterDefinition, Boolean> editable;
	public static volatile SingularAttribute<ParameterDefinition, String> name;
	public static volatile SingularAttribute<ParameterDefinition, String> description;
	public static volatile SingularAttribute<ParameterDefinition, Integer> labelWidth;
	public static volatile SingularAttribute<ParameterDefinition, Long> id;
	public static volatile SingularAttribute<ParameterDefinition, Boolean> mandatory;
	public static volatile SingularAttribute<ParameterDefinition, Long> version;
	public static volatile SingularAttribute<ParameterDefinition, String> key;
	public static volatile SingularAttribute<ParameterDefinition, Integer> n;

}

