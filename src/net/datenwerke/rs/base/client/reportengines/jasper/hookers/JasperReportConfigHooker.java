package net.datenwerke.rs.base.client.reportengines.jasper.hookers;

import java.util.Collection;
import java.util.Collections;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportVariantDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportDtoDec;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator.JasperReportVariantDtoDec;
import net.datenwerke.rs.base.client.reportengines.jasper.locale.JasperMessages;
import net.datenwerke.rs.base.client.reportengines.jasper.ui.JasperReportForm;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class JasperReportConfigHooker implements ReportTypeConfigHook {

	private final Provider<JasperReportForm> adminViewProvider;
	
	@Inject
	public JasperReportConfigHooker(
		Provider<JasperReportForm> adminViewProvider
		) {

		/* store objects */
		this.adminViewProvider = adminViewProvider;
	}
	
	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof JasperReportDto;
	}

	@Override
	public Class<? extends ReportDto> getReportClass() {
		return JasperReportDto.class;
	}

	@Override
	public Class<? extends ReportDto> getReportVariantClass() {
		return JasperReportVariantDto.class;
	}

	@Override
	public ImageResource getReportIcon() {
		return BaseIcon.REPORT_JASPER.toImageResource();
	}

	@Override
	public ImageResource getReportVariantIcon() {
		return BaseIcon.REPORT_JASPER.toImageResource();
	}

	@Override
	public ImageResource getReportIconLarge() {
		return BaseIcon.REPORT_JASPER.toImageResource(1);
	}

	@Override
	public ImageResource getReportVariantIconLarge() {
		return BaseIcon.REPORT_JASPER.toImageResource(1);
	}

	@Override
	public ImageResource getReportLinkIcon() {
		return BaseIcon.REPORT_JASPER_LINK.toImageResource();
	}

	@Override
	public ImageResource getReportLinkIconLarge() {
		return BaseIcon.REPORT_JASPER_LINK.toImageResource(1);
	}

	@Override
	public ReportDto instantiateReport() {
		return new JasperReportDtoDec();
	}
	
	@Override
	public ReportDto instantiateReportVariant() {
		return new JasperReportVariantDtoDec();
	}

	@Override
	public String getReportName() {
		return JasperMessages.INSTANCE.reportTypeName();
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(ReportDto report) {
		return Collections.singleton(adminViewProvider.get());
	}

	@Override
	public boolean isAvailable() {
		return true;
	}

}
