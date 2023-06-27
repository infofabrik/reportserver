package net.datenwerke.rs.dropbox.service.dropbox.definitions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DropboxDatasink.class)
public abstract class DropboxDatasink_ extends net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition_ {

	public static volatile SingularAttribute<DropboxDatasink, String> folder;
	public static volatile SingularAttribute<DropboxDatasink, String> secretKey;
	public static volatile SingularAttribute<DropboxDatasink, String> appKey;
	public static volatile SingularAttribute<DropboxDatasink, String> refreshToken;

}

