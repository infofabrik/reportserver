package net.datenwerke.rs.transport.service.transport.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.security.service.usermanager.entities.User;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transport.class)
public abstract class Transport_ extends net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode_ {

	public static volatile SingularAttribute<Transport, String> appliedProtocol;
	public static volatile SingularAttribute<Transport, String> creatorFirstname;
	public static volatile SingularAttribute<Transport, String> creatorEmail;
	public static volatile SingularAttribute<Transport, String> description;
	public static volatile SingularAttribute<Transport, String> serverId;
	public static volatile SingularAttribute<Transport, User> importedBy;
	public static volatile SingularAttribute<Transport, Date> appliedOn;
	public static volatile SingularAttribute<Transport, String> xml;
	public static volatile SingularAttribute<Transport, String> creatorUsername;
	public static volatile SingularAttribute<Transport, String> creatorLastname;
	public static volatile SingularAttribute<Transport, Date> importedOn;
	public static volatile SingularAttribute<Transport, Boolean> closed;
	public static volatile SingularAttribute<Transport, String> rsVersion;
	public static volatile SingularAttribute<Transport, User> appliedBy;
	public static volatile SingularAttribute<Transport, String> rsSchemaVersion;
	public static volatile SingularAttribute<Transport, String> key;
	public static volatile SingularAttribute<Transport, String> status;

}

