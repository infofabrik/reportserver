package net.datenwerke.rs.dashboard.service.dashboard.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DashboardNode.class)
public abstract class DashboardNode_ extends net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode_ {

	public static volatile SingularAttribute<DashboardNode, String> name;
	public static volatile SingularAttribute<DashboardNode, String> description;
	public static volatile SingularAttribute<DashboardNode, Dashboard> dashboard;

}

