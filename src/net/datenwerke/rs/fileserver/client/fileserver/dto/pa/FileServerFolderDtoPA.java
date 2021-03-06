package net.datenwerke.rs.fileserver.client.fileserver.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.pa.AbstractFileServerNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder.class)
public interface FileServerFolderDtoPA extends AbstractFileServerNodeDtoPA {


	public static final FileServerFolderDtoPA INSTANCE = GWT.create(FileServerFolderDtoPA.class);


	/* Properties */
	public ValueProvider<FileServerFolderDto,String> description();
	public ValueProvider<FileServerFolderDto,String> name();
	public ValueProvider<FileServerFolderDto,Boolean> publiclyAccessible();


}
