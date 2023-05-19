package net.datenwerke.rs.teamspace.service.teamspace.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TeamSpaceApp.class)
public abstract class TeamSpaceApp_ {

	public static volatile SingularAttribute<TeamSpaceApp, Boolean> installed;
	public static volatile SingularAttribute<TeamSpaceApp, Long> id;
	public static volatile SingularAttribute<TeamSpaceApp, String> type;
	public static volatile SingularAttribute<TeamSpaceApp, Long> version;
	public static volatile SetAttribute<TeamSpaceApp, AppProperty> appProperties;

}

