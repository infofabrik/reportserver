package net.datenwerke.security.service.usermanager.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Group.class)
public abstract class Group_ extends net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode_ {

	public static volatile SetAttribute<Group, Group> referencedGroups;
	public static volatile SingularAttribute<Group, String> name;
	public static volatile SingularAttribute<Group, String> description;
	public static volatile SetAttribute<Group, OrganisationalUnit> ous;
	public static volatile SetAttribute<Group, User> users;

}

