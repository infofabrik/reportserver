package net.datenwerke.rs.dashboard.service.dashboard.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.security.service.usermanager.entities.User;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserDashboard.class)
public abstract class UserDashboard_ {

	public static volatile SingularAttribute<UserDashboard, DashboardContainer> dashboardContainer;
	public static volatile SingularAttribute<UserDashboard, Long> id;
	public static volatile SingularAttribute<UserDashboard, User> user;
	public static volatile SingularAttribute<UserDashboard, Long> version;

}

