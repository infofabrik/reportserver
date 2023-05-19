package net.datenwerke.rs.compiledreportstore.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PersistentCompiledReport.class)
public abstract class PersistentCompiledReport_ {

	public static volatile SingularAttribute<PersistentCompiledReport, Report> report;
	public static volatile SingularAttribute<PersistentCompiledReport, Long> id;
	public static volatile SingularAttribute<PersistentCompiledReport, Date> createdOn;
	public static volatile SingularAttribute<PersistentCompiledReport, Long> version;
	public static volatile SingularAttribute<PersistentCompiledReport, byte[]> serializedReport;

}

