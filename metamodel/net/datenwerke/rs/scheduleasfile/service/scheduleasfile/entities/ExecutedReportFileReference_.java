package net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.compiledreportstore.entities.PersistentCompiledReport;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExecutedReportFileReference.class)
public abstract class ExecutedReportFileReference_ extends net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskGeneralReference_ {

	public static volatile SingularAttribute<ExecutedReportFileReference, String> outputFormat;
	public static volatile SingularAttribute<ExecutedReportFileReference, PersistentCompiledReport> compiledReport;

}

