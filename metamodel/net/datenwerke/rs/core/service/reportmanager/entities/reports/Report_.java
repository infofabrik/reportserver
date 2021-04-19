package net.datenwerke.rs.core.service.reportmanager.entities.reports;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Report.class)
public abstract class Report_ extends net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode_ {

	public static volatile SetAttribute<Report, ParameterInstance> parameterInstances;
	public static volatile SingularAttribute<Report, DatasourceContainer> datasourceContainer;
	public static volatile SetAttribute<Report, ReportMetadata> reportMetadata;
	public static volatile ListAttribute<Report, ParameterDefinition> parameterDefinitions;
	public static volatile SingularAttribute<Report, String> name;
	public static volatile SingularAttribute<Report, String> description;
	public static volatile SingularAttribute<Report, String> uuid;
	public static volatile SetAttribute<Report, ReportProperty> reportProperties;
	public static volatile SingularAttribute<Report, String> key;
	public static volatile SingularAttribute<Report, PreviewImage> previewImage;

}

