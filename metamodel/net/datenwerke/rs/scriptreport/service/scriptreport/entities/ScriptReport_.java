package net.datenwerke.rs.scriptreport.service.scriptreport.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScriptReport.class)
public abstract class ScriptReport_ extends net.datenwerke.rs.core.service.reportmanager.entities.reports.Report_ {

	public static volatile SingularAttribute<ScriptReport, String> arguments;
	public static volatile SingularAttribute<ScriptReport, FileServerFile> script;
	public static volatile ListAttribute<ScriptReport, String> exportFormats;

}

