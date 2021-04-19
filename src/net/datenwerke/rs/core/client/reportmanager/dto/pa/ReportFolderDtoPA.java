package net.datenwerke.rs.core.client.reportmanager.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.AbstractReportManagerNodeDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.core.service.reportmanager.entities.ReportFolder.class)
public interface ReportFolderDtoPA extends AbstractReportManagerNodeDtoPA {


	public static final ReportFolderDtoPA INSTANCE = GWT.create(ReportFolderDtoPA.class);


	/* Properties */
	public ValueProvider<ReportFolderDto,String> description();
	public ValueProvider<ReportFolderDto,String> name();
	public ValueProvider<ReportFolderDto,Boolean> isReportRoot();


}
