package net.datenwerke.rs.teamspace.service.teamspace.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TeamSpaceMember.class)
public abstract class TeamSpaceMember_ {

	public static volatile SingularAttribute<TeamSpaceMember, TeamSpace> teamSpace;
	public static volatile SingularAttribute<TeamSpaceMember, TeamSpaceRole> role;
	public static volatile SingularAttribute<TeamSpaceMember, AbstractUserManagerNode> folk;
	public static volatile SingularAttribute<TeamSpaceMember, Long> id;
	public static volatile SingularAttribute<TeamSpaceMember, Long> version;

}

