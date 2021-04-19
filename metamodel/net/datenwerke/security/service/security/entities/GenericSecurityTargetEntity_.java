package net.datenwerke.security.service.security.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GenericSecurityTargetEntity.class)
public abstract class GenericSecurityTargetEntity_ {

	public static volatile SingularAttribute<GenericSecurityTargetEntity, String> targetIdentifier;
	public static volatile SingularAttribute<GenericSecurityTargetEntity, Acl> acl;
	public static volatile SingularAttribute<GenericSecurityTargetEntity, Long> id;
	public static volatile SingularAttribute<GenericSecurityTargetEntity, Long> version;

}

