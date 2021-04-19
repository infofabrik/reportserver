package net.datenwerke.rs.birt.client.reportengines.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.birt.service.reportengine.entities.BirtReportFile.class)
public interface BirtReportFileDtoPA extends PropertyAccess<BirtReportFileDto> {


	public static final BirtReportFileDtoPA INSTANCE = GWT.create(BirtReportFileDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<BirtReportFileDto> dtoId();

	/* Properties */
	public ValueProvider<BirtReportFileDto,Long> id();
	public ValueProvider<BirtReportFileDto,String> name();


}
