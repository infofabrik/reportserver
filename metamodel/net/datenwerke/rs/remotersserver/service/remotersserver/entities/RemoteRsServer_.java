package net.datenwerke.rs.remotersserver.service.remotersserver.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RemoteRsServer.class)
public abstract class RemoteRsServer_ extends net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerDefinition_ {

	public static volatile SingularAttribute<RemoteRsServer, String> apikey;
	public static volatile SingularAttribute<RemoteRsServer, String> url;
	public static volatile SingularAttribute<RemoteRsServer, String> username;

}
