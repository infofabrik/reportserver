package net.datenwerke.rs.base.client.reportengines.jasper.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledCSVJasperReportDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledCSVJasperReport.class)
public interface CompiledCSVJasperReportDtoPA extends PropertyAccess<CompiledCSVJasperReportDto> {


	public static final CompiledCSVJasperReportDtoPA INSTANCE = GWT.create(CompiledCSVJasperReportDtoPA.class);


	/* Properties */
	public ValueProvider<CompiledCSVJasperReportDto,String> report();


}
