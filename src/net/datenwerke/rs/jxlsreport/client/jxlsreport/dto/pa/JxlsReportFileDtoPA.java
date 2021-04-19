package net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportFile.class)
public interface JxlsReportFileDtoPA extends PropertyAccess<JxlsReportFileDto> {


	public static final JxlsReportFileDtoPA INSTANCE = GWT.create(JxlsReportFileDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<JxlsReportFileDto> dtoId();

	/* Properties */
	public ValueProvider<JxlsReportFileDto,Long> id();
	public ValueProvider<JxlsReportFileDto,String> name();


}
