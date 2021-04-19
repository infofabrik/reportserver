package net.datenwerke.rs.crystal.client.crystal.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.crystal.client.crystal.CrystalUiService;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportVariantDto;
import net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportDtoDec;
import net.datenwerke.rs.crystal.client.crystal.dto.decorator.CrystalReportVariantDtoDec;
import net.datenwerke.rs.crystal.client.crystal.locale.CrystalMessages;
import net.datenwerke.rs.crystal.client.crystal.ui.CrystalReportForm;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class CrystalReportConfigHooker implements ReportTypeConfigHook {
	
	private final Provider<CrystalReportForm> adminFormProvider;
	private final CrystalUiService crystalService;
	private final EnterpriseUiService enterpriseService;
	
	@Inject
	public CrystalReportConfigHooker(
			Provider<CrystalReportForm> adminFormProvider,
			CrystalUiService crystalService,
			EnterpriseUiService enterpriseService
	) {
		this.adminFormProvider = adminFormProvider;
		this.crystalService = crystalService;
		this.enterpriseService = enterpriseService;
	}

	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof CrystalReportDto;
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(ReportDto report) {
		return Collections.singleton(adminFormProvider.get());
	}

	@Override
	public Class<? extends ReportDto> getReportClass() {
		return CrystalReportDto.class;
	}

	@Override
	public ImageResource getReportIcon() {
		return BaseIcon.REPORT_CRYSTAL.toImageResource();
	}

	@Override
	public ImageResource getReportIconLarge() {
		return BaseIcon.REPORT_CRYSTAL.toImageResource(1);
	}

	@Override
	public ImageResource getReportLinkIcon() {
		return BaseIcon.REPORT_CRYSTAL_LINK.toImageResource();
	}

	@Override
	public ImageResource getReportLinkIconLarge() {
		return BaseIcon.REPORT_CRYSTAL_LINK.toImageResource(1);
	}

	@Override
	public String getReportName() {
		return CrystalMessages.INSTANCE.reportTypeName();
	}

	@Override
	public Class<? extends ReportDto> getReportVariantClass() {
		return CrystalReportVariantDto.class;
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
		return new CrystalReportDtoDec();
	}

	@Override
	public ReportDto instantiateReportVariant() {
		return new CrystalReportVariantDtoDec();
	}
	
	@Override
	public boolean isAvailable() {
		return crystalService.isAvailable() && enterpriseService.isEnterprise();
	}
}
