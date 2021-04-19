package net.datenwerke.rs.core.client.parameters.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.core.service.parameters.entities.ParameterInstance.class)
public interface ParameterInstanceDtoPA extends PropertyAccess<ParameterInstanceDto> {


	public static final ParameterInstanceDtoPA INSTANCE = GWT.create(ParameterInstanceDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<ParameterInstanceDto> dtoId();

	/* Properties */
	public ValueProvider<ParameterInstanceDto,ParameterDefinitionDto> definition();
	public ValueProvider<ParameterInstanceDto,Long> id();
	public ValueProvider<ParameterInstanceDto,Boolean> stillDefault();


}
