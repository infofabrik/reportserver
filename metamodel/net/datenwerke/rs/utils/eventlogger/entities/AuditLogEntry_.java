package net.datenwerke.rs.utils.eventlogger.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuditLogEntry.class)
public abstract class AuditLogEntry_ {

	public static volatile SingularAttribute<AuditLogEntry, Date> date;
	public static volatile SetAttribute<AuditLogEntry, LogProperty> logProperties;
	public static volatile SingularAttribute<AuditLogEntry, String> action;
	public static volatile SingularAttribute<AuditLogEntry, Long> id;
	public static volatile SingularAttribute<AuditLogEntry, Long> version;
	public static volatile SingularAttribute<AuditLogEntry, Long> userId;

}

