package net.datenwerke.security.service.security.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Acl.class)
public abstract class Acl_ {

	public static volatile SingularAttribute<Acl, Long> id;
	public static volatile SingularAttribute<Acl, Integer> version;
	public static volatile ListAttribute<Acl, Ace> aces;

}

