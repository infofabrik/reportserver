package net.datenwerke.rs.scripting.service.jobs;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScriptExecuteJob.class)
public abstract class ScriptExecuteJob_ extends net.datenwerke.rs.scheduler.service.scheduler.jobs.ReportServerJob_ {

	public static volatile SingularAttribute<ScriptExecuteJob, Long> scriptId;
	public static volatile SingularAttribute<ScriptExecuteJob, String> arguments;

}

