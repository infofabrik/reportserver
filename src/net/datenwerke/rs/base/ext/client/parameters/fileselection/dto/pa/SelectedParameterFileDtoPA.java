package net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.ext.service.parameters.fileselection.SelectedParameterFile.class)
public interface SelectedParameterFileDtoPA extends PropertyAccess<SelectedParameterFileDto> {


	public static final SelectedParameterFileDtoPA INSTANCE = GWT.create(SelectedParameterFileDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<SelectedParameterFileDto> dtoId();

	/* Properties */
	public ValueProvider<SelectedParameterFileDto,AbstractFileServerNodeDto> fileServerFile();
	public ValueProvider<SelectedParameterFileDto,Long> id();
	public ValueProvider<SelectedParameterFileDto,String> name();
	public ValueProvider<SelectedParameterFileDto,AbstractTsDiskNodeDto> teamSpaceFile();
	public ValueProvider<SelectedParameterFileDto,UploadedParameterFileDto> uploadedFile();


}
