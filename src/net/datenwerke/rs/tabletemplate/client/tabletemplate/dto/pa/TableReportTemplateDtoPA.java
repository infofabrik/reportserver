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
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportTemplate.class)
public interface TableReportTemplateDtoPA extends PropertyAccess<TableReportTemplateDto> {


	public static final TableReportTemplateDtoPA INSTANCE = GWT.create(TableReportTemplateDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<TableReportTemplateDto> dtoId();

	/* Properties */
	public ValueProvider<TableReportTemplateDto,String> contentType();
	public ValueProvider<TableReportTemplateDto,String> description();
	public ValueProvider<TableReportTemplateDto,String> fileExtension();
	public ValueProvider<TableReportTemplateDto,Long> id();
	public ValueProvider<TableReportTemplateDto,String> key();
	public ValueProvider<TableReportTemplateDto,String> name();
	public ValueProvider<TableReportTemplateDto,String> templateType();
	public ValueProvider<TableReportTemplateDto,Long> temporaryId();


}
