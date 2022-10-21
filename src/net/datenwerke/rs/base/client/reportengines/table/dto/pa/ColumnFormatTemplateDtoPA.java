package net.datenwerke.rs.base.client.reportengines.table.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTemplateDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnFormatDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatTemplate.class)
public interface ColumnFormatTemplateDtoPA extends ColumnFormatDtoPA {


	public static final ColumnFormatTemplateDtoPA INSTANCE = GWT.create(ColumnFormatTemplateDtoPA.class);


	/* Properties */
	public ValueProvider<ColumnFormatTemplateDto,String> template();


}
