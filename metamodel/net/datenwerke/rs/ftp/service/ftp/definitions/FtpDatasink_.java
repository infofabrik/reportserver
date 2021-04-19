package net.datenwerke.rs.ftp.service.ftp.definitions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FtpDatasink.class)
public abstract class FtpDatasink_ extends net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition_ {

	public static volatile SingularAttribute<FtpDatasink, String> password;
	public static volatile SingularAttribute<FtpDatasink, String> folder;
	public static volatile SingularAttribute<FtpDatasink, Integer> port;
	public static volatile SingularAttribute<FtpDatasink, String> host;
	public static volatile SingularAttribute<FtpDatasink, String> username;

}

