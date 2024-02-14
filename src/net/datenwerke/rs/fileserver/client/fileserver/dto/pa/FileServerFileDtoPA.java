package net.datenwerke.rs.fileserver.client.fileserver.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Integer;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec;
import net.datenwerke.rs.fileserver.client.fileserver.dto.pa.AbstractFileServerNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile.class)
public interface FileServerFileDtoPA extends AbstractFileServerNodeDtoPA {


	public static final FileServerFileDtoPA INSTANCE = GWT.create(FileServerFileDtoPA.class);


	/* Properties */
	public ValueProvider<FileServerFileDto,String> contentType();
	public ValueProvider<FileServerFileDto,String> description();
	public ValueProvider<FileServerFileDto,String> key();
	public ValueProvider<FileServerFileDto,String> name();
	public ValueProvider<FileServerFileDto,Integer> size();


}
