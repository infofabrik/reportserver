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
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata.class)
public interface ReportMetadataDtoPA extends PropertyAccess<ReportMetadataDto> {


	public static final ReportMetadataDtoPA INSTANCE = GWT.create(ReportMetadataDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<ReportMetadataDto> dtoId();

	/* Properties */
	public ValueProvider<ReportMetadataDto,Long> id();
	public ValueProvider<ReportMetadataDto,String> name();
	public ValueProvider<ReportMetadataDto,String> value();


}
