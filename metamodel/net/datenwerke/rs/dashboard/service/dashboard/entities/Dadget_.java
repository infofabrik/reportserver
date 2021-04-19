package net.datenwerke.rs.dashboard.service.dashboard.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dadget.class)
public abstract class Dadget_ {

	public static volatile SingularAttribute<Dadget, DadgetContainer> container;
	public static volatile SingularAttribute<Dadget, Integer> col;
	public static volatile SetAttribute<Dadget, ParameterInstance> parameterInstances;
	public static volatile SingularAttribute<Dadget, Long> reloadInterval;
	public static volatile SingularAttribute<Dadget, Long> id;
	public static volatile SingularAttribute<Dadget, Long> version;
	public static volatile SingularAttribute<Dadget, Integer> n;
	public static volatile SingularAttribute<Dadget, Integer> height;

}

