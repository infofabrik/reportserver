package net.datenwerke.rs.scripting.service.scripting.extensions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.scripting.client.scripting.dto.AddMenuEntryExtensionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddMenuEntryExtension;
import net.datenwerke.rs.scripting.service.scripting.extensions.DisplayCondition;
import net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.AddMenuEntryExtension2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for AddMenuEntryExtension
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AddMenuEntryExtension2DtoGenerator implements Poso2DtoGenerator<AddMenuEntryExtension,AddMenuEntryExtensionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public AddMenuEntryExtension2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public AddMenuEntryExtensionDto instantiateDto(AddMenuEntryExtension poso)  {
		AddMenuEntryExtensionDto dto = new AddMenuEntryExtensionDto();
		return dto;
	}

	public AddMenuEntryExtensionDto createDto(AddMenuEntryExtension poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final AddMenuEntryExtensionDto dto = new AddMenuEntryExtensionDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set arguments */
			dto.setArguments(StringEscapeUtils.escapeXml(StringUtils.left(poso.getArguments(),8192)));

			/*  set displayConditions */
			final List<DisplayConditionDto> col_displayConditions = new ArrayList<DisplayConditionDto>();
			if( null != poso.getDisplayConditions()){
				for(DisplayCondition refPoso : poso.getDisplayConditions()){
					final Object tmpDtoDisplayConditionDtogetDisplayConditions = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_displayConditions.add((DisplayConditionDto) tmpDtoDisplayConditionDtogetDisplayConditions);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDisplayConditionDtogetDisplayConditions, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (displayConditions)");
							int tmp_index = col_displayConditions.indexOf(tmpDtoDisplayConditionDtogetDisplayConditions);
							col_displayConditions.set(tmp_index,(DisplayConditionDto) dto);
						}
					});
				}
				dto.setDisplayConditions(col_displayConditions);
			}

			/*  set icon */
			dto.setIcon(StringEscapeUtils.escapeXml(StringUtils.left(poso.getIcon(),8192)));

			/*  set javaScript */
			dto.setJavaScript(poso.getJavaScript() );

			/*  set label */
			dto.setLabel(StringEscapeUtils.escapeXml(StringUtils.left(poso.getLabel(),8192)));

			/*  set menuName */
			dto.setMenuName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getMenuName(),8192)));

			/*  set scriptLocation */
			dto.setScriptLocation(StringEscapeUtils.escapeXml(StringUtils.left(poso.getScriptLocation(),8192)));

			/*  set subMenuEntries */
			final List<AddMenuEntryExtensionDto> col_subMenuEntries = new ArrayList<AddMenuEntryExtensionDto>();
			if( null != poso.getSubMenuEntries()){
				for(AddMenuEntryExtension refPoso : poso.getSubMenuEntries()){
					final Object tmpDtoAddMenuEntryExtensionDtogetSubMenuEntries = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_subMenuEntries.add((AddMenuEntryExtensionDto) tmpDtoAddMenuEntryExtensionDtogetSubMenuEntries);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoAddMenuEntryExtensionDtogetSubMenuEntries, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (subMenuEntries)");
							int tmp_index = col_subMenuEntries.indexOf(tmpDtoAddMenuEntryExtensionDtogetSubMenuEntries);
							col_subMenuEntries.set(tmp_index,(AddMenuEntryExtensionDto) dto);
						}
					});
				}
				dto.setSubMenuEntries(col_subMenuEntries);
			}

		}

		return dto;
	}


}
