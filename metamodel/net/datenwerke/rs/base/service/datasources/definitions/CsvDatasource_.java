package net.datenwerke.rs.base.service.datasources.definitions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CsvDatasource.class)
public abstract class CsvDatasource_ extends net.datenwerke.rs.base.service.datasources.definitions.FormatBasedDatasourceDefinition_ {

	public static volatile SingularAttribute<CsvDatasource, String> quote;
	public static volatile SingularAttribute<CsvDatasource, Integer> databaseCache;
	public static volatile SingularAttribute<CsvDatasource, String> separator;

}

