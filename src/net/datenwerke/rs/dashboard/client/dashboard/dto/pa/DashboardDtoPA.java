package net.datenwerke.rs.dashboard.client.dashboard.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LayoutTypeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dashboard.service.dashboard.entities.Dashboard.class)
public interface DashboardDtoPA extends PropertyAccess<DashboardDto> {


	public static final DashboardDtoPA INSTANCE = GWT.create(DashboardDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<DashboardDto> dtoId();

	/* Properties */
	public ValueProvider<DashboardDto,List<DadgetDto>> dadgets();
	public ValueProvider<DashboardDto,String> description();
	public ValueProvider<DashboardDto,Long> id();
	public ValueProvider<DashboardDto,LayoutTypeDto> layout();
	public ValueProvider<DashboardDto,Integer> n();
	public ValueProvider<DashboardDto,String> name();
	public ValueProvider<DashboardDto,Boolean> primary();
	public ValueProvider<DashboardDto,Long> reloadInterval();
	public ValueProvider<DashboardDto,Boolean> singlePage();


}
