package net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities.post;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.core.service.reportmanager.helpers.ReportIconHelper;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.decorator.ExecutedReportFileReferenceDtoDec;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities.ExecutedReportFileReference;

import com.google.inject.Inject;

public class ExecuteReportFileReference2DtoPost implements
		Poso2DtoPostProcessor<ExecutedReportFileReference, ExecutedReportFileReferenceDto> {

	private final ReportIconHelper iconHelper;

	@Inject
	public ExecuteReportFileReference2DtoPost(ReportIconHelper iconHelper) {
		this.iconHelper = iconHelper;
	}

	@Override
	public void dtoCreated(ExecutedReportFileReference ref,
			ExecutedReportFileReferenceDto refDto) {
		if(null != ref.getOutputFormat()){
			((ExecutedReportFileReferenceDtoDec)refDto).setIconStr(iconHelper.getSmallIconForOutputFormat(ref.getOutputFormat()));
			((ExecutedReportFileReferenceDtoDec)refDto).setTypeStr(ref.getOutputFormat().toLowerCase());
		}
		
	}

	@Override
	public void dtoInstantiated(ExecutedReportFileReference arg0,
			ExecutedReportFileReferenceDto arg1) {
		
	}

}
