package net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmailDatasink.class)
public abstract class EmailDatasink_ extends net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition_ {

	public static volatile SingularAttribute<EmailDatasink, String> password;
	public static volatile SingularAttribute<EmailDatasink, String> senderName;
	public static volatile SingularAttribute<EmailDatasink, Integer> port;
	public static volatile SingularAttribute<EmailDatasink, String> sender;
	public static volatile SingularAttribute<EmailDatasink, String> encryptionPolicy;
	public static volatile SingularAttribute<EmailDatasink, String> host;
	public static volatile SingularAttribute<EmailDatasink, Boolean> tlsEnable;
	public static volatile SingularAttribute<EmailDatasink, Boolean> tlsRequire;
	public static volatile SingularAttribute<EmailDatasink, String> username;
	public static volatile SingularAttribute<EmailDatasink, Boolean> sslEnable;
	public static volatile SingularAttribute<EmailDatasink, Boolean> forceSender;

}

