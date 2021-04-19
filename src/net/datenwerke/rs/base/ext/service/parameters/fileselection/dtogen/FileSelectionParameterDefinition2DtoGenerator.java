package net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterDefinition;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.FileSelectionParameterDefinition2DtoGenerator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for FileSelectionParameterDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class FileSelectionParameterDefinition2DtoGenerator implements Poso2DtoGenerator<FileSelectionParameterDefinition,FileSelectionParameterDefinitionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public FileSelectionParameterDefinition2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public FileSelectionParameterDefinitionDto instantiateDto(FileSelectionParameterDefinition poso)  {
		FileSelectionParameterDefinitionDto dto = new FileSelectionParameterDefinitionDto();
		return dto;
	}

	public FileSelectionParameterDefinitionDto createDto(FileSelectionParameterDefinition poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final FileSelectionParameterDefinitionDto dto = new FileSelectionParameterDefinitionDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

			/*  set key */
			dto.setKey(StringEscapeUtils.escapeXml(StringUtils.left(poso.getKey(),8192)));

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set allowDownload */
			dto.setAllowDownload(poso.isAllowDownload() );

			/*  set allowFileServerSelection */
			dto.setAllowFileServerSelection(poso.isAllowFileServerSelection() );

			/*  set allowFileUpload */
			dto.setAllowFileUpload(poso.isAllowFileUpload() );

			/*  set allowTeamSpaceSelection */
			dto.setAllowTeamSpaceSelection(poso.isAllowTeamSpaceSelection() );

			/*  set allowedFileExtensions */
			dto.setAllowedFileExtensions(StringEscapeUtils.escapeXml(StringUtils.left(poso.getAllowedFileExtensions(),8192)));

			/*  set dependsOn */
			final List<ParameterDefinitionDto> col_dependsOn = new ArrayList<ParameterDefinitionDto>();
			if( null != poso.getDependsOn()){
				for(ParameterDefinition refPoso : poso.getDependsOn()){
					final Object tmpDtoParameterDefinitionDtogetDependsOn = dtoServiceProvider.get().createDto(refPoso, referenced, referenced);
					col_dependsOn.add((ParameterDefinitionDto) tmpDtoParameterDefinitionDtogetDependsOn);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoParameterDefinitionDtogetDependsOn, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (dependsOn)");
							int tmp_index = col_dependsOn.indexOf(tmpDtoParameterDefinitionDtogetDependsOn);
							col_dependsOn.set(tmp_index,(ParameterDefinitionDto) dto);
						}
					});
				}
				dto.setDependsOn(col_dependsOn);
			}

			/*  set displayInline */
			dto.setDisplayInline(poso.isDisplayInline() );

			/*  set editable */
			dto.setEditable(poso.isEditable() );

			/*  set fileSizeString */
			dto.setFileSizeString(StringEscapeUtils.escapeXml(StringUtils.left(poso.getFileSizeString(),8192)));

			/*  set height */
			dto.setHeight(poso.getHeight() );

			/*  set hidden */
			dto.setHidden(poso.isHidden() );

			/*  set labelWidth */
			dto.setLabelWidth(poso.getLabelWidth() );

			/*  set mandatory */
			dto.setMandatory(poso.isMandatory() );

			/*  set maxNumberOfFiles */
			dto.setMaxNumberOfFiles(poso.getMaxNumberOfFiles() );

			/*  set minNumberOfFiles */
			dto.setMinNumberOfFiles(poso.getMinNumberOfFiles() );

			/*  set n */
			dto.setN(poso.getN() );

			/*  set width */
			dto.setWidth(poso.getWidth() );

			/*  set FileSize */
			dto.setFileSize(poso.getFileSize() );

		}

		return dto;
	}


}
