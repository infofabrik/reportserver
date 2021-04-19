package net.datenwerke.rs.grideditor.service.grideditor.definition.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.EditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorColumnConfigDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.ValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorColumnConfigDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorColumnConfig;
import net.datenwerke.rs.grideditor.service.grideditor.definition.dtogen.GridEditorColumnConfig2DtoGenerator;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.Validator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for GridEditorColumnConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class GridEditorColumnConfig2DtoGenerator implements Poso2DtoGenerator<GridEditorColumnConfig,GridEditorColumnConfigDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public GridEditorColumnConfig2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public GridEditorColumnConfigDtoDec instantiateDto(GridEditorColumnConfig poso)  {
		GridEditorColumnConfigDtoDec dto = new GridEditorColumnConfigDtoDec();
		return dto;
	}

	public GridEditorColumnConfigDtoDec createDto(GridEditorColumnConfig poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final GridEditorColumnConfigDtoDec dto = new GridEditorColumnConfigDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set defaultCaseSensitive */
			dto.setDefaultCaseSensitive(poso.isDefaultCaseSensitive() );

			/*  set defaultValue */
			Object tmpDtoGridEditorRecordEntryDtogetDefaultValue = dtoServiceProvider.get().createDto(poso.getDefaultValue(), here, referenced);
			dto.setDefaultValue((GridEditorRecordEntryDto)tmpDtoGridEditorRecordEntryDtogetDefaultValue);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoGridEditorRecordEntryDtogetDefaultValue, poso.getDefaultValue(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDefaultValue((GridEditorRecordEntryDto)refDto);
				}
			});

			/*  set displayName */
			dto.setDisplayName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDisplayName(),8192)));

			/*  set editable */
			dto.setEditable(poso.isEditable() );

			/*  set editor */
			Object tmpDtoEditorDtogetEditor = dtoServiceProvider.get().createDto(poso.getEditor(), here, referenced);
			dto.setEditor((EditorDto)tmpDtoEditorDtogetEditor);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoEditorDtogetEditor, poso.getEditor(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setEditor((EditorDto)refDto);
				}
			});

			/*  set enforceCaseSensitivity */
			dto.setEnforceCaseSensitivity(poso.isEnforceCaseSensitivity() );

			/*  set filterable */
			dto.setFilterable(poso.isFilterable() );

			/*  set hidden */
			dto.setHidden(poso.isHidden() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

			/*  set order */
			Object tmpDtoOrderDtogetOrder = dtoServiceProvider.get().createDto(poso.getOrder(), referenced, referenced);
			dto.setOrder((OrderDto)tmpDtoOrderDtogetOrder);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoOrderDtogetOrder, poso.getOrder(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setOrder((OrderDto)refDto);
				}
			});

			/*  set primaryKey */
			dto.setPrimaryKey(poso.isPrimaryKey() );

			/*  set sortable */
			dto.setSortable(poso.isSortable() );

			/*  set type */
			dto.setType(poso.getType() );

			/*  set validators */
			final List<ValidatorDto> col_validators = new ArrayList<ValidatorDto>();
			if( null != poso.getValidators()){
				for(Validator refPoso : poso.getValidators()){
					final Object tmpDtoValidatorDtogetValidators = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_validators.add((ValidatorDto) tmpDtoValidatorDtogetValidators);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoValidatorDtogetValidators, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (validators)");
							int tmp_index = col_validators.indexOf(tmpDtoValidatorDtogetValidators);
							col_validators.set(tmp_index,(ValidatorDto) dto);
						}
					});
				}
				dto.setValidators(col_validators);
			}

			/*  set width */
			dto.setWidth(poso.getWidth() );

		}

		return dto;
	}


}
