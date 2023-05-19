package net.datenwerke.rs.dsbundle.service.dsbundle.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DatabaseBundle.class)
public abstract class DatabaseBundle_ extends net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource_ {

	public static volatile SetAttribute<DatabaseBundle, DatabaseBundleEntry> bundleEntries;
	public static volatile SingularAttribute<DatabaseBundle, String> keySource;
	public static volatile SingularAttribute<DatabaseBundle, String> keySourceParamName;
	public static volatile SingularAttribute<DatabaseBundle, String> mappingSource;

}

