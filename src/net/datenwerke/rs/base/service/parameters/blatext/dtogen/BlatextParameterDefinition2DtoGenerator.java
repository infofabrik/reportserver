package net.datenwerke.rs.base.service.parameters.blatext.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.service.parameters.blatext.BlatextParameterDefinition;
import net.datenwerke.rs.base.service.parameters.blatext.dtogen.BlatextParameterDefinition2DtoGenerator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

/**
 * Poso2DtoGenerator for BlatextParameterDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class BlatextParameterDefinition2DtoGenerator implements Poso2DtoGenerator<BlatextParameterDefinition,BlatextParameterDefinitionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public BlatextParameterDefinition2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public BlatextParameterDefinitionDto instantiateDto(BlatextParameterDefinition poso)  {
		BlatextParameterDefinitionDto dto = new BlatextParameterDefinitionDto();
		return dto;
	}

	public BlatextParameterDefinitionDto createDto(BlatextParameterDefinition poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final BlatextParameterDefinitionDto dto = new BlatextParameterDefinitionDto();
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

			/*  set hidden */
			dto.setHidden(poso.isHidden() );

			/*  set labelWidth */
			dto.setLabelWidth(poso.getLabelWidth() );

			/*  set mandatory */
			dto.setMandatory(poso.isMandatory() );

			/*  set n */
			dto.setN(poso.getN() );

			/*  set value */
			dto.setValue(new HtmlPolicyBuilder().allowCommonInlineFormattingElements().allowCommonBlockElements().allowStyling().allowAttributes("size", "color").onElements("font").toFactory().sanitize(poso.getValue() ));

		}

		return dto;
	}


}
