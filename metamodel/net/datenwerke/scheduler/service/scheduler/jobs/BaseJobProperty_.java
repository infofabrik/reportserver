package net.datenwerke.scheduler.service.scheduler.jobs;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import net.datenwerke.scheduler.service.scheduler.entities.BaseProperty;

@StaticMetamodel(BaseProperty.class)
public abstract class BaseJobProperty_ {

	public static volatile SingularAttribute<BaseProperty, Long> id;
	public static volatile SingularAttribute<BaseProperty, String> value;
	public static volatile SingularAttribute<BaseProperty, String> key;
	public static volatile SingularAttribute<BaseProperty, Long> version;

}

