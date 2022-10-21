package net.datenwerke.rs.base.client.parameters.string.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Integer;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.DatatypeDto;
import net.datenwerke.rs.core.client.parameters.dto.pa.ParameterDefinitionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.parameters.string.TextParameterDefinition.class)
public interface TextParameterDefinitionDtoPA extends ParameterDefinitionDtoPA {


	public static final TextParameterDefinitionDtoPA INSTANCE = GWT.create(TextParameterDefinitionDtoPA.class);


	/* Properties */
	public ValueProvider<TextParameterDefinitionDto,String> defaultValue();
	public ValueProvider<TextParameterDefinitionDto,Integer> height();
	public ValueProvider<TextParameterDefinitionDto,Boolean> returnNullWhenEmpty();
	public ValueProvider<TextParameterDefinitionDto,DatatypeDto> returnType();
	public ValueProvider<TextParameterDefinitionDto,String> validatorRegex();
	public ValueProvider<TextParameterDefinitionDto,Integer> width();


}
