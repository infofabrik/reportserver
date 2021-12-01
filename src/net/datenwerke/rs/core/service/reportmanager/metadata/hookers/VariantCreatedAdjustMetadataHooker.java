package net.datenwerke.rs.core.service.reportmanager.metadata.hookers;

import java.util.Set;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantCreatorHook;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;

public class VariantCreatedAdjustMetadataHooker implements VariantCreatorHook {
	
	private final EntityClonerService entityClonerService;
	private final ReportService reportService;

	@Inject
	public VariantCreatedAdjustMetadataHooker(EntityClonerService entityClonerService, ReportService reportService) {
		this.entityClonerService = entityClonerService;
		this.reportService = reportService;
	}
	
	@Override
	public void newVariantCreated(Report referenceReport, Report adjustedReport, Report variant) {

	}

	@Override
	public void temporaryVariantCreated(Report referenceReport, Report adjustedReport, Report variant) {

		if (null == variant.getOldTransientId())
			return;
		
		Report variantReport  = reportService.getReportById(variant.getOldTransientId());
		if (null == variantReport)
			return;
		
		Set<ReportMetadata> variantReportMetadata = variantReport.getReportMetadata();
		for (ReportMetadata variantMetadata: variantReportMetadata) {
			variant.addReportMetadata(entityClonerService.cloneEntity(variantMetadata));
		}
	}

}
