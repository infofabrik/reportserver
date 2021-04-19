package net.datenwerke.rs.dashboard.client.dashboard.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardContainerDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardContainer.class)
public interface DashboardContainerDtoPA extends PropertyAccess<DashboardContainerDto> {


	public static final DashboardContainerDtoPA INSTANCE = GWT.create(DashboardContainerDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<DashboardContainerDto> dtoId();

	/* Properties */
	public ValueProvider<DashboardContainerDto,List<DashboardDto>> dashboards();
	public ValueProvider<DashboardContainerDto,Long> id();


}
