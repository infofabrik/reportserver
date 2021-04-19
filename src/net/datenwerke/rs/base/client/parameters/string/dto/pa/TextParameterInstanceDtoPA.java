package net.datenwerke.rs.base.client.parameters.string.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.dto.pa.ParameterInstanceDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.parameters.string.TextParameterInstance.class)
public interface TextParameterInstanceDtoPA extends ParameterInstanceDtoPA {


	public static final TextParameterInstanceDtoPA INSTANCE = GWT.create(TextParameterInstanceDtoPA.class);


	/* Properties */
	public ValueProvider<TextParameterInstanceDto,String> value();


}
