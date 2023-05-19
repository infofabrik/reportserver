package net.datenwerke.security.service.treedb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.security.service.security.entities.HierarchicalAcl;
import net.datenwerke.security.service.usermanager.entities.User;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SecuredAbstractNode.class)
public abstract class SecuredAbstractNode_ extends net.datenwerke.treedb.service.treedb.AbstractNode_ {

	public static volatile SingularAttribute<SecuredAbstractNode, User> owner;
	public static volatile SingularAttribute<SecuredAbstractNode, HierarchicalAcl> acl;

}

