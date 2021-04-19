package net.datenwerke.rs.birt.client.reportengines.hookers;

import java.util.Collection;
import java.util.Collections;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportVariantDto;
import net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportDtoDec;
import net.datenwerke.rs.birt.client.reportengines.dto.decorator.BirtReportVariantDtoDec;
import net.datenwerke.rs.birt.client.reportengines.locale.BirtMessages;
import net.datenwerke.rs.birt.client.reportengines.ui.BirtReportForm;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class BirtReportConfigHooker implements ReportTypeConfigHook {
	
	private final Provider<BirtReportForm> adminFormProvider;
	
	@Inject
	public BirtReportConfigHooker(
			Provider<BirtReportForm> adminFormProvider
	) {
		this.adminFormProvider = adminFormProvider;
	}

	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof BirtReportDto;
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(ReportDto report) {
		return Collections.singleton(adminFormProvider.get());
	}

	@Override
	public Class<? extends ReportDto> getReportClass() {
		return BirtReportDto.class;
	}

	@Override
	public ImageResource getReportIcon() {
		return BaseIcon.REPORT_BIRT.toImageResource();
	}

	@Override
	public ImageResource getReportIconLarge() {
		return BaseIcon.REPORT_BIRT.toImageResource(1);
	}

	@Override
	public ImageResource getReportLinkIcon() {
		return BaseIcon.REPORT_BIRT_LINK.toImageResource();
	}

	@Override
	public ImageResource getReportLinkIconLarge() {
		return BaseIcon.REPORT_BIRT_LINK.toImageResource(1);
	}

	@Override
	public String getReportName() {
		return BirtMessages.INSTANCE.reportTypeName();
	}

	@Override
	public Class<? extends ReportDto> getReportVariantClass() {
		return BirtReportVariantDto.class;
	}

	@Override
	public ImageResource getReportVariantIcon() {
		return BaseIcon.REPORT_USER.toImageResource();
	}

	@Override
	public ImageResource getReportVariantIconLarge() {
		return BaseIcon.REPORT_USER.toImageResource(1);
	}

	@Override
	public ReportDto instantiateReport() {
		return new BirtReportDtoDec();
	}

	@Override
	public ReportDto instantiateReportVariant() {
		return new BirtReportVariantDtoDec();
	}
	
	@Override
	public boolean isAvailable() {
		return true;
	}
}
