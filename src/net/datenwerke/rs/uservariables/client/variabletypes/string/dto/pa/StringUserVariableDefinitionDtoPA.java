package net.datenwerke.rs.uservariables.client.variabletypes.string.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Integer;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.uservariables.client.uservariables.dto.pa.UserVariableDefinitionDtoPA;
import net.datenwerke.rs.uservariables.client.uservariables.locale.UserVariablesMessages;
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.uservariables.service.variabletypes.string.StringUserVariableDefinition.class)
public interface StringUserVariableDefinitionDtoPA extends UserVariableDefinitionDtoPA {


	public static final StringUserVariableDefinitionDtoPA INSTANCE = GWT.create(StringUserVariableDefinitionDtoPA.class);


	/* Properties */
	public ValueProvider<StringUserVariableDefinitionDto,Integer> height();
	public ValueProvider<StringUserVariableDefinitionDto,Integer> width();


}
