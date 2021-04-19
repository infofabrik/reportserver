package net.datenwerke.rs.uservariables.client.uservariables.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition.class)
public interface UserVariableDefinitionDtoPA extends PropertyAccess<UserVariableDefinitionDto> {


	public static final UserVariableDefinitionDtoPA INSTANCE = GWT.create(UserVariableDefinitionDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<UserVariableDefinitionDto> dtoId();

	/* Properties */
	public ValueProvider<UserVariableDefinitionDto,String> description();
	public ValueProvider<UserVariableDefinitionDto,Long> id();
	public ValueProvider<UserVariableDefinitionDto,String> name();


}
