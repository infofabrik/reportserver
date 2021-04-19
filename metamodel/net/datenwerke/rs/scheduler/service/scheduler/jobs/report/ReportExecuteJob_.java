package net.datenwerke.rs.scheduler.service.scheduler.jobs.report;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.service.usermanager.entities.User;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReportExecuteJob.class)
public abstract class ReportExecuteJob_ extends net.datenwerke.rs.scheduler.service.scheduler.jobs.ReportServerJob_ {

	public static volatile SetAttribute<ReportExecuteJob, Long> rcptIDs;
	public static volatile SingularAttribute<ReportExecuteJob, Report> report;
	public static volatile SetAttribute<ReportExecuteJob, User> owners;
	public static volatile SingularAttribute<ReportExecuteJob, String> outputFormat;

}

