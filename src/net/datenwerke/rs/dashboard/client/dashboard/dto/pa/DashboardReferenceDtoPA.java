package net.datenwerke.rs.dashboard.client.dashboard.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardReferenceDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DashboardDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardReference.class)
public interface DashboardReferenceDtoPA extends DashboardDtoPA {


	public static final DashboardReferenceDtoPA INSTANCE = GWT.create(DashboardReferenceDtoPA.class);


	/* Properties */
	public ValueProvider<DashboardReferenceDto,DashboardNodeDto> dashboardNode();


}
