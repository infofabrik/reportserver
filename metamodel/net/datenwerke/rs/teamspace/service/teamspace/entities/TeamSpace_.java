package net.datenwerke.rs.teamspace.service.teamspace.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.security.service.usermanager.entities.User;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TeamSpace.class)
public abstract class TeamSpace_ {

	public static volatile SingularAttribute<TeamSpace, User> owner;
	public static volatile SetAttribute<TeamSpace, TeamSpaceMember> members;
	public static volatile SingularAttribute<TeamSpace, String> name;
	public static volatile SingularAttribute<TeamSpace, String> description;
	public static volatile SingularAttribute<TeamSpace, Long> id;
	public static volatile SingularAttribute<TeamSpace, Long> version;
	public static volatile SetAttribute<TeamSpace, TeamSpaceApp> apps;

}

