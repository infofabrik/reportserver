package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.decorator.ExecutedReportFileReferenceDtoDec;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa.TsDiskGeneralReferenceDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities.ExecutedReportFileReference.class)
public interface ExecutedReportFileReferenceDtoPA extends TsDiskGeneralReferenceDtoPA {


	public static final ExecutedReportFileReferenceDtoPA INSTANCE = GWT.create(ExecutedReportFileReferenceDtoPA.class);


	/* Properties */
	public ValueProvider<ExecutedReportFileReferenceDto,String> outputFormat();
	public ValueProvider<ExecutedReportFileReferenceDto,String> iconStr();
	public ValueProvider<ExecutedReportFileReferenceDto,String> typeStr();


}
