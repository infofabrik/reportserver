package net.datenwerke.rs.core.service.datasourcemanager.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DatasourceContainer.class)
public abstract class DatasourceContainer_ {

	public static volatile SingularAttribute<DatasourceContainer, DatasourceDefinitionConfig> datasourceConfig;
	public static volatile SingularAttribute<DatasourceContainer, DatasourceDefinition> datasource;
	public static volatile SingularAttribute<DatasourceContainer, Long> id;
	public static volatile SingularAttribute<DatasourceContainer, Long> version;

}

