package net.datenwerke.rs.base.client.parameters.datetime.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import java.util.Date;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterInstanceDtoDec;
import net.datenwerke.rs.core.client.parameters.dto.pa.ParameterInstanceDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterInstance.class)
public interface DateTimeParameterInstanceDtoPA extends ParameterInstanceDtoPA {


	public static final DateTimeParameterInstanceDtoPA INSTANCE = GWT.create(DateTimeParameterInstanceDtoPA.class);


	/* Properties */
	public ValueProvider<DateTimeParameterInstanceDto,String> formula();
	public ValueProvider<DateTimeParameterInstanceDto,String> strValue();
	public ValueProvider<DateTimeParameterInstanceDto,Date> value();


}
