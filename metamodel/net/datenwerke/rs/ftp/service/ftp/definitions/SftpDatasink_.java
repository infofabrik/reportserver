package net.datenwerke.rs.ftp.service.ftp.definitions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SftpDatasink.class)
public abstract class SftpDatasink_ extends net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition_ {

	public static volatile SingularAttribute<SftpDatasink, String> password;
	public static volatile SingularAttribute<SftpDatasink, String> folder;
	public static volatile SingularAttribute<SftpDatasink, Integer> port;
	public static volatile SingularAttribute<SftpDatasink, String> host;
	public static volatile SingularAttribute<SftpDatasink, String> username;

}

