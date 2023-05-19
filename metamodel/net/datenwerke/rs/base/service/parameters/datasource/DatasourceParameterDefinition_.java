package net.datenwerke.rs.base.service.parameters.datasource;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.parameters.entities.Datatype;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DatasourceParameterDefinition.class)
public abstract class DatasourceParameterDefinition_ extends net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition_ {

	public static volatile ListAttribute<DatasourceParameterDefinition, DatasourceParameterData> multiDefaultValueSimpleData;
	public static volatile SingularAttribute<DatasourceParameterDefinition, String> format;
	public static volatile SingularAttribute<DatasourceParameterDefinition, SingleSelectionMode> singleSelectionMode;
	public static volatile SingularAttribute<DatasourceParameterDefinition, MultiSelectionMode> multiSelectionMode;
	public static volatile SingularAttribute<DatasourceParameterDefinition, Mode> mode;
	public static volatile SingularAttribute<DatasourceParameterDefinition, BoxLayoutPackMode> boxLayoutPackMode;
	public static volatile SingularAttribute<DatasourceParameterDefinition, DatasourceContainer> datasourceContainer;
	public static volatile SingularAttribute<DatasourceParameterDefinition, BoxLayoutMode> boxLayoutMode;
	public static volatile SingularAttribute<DatasourceParameterDefinition, Integer> width;
	public static volatile SingularAttribute<DatasourceParameterDefinition, DatasourceParameterData> singleDefaultValueSimpleData;
	public static volatile SingularAttribute<DatasourceParameterDefinition, Integer> boxLayoutPackColSize;
	public static volatile SingularAttribute<DatasourceParameterDefinition, String> postProcess;
	public static volatile SingularAttribute<DatasourceParameterDefinition, Datatype> returnType;
	public static volatile SingularAttribute<DatasourceParameterDefinition, Integer> height;

}

