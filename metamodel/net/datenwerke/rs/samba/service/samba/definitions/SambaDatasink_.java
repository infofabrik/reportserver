package net.datenwerke.rs.samba.service.samba.definitions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SambaDatasink.class)
public abstract class SambaDatasink_ extends net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition_ {

	public static volatile SingularAttribute<SambaDatasink, String> password;
	public static volatile SingularAttribute<SambaDatasink, String> folder;
	public static volatile SingularAttribute<SambaDatasink, Integer> port;
	public static volatile SingularAttribute<SambaDatasink, String> domain;
	public static volatile SingularAttribute<SambaDatasink, String> host;
	public static volatile SingularAttribute<SambaDatasink, String> username;

}

