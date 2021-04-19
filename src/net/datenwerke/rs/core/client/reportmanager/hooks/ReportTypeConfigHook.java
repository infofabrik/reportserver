package net.datenwerke.rs.core.client.reportmanager.hooks;

import java.util.Collection;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

import com.google.gwt.resources.client.ImageResource;

public interface ReportTypeConfigHook extends Hook {

	Class<? extends ReportDto> getReportClass();
	Class<? extends ReportDto> getReportVariantClass();
	ReportDto instantiateReport();
	ReportDto instantiateReportVariant();
	String getReportName();
	
	ImageResource getReportIcon();
	ImageResource getReportVariantIcon();
	ImageResource getReportIconLarge();
	ImageResource getReportVariantIconLarge();
	ImageResource getReportLinkIcon();
	ImageResource getReportLinkIconLarge();
	boolean consumes(ReportDto report);
	Collection<? extends MainPanelView> getAdminViews(ReportDto report);
	
	boolean isAvailable();
	
	

	
}
