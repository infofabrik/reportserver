package net.datenwerke.rs.fileserver.service.fileserver.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FileServerFile.class)
public abstract class FileServerFile_ extends net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode_ {

	public static volatile SingularAttribute<FileServerFile, FileServerFileData> fileData;
	public static volatile SingularAttribute<FileServerFile, String> name;
	public static volatile SingularAttribute<FileServerFile, String> description;
	public static volatile SingularAttribute<FileServerFile, String> contentType;

}

