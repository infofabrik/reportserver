package net.datenwerke.rs.saiku.client.saiku.hookers;

import java.util.Collection;
import java.util.Collections;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportVariantDto;
import net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportDtoDec;
import net.datenwerke.rs.saiku.client.saiku.dto.decorator.SaikuReportVariantDtoDec;
import net.datenwerke.rs.saiku.client.saiku.locale.SaikuMessages;
import net.datenwerke.rs.saiku.client.saiku.ui.SaikuReportForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class SaikuReportConfigHooker implements ReportTypeConfigHook {
	
	private final Provider<SaikuReportForm> adminFormProvider;
	
	@Inject
	public SaikuReportConfigHooker(
			Provider<SaikuReportForm> adminFormProvider
	) {
		this.adminFormProvider = adminFormProvider;
	}

	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof SaikuReportDto;
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(ReportDto report) {
		return Collections.singleton(adminFormProvider.get());
	}

	@Override
	public Class<? extends ReportDto> getReportClass() {
		return SaikuReportDto.class;
	}

	@Override
	public ImageResource getReportIcon() {
		return BaseIcon.REPORT_SAIKU.toImageResource();
	}

	@Override
	public ImageResource getReportIconLarge() {
		return BaseIcon.REPORT_SAIKU.toImageResource(1);
	}

	@Override
	public ImageResource getReportLinkIcon() {
		return BaseIcon.REPORT_SAIKU_LINK.toImageResource();
	}

	@Override
	public ImageResource getReportLinkIconLarge() {
		return BaseIcon.REPORT_SAIKU_LINK.toImageResource(1);
	}

	@Override
	public String getReportName() {
		return SaikuMessages.INSTANCE.reportTypeName();
	}

	@Override
	public Class<? extends ReportDto> getReportVariantClass() {
		return SaikuReportVariantDto.class;
	}

	@Override
	public ImageResource getReportVariantIcon() {
		return BaseIcon.REPORT_USER.toImageResource();
	}

	@Override
	public ImageResource getReportVariantIconLarge() {
		return BaseIcon.REPORT_USER.toImageResource();
	}

	@Override
	public ReportDto instantiateReport() {
		return new SaikuReportDtoDec();
	}

	@Override
	public ReportDto instantiateReportVariant() {
		return new SaikuReportVariantDtoDec();
	}
	
	@Override
	public boolean isAvailable() {
		return true;
	}
}
