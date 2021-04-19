package net.datenwerke.rs.core.client.reportmanager.dto.reports.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty.class)
public interface ReportPropertyDtoPA extends PropertyAccess<ReportPropertyDto> {


	public static final ReportPropertyDtoPA INSTANCE = GWT.create(ReportPropertyDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<ReportPropertyDto> dtoId();

	/* Properties */
	public ValueProvider<ReportPropertyDto,Long> id();
	public ValueProvider<ReportPropertyDto,String> name();


}
