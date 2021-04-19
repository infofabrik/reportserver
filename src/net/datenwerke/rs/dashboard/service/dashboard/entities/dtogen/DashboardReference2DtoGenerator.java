package net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardReferenceDtoDec;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardReference;
import net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.DashboardReference2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for DashboardReference
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DashboardReference2DtoGenerator implements Poso2DtoGenerator<DashboardReference,DashboardReferenceDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DashboardReference2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DashboardReferenceDtoDec instantiateDto(DashboardReference poso)  {
		DashboardReferenceDtoDec dto = new DashboardReferenceDtoDec();
		return dto;
	}

	public DashboardReferenceDtoDec createDto(DashboardReference poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DashboardReferenceDtoDec dto = new DashboardReferenceDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set dadgets */
			final List<DadgetDto> col_dadgets = new ArrayList<DadgetDto>();
			if( null != poso.getDadgets()){
				for(Dadget refPoso : poso.getDadgets()){
					final Object tmpDtoDadgetDtogetDadgets = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_dadgets.add((DadgetDto) tmpDtoDadgetDtogetDadgets);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDadgetDtogetDadgets, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (dadgets)");
							int tmp_index = col_dadgets.indexOf(tmpDtoDadgetDtogetDadgets);
							col_dadgets.set(tmp_index,(DadgetDto) dto);
						}
					});
				}
				dto.setDadgets(col_dadgets);
			}

			/*  set dashboardNode */
			Object tmpDtoDashboardNodeDtogetDashboardNode = dtoServiceProvider.get().createDto(poso.getDashboardNode(), here, referenced);
			dto.setDashboardNode((DashboardNodeDto)tmpDtoDashboardNodeDtogetDashboardNode);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDashboardNodeDtogetDashboardNode, poso.getDashboardNode(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDashboardNode((DashboardNodeDto)refDto);
				}
			});

			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set layout */
			Object tmpDtoLayoutTypeDtogetLayout = dtoServiceProvider.get().createDto(poso.getLayout(), referenced, referenced);
			dto.setLayout((LayoutTypeDto)tmpDtoLayoutTypeDtogetLayout);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoLayoutTypeDtogetLayout, poso.getLayout(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setLayout((LayoutTypeDto)refDto);
				}
			});

			/*  set n */
			dto.setN(poso.getN() );

			/*  set primary */
			dto.setPrimary(poso.isPrimary() );

			/*  set reloadInterval */
			dto.setReloadInterval(poso.getReloadInterval() );

			/*  set singlePage */
			dto.setSinglePage(poso.isSinglePage() );

		}

		return dto;
	}


}
