package net.datenwerke.rs.scriptdatasink.service.scriptdatasink.action;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.definitions.ScriptDatasink;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleAsScriptDatasinkFileAction.class)
public abstract class ScheduleAsScriptDatasinkFileAction_ extends net.datenwerke.scheduler.service.scheduler.entities.AbstractAction_ {

	public static volatile SingularAttribute<ScheduleAsScriptDatasinkFileAction, String> name;
	public static volatile SingularAttribute<ScheduleAsScriptDatasinkFileAction, Boolean> compressed;
	public static volatile SingularAttribute<ScheduleAsScriptDatasinkFileAction, ScriptDatasink> scriptDatasink;

}

