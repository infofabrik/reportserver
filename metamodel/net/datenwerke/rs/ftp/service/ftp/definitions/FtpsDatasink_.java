package net.datenwerke.rs.ftp.service.ftp.definitions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FtpsDatasink.class)
public abstract class FtpsDatasink_ extends net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition_ {

	public static volatile SingularAttribute<FtpsDatasink, String> password;
	public static volatile SingularAttribute<FtpsDatasink, String> folder;
	public static volatile SingularAttribute<FtpsDatasink, Integer> port;
	public static volatile SingularAttribute<FtpsDatasink, String> dataChannelProtectionLevel;
	public static volatile SingularAttribute<FtpsDatasink, String> host;
	public static volatile SingularAttribute<FtpsDatasink, String> ftpMode;
	public static volatile SingularAttribute<FtpsDatasink, String> authenticationType;
	public static volatile SingularAttribute<FtpsDatasink, String> username;

}

