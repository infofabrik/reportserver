package net.datenwerke.rs.scriptreport.service.scriptreport.parameter;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScriptParameterDefinition.class)
public abstract class ScriptParameterDefinition_ extends net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition_ {

	public static volatile SingularAttribute<ScriptParameterDefinition, String> defaultValue;
	public static volatile SingularAttribute<ScriptParameterDefinition, Integer> width;
	public static volatile SingularAttribute<ScriptParameterDefinition, String> arguments;
	public static volatile SingularAttribute<ScriptParameterDefinition, FileServerFile> script;
	public static volatile SingularAttribute<ScriptParameterDefinition, Integer> height;

}

