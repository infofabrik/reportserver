package net.datenwerke.rs.base.service.datasources.definitions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DatabaseDatasource.class)
public abstract class DatabaseDatasource_ extends net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition_ {

	public static volatile SingularAttribute<DatabaseDatasource, String> password;
	public static volatile SingularAttribute<DatabaseDatasource, String> databaseDescriptor;
	public static volatile SingularAttribute<DatabaseDatasource, String> jdbcProperties;
	public static volatile SingularAttribute<DatabaseDatasource, String> url;
	public static volatile SingularAttribute<DatabaseDatasource, String> username;

}

