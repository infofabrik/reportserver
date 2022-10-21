package net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.ext.service.parameters.fileselection.UploadedParameterFile.class)
public interface UploadedParameterFileDtoPA extends PropertyAccess<UploadedParameterFileDto> {


	public static final UploadedParameterFileDtoPA INSTANCE = GWT.create(UploadedParameterFileDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<UploadedParameterFileDto> dtoId();

	/* Properties */
	public ValueProvider<UploadedParameterFileDto,Long> id();


}
