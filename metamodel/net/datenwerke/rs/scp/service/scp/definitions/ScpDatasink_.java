package net.datenwerke.rs.scp.service.scp.definitions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScpDatasink.class)
public abstract class ScpDatasink_ extends net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition_ {

	public static volatile SingularAttribute<ScpDatasink, byte[]> privateKey;
	public static volatile SingularAttribute<ScpDatasink, String> password;
	public static volatile SingularAttribute<ScpDatasink, String> privateKeyPassphrase;
	public static volatile SingularAttribute<ScpDatasink, String> folder;
	public static volatile SingularAttribute<ScpDatasink, Integer> port;
	public static volatile SingularAttribute<ScpDatasink, String> host;
	public static volatile SingularAttribute<ScpDatasink, String> authenticationType;
	public static volatile SingularAttribute<ScpDatasink, String> username;

}

