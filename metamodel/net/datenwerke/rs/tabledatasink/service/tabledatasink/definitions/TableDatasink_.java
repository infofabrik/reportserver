package net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TableDatasink.class)
public abstract class TableDatasink_ extends net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition_ {

	public static volatile SingularAttribute<TableDatasink, String> primaryKeys;
	public static volatile SingularAttribute<TableDatasink, DatasourceContainer> datasourceContainer;
	public static volatile SingularAttribute<TableDatasink, Integer> batchSize;
	public static volatile SingularAttribute<TableDatasink, String> tableName;
	public static volatile SingularAttribute<TableDatasink, Boolean> copyPrimaryKeys;

}

