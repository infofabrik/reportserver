package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TsDiskReportReference.class)
public abstract class TsDiskReportReference_ extends net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskGeneralReference_ {

	public static volatile SingularAttribute<TsDiskReportReference, Boolean> hardlink;
	public static volatile SingularAttribute<TsDiskReportReference, Report> report;

}

