package net.datenwerke.rs.googledrive.service.googledrive.definitions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GoogleDriveDatasink.class)
public abstract class GoogleDriveDatasink_ extends net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition_ {

	public static volatile SingularAttribute<GoogleDriveDatasink, String> folder;
	public static volatile SingularAttribute<GoogleDriveDatasink, String> secretKey;
	public static volatile SingularAttribute<GoogleDriveDatasink, String> appKey;
	public static volatile SingularAttribute<GoogleDriveDatasink, String> refreshToken;

}

