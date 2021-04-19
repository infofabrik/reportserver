package net.datenwerke.rs.dashboard.service.dashboard.dagets;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReportDadget.class)
public abstract class ReportDadget_ extends net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget_ {

	public static volatile SingularAttribute<ReportDadget, Boolean> showExecuteButton;
	public static volatile SingularAttribute<ReportDadget, Report> report;
	public static volatile SingularAttribute<ReportDadget, TsDiskReportReference> reportReference;
	public static volatile SingularAttribute<ReportDadget, String> config;

}

