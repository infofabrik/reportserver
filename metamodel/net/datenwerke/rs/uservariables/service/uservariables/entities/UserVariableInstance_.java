package net.datenwerke.rs.uservariables.service.uservariables.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserVariableInstance.class)
public abstract class UserVariableInstance_ {

	public static volatile SingularAttribute<UserVariableInstance, AbstractUserManagerNode> folk;
	public static volatile SingularAttribute<UserVariableInstance, UserVariableDefinition> definition;
	public static volatile SingularAttribute<UserVariableInstance, Long> id;
	public static volatile SingularAttribute<UserVariableInstance, Long> version;

}

