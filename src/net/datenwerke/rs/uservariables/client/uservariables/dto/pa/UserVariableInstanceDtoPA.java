package net.datenwerke.rs.uservariables.client.uservariables.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance.class)
public interface UserVariableInstanceDtoPA extends PropertyAccess<UserVariableInstanceDto> {


	public static final UserVariableInstanceDtoPA INSTANCE = GWT.create(UserVariableInstanceDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<UserVariableInstanceDto> dtoId();

	/* Properties */
	public ValueProvider<UserVariableInstanceDto,UserVariableDefinitionDto> definition();
	public ValueProvider<UserVariableInstanceDto,AbstractUserManagerNodeDto> folk();
	public ValueProvider<UserVariableInstanceDto,Long> id();


}
