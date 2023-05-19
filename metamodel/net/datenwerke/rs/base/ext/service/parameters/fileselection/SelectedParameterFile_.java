package net.datenwerke.rs.base.ext.service.parameters.fileselection;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SelectedParameterFile.class)
public abstract class SelectedParameterFile_ {

	public static volatile SingularAttribute<SelectedParameterFile, AbstractFileServerNode> fileServerFile;
	public static volatile SingularAttribute<SelectedParameterFile, AbstractTsDiskNode> teamSpaceFile;
	public static volatile SingularAttribute<SelectedParameterFile, String> name;
	public static volatile SingularAttribute<SelectedParameterFile, Long> id;
	public static volatile SingularAttribute<SelectedParameterFile, Long> version;
	public static volatile SingularAttribute<SelectedParameterFile, UploadedParameterFile> uploadedFile;

}

