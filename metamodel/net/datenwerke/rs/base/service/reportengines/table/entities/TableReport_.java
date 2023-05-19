package net.datenwerke.rs.base.service.reportengines.table.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TableReport.class)
public abstract class TableReport_ extends net.datenwerke.rs.core.service.reportmanager.entities.reports.Report_ {

	public static volatile SingularAttribute<TableReport, PreFilter> preFilter;
	public static volatile ListAttribute<TableReport, Column> columns;
	public static volatile SingularAttribute<TableReport, Boolean> enableSubtotals;
	public static volatile SingularAttribute<TableReport, Boolean> distinctFlag;
	public static volatile SingularAttribute<TableReport, String> cubeXml;
	public static volatile SingularAttribute<TableReport, Boolean> cubeFlag;
	public static volatile SingularAttribute<TableReport, Boolean> allowCubification;
	public static volatile SingularAttribute<TableReport, Boolean> allowMdx;
	public static volatile SingularAttribute<TableReport, DatasourceContainer> metadataDatasourceContainer;
	public static volatile ListAttribute<TableReport, AdditionalColumnSpec> additionalColumns;
	public static volatile SingularAttribute<TableReport, Boolean> hideParents;

}

