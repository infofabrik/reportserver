package net.datenwerke.security.service.security.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ace.class)
public abstract class Ace_ {

	public static volatile SetAttribute<Ace, AceAccessMap> accessMaps;
	public static volatile SingularAttribute<Ace, AccessType> accesstype;
	public static volatile SingularAttribute<Ace, AbstractUserManagerNode> folk;
	public static volatile SingularAttribute<Ace, Long> id;
	public static volatile SingularAttribute<Ace, Acl> acl;
	public static volatile SingularAttribute<Ace, Integer> version;
	public static volatile SingularAttribute<Ace, Integer> n;

}

