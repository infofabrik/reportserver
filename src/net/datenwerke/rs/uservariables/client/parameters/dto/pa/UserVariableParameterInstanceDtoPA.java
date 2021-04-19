package net.datenwerke.rs.uservariables.client.parameters.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.parameters.dto.pa.ParameterInstanceDtoPA;
import net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterInstanceDto;
import net.datenwerke.rs.uservariables.client.parameters.dto.decorator.UserVariableParameterInstanceDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterInstance.class)
public interface UserVariableParameterInstanceDtoPA extends ParameterInstanceDtoPA {


	public static final UserVariableParameterInstanceDtoPA INSTANCE = GWT.create(UserVariableParameterInstanceDtoPA.class);


	/* Properties */
	public ValueProvider<UserVariableParameterInstanceDto,String> value();


}
