package net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.parameters.dto.pa.ParameterInstanceDtoPA;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.scriptreport.service.scriptreport.parameter.ScriptParameterInstance.class)
public interface ScriptParameterInstanceDtoPA extends ParameterInstanceDtoPA {


	public static final ScriptParameterInstanceDtoPA INSTANCE = GWT.create(ScriptParameterInstanceDtoPA.class);


	/* Properties */
	public ValueProvider<ScriptParameterInstanceDto,String> value();


}
