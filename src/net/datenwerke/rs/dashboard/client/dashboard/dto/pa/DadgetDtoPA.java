package net.datenwerke.rs.dashboard.client.dashboard.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DadgetDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget.class)
public interface DadgetDtoPA extends PropertyAccess<DadgetDto> {


	public static final DadgetDtoPA INSTANCE = GWT.create(DadgetDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<DadgetDto> dtoId();

	/* Properties */
	public ValueProvider<DadgetDto,Integer> col();
	public ValueProvider<DadgetDto,DadgetContainerDto> container();
	public ValueProvider<DadgetDto,Integer> height();
	public ValueProvider<DadgetDto,Long> id();
	public ValueProvider<DadgetDto,Integer> n();
	public ValueProvider<DadgetDto,Set<ParameterInstanceDto>> parameterInstances();
	public ValueProvider<DadgetDto,Long> reloadInterval();


}
