package net.datenwerke.rs.birt.service.datasources.birtreport.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BirtReportDatasourceConfig.class)
public abstract class BirtReportDatasourceConfig_ extends net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig_ {

	public static volatile SingularAttribute<BirtReportDatasourceConfig, BirtReport> report;
	public static volatile SingularAttribute<BirtReportDatasourceConfig, String> queryWrapper;
	public static volatile SingularAttribute<BirtReportDatasourceConfig, BirtReportDatasourceTargetType> targetType;
	public static volatile SingularAttribute<BirtReportDatasourceConfig, String> target;

}

