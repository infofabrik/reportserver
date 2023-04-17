package net.datenwerke.security.service.usermanager.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode_ {

	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Sex> sex;
	public static volatile SetAttribute<User, Group> groups;
	public static volatile SingularAttribute<User, String> title;
	public static volatile SetAttribute<User, UserProperty> properties;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Boolean> superUser;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile SingularAttribute<User, String> username;

}

