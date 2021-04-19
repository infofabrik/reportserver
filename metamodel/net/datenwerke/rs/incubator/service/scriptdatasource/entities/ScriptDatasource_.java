package net.datenwerke.rs.incubator.service.scriptdatasource.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScriptDatasource.class)
public abstract class ScriptDatasource_ extends net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition_ {

	public static volatile SingularAttribute<ScriptDatasource, Boolean> defineAtTarget;
	public static volatile SingularAttribute<ScriptDatasource, Integer> databaseCache;
	public static volatile SingularAttribute<ScriptDatasource, FileServerFile> script;

}

