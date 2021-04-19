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
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardContainerDtoDec;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dashboard;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardContainer;
import net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.DashboardContainer2DtoGenerator;

/**
 * Poso2DtoGenerator for DashboardContainer
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DashboardContainer2DtoGenerator implements Poso2DtoGenerator<DashboardContainer,DashboardContainerDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DashboardContainer2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DashboardContainerDtoDec instantiateDto(DashboardContainer poso)  {
		DashboardContainerDtoDec dto = new DashboardContainerDtoDec();
		return dto;
	}

	public DashboardContainerDtoDec createDto(DashboardContainer poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DashboardContainerDtoDec dto = new DashboardContainerDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set dashboards */
			final List<DashboardDto> col_dashboards = new ArrayList<DashboardDto>();
			if( null != poso.getDashboards()){
				for(Dashboard refPoso : poso.getDashboards()){
					final Object tmpDtoDashboardDtogetDashboards = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_dashboards.add((DashboardDto) tmpDtoDashboardDtogetDashboards);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDashboardDtogetDashboards, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (dashboards)");
							int tmp_index = col_dashboards.indexOf(tmpDtoDashboardDtogetDashboards);
							col_dashboards.set(tmp_index,(DashboardDto) dto);
						}
					});
				}
				dto.setDashboards(col_dashboards);
			}

			/*  set id */
			dto.setId(poso.getId() );

		}

		return dto;
	}


}
