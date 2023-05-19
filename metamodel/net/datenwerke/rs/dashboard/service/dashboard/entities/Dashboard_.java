package net.datenwerke.rs.dashboard.service.dashboard.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dashboard.class)
public abstract class Dashboard_ {

	public static volatile SingularAttribute<Dashboard, LayoutType> layout;
	public static volatile ListAttribute<Dashboard, Dadget> dadgets;
	public static volatile SingularAttribute<Dashboard, Long> reloadInterval;
	public static volatile SingularAttribute<Dashboard, Boolean> singlePage;
	public static volatile SingularAttribute<Dashboard, String> name;
	public static volatile SingularAttribute<Dashboard, String> description;
	public static volatile SingularAttribute<Dashboard, Long> id;
	public static volatile SingularAttribute<Dashboard, Long> version;
	public static volatile SingularAttribute<Dashboard, Integer> n;

}

