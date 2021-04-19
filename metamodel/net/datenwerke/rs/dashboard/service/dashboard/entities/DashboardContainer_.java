package net.datenwerke.rs.dashboard.service.dashboard.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DashboardContainer.class)
public abstract class DashboardContainer_ {

	public static volatile SingularAttribute<DashboardContainer, Long> id;
	public static volatile ListAttribute<DashboardContainer, Dashboard> dashboards;
	public static volatile SingularAttribute<DashboardContainer, Long> version;

}

