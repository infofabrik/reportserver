package net.datenwerke.rs.base.ext.service.parameters.fileselection;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FileSelectionParameterDefinition.class)
public abstract class FileSelectionParameterDefinition_ extends net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition_ {

	public static volatile SingularAttribute<FileSelectionParameterDefinition, Boolean> allowFileUpload;
	public static volatile SingularAttribute<FileSelectionParameterDefinition, Boolean> allowFileServerSelection;
	public static volatile SingularAttribute<FileSelectionParameterDefinition, String> allowedFileExtensions;
	public static volatile SingularAttribute<FileSelectionParameterDefinition, String> fileSizeString;
	public static volatile SingularAttribute<FileSelectionParameterDefinition, Integer> maxNumberOfFiles;
	public static volatile SingularAttribute<FileSelectionParameterDefinition, Integer> width;
	public static volatile SingularAttribute<FileSelectionParameterDefinition, Integer> minNumberOfFiles;
	public static volatile SingularAttribute<FileSelectionParameterDefinition, Boolean> allowTeamSpaceSelection;
	public static volatile SingularAttribute<FileSelectionParameterDefinition, Boolean> allowDownload;
	public static volatile SingularAttribute<FileSelectionParameterDefinition, Integer> height;

}

