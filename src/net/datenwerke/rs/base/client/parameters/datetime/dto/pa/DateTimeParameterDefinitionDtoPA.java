package net.datenwerke.rs.base.client.parameters.datetime.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import java.util.Date;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.datetime.dto.ModeDto;
import net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterDefinitionDtoDec;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.core.client.parameters.dto.pa.ParameterDefinitionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterDefinition.class)
public interface DateTimeParameterDefinitionDtoPA extends ParameterDefinitionDtoPA {


	public static final DateTimeParameterDefinitionDtoPA INSTANCE = GWT.create(DateTimeParameterDefinitionDtoPA.class);


	/* Properties */
	public ValueProvider<DateTimeParameterDefinitionDto,Date> defaultValue();
	public ValueProvider<DateTimeParameterDefinitionDto,String> formula();
	public ValueProvider<DateTimeParameterDefinitionDto,ModeDto> mode();
	public ValueProvider<DateTimeParameterDefinitionDto,Boolean> useNowAsDefault();


}
