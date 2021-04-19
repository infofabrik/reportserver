package net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.tabletemplate.service.tabletemplate.config.RECTableTemplate.class)
public interface RECTableTemplateDtoPA extends PropertyAccess<RECTableTemplateDto> {


	public static final RECTableTemplateDtoPA INSTANCE = GWT.create(RECTableTemplateDtoPA.class);


	/* Properties */
	public ValueProvider<RECTableTemplateDto,Long> templateId();
	public ValueProvider<RECTableTemplateDto,String> templateKey();
	public ValueProvider<RECTableTemplateDto,Long> temporaryId();


}
