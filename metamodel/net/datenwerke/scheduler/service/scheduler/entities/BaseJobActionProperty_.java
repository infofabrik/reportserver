package net.datenwerke.scheduler.service.scheduler.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(BaseProperty.class)
public abstract class BaseJobActionProperty_ {

	public static volatile SingularAttribute<BaseProperty, Long> id;
	public static volatile SingularAttribute<BaseProperty, String> value;
	public static volatile SingularAttribute<BaseProperty, String> key;
	public static volatile SingularAttribute<BaseProperty, Long> version;

}

