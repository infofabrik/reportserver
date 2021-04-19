package net.datenwerke.rs.dashboard.service.dashboard.dagets;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.security.service.usermanager.entities.User;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FavoriteList.class)
public abstract class FavoriteList_ {

	public static volatile ListAttribute<FavoriteList, FavoriteListEntry> referenceEntries;
	public static volatile SingularAttribute<FavoriteList, Long> id;
	public static volatile SingularAttribute<FavoriteList, User> user;
	public static volatile SingularAttribute<FavoriteList, Long> version;

}

