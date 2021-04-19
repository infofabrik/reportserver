package net.datenwerke.rs.birt.client.reportengines.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.birt.client.reportengines.dto.CompiledHTMLBirtReportDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.birt.service.reportengine.output.object.CompiledHTMLBirtReport.class)
public interface CompiledHTMLBirtReportDtoPA extends PropertyAccess<CompiledHTMLBirtReportDto> {


	public static final CompiledHTMLBirtReportDtoPA INSTANCE = GWT.create(CompiledHTMLBirtReportDtoPA.class);


	/* Properties */
	public ValueProvider<CompiledHTMLBirtReportDto,String> report();


}
