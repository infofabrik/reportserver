package net.datenwerke.rs.uservariables.client.parameters.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.parameters.dto.pa.ParameterDefinitionDtoPA;
import net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.locale.UserVariablesMessages;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterDefinition.class)
public interface UserVariableParameterDefinitionDtoPA extends ParameterDefinitionDtoPA {


	public static final UserVariableParameterDefinitionDtoPA INSTANCE = GWT.create(UserVariableParameterDefinitionDtoPA.class);


	/* Properties */
	public ValueProvider<UserVariableParameterDefinitionDto,UserVariableDefinitionDto> userVariableDefinition();


}
