package net.datenwerke.rs.saiku.service.datasource;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MondrianDatasource.class)
public abstract class MondrianDatasource_ extends net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition_ {

	public static volatile SingularAttribute<MondrianDatasource, String> password;
	public static volatile SingularAttribute<MondrianDatasource, String> mondrianSchema;
	public static volatile SingularAttribute<MondrianDatasource, String> url;
	public static volatile SingularAttribute<MondrianDatasource, String> properties;
	public static volatile SingularAttribute<MondrianDatasource, String> username;

}

