package net.datenwerke.rs.scriptreport.client.scriptreport.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportVariantDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportDtoDec;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.decorator.ScriptReportVariantDtoDec;
import net.datenwerke.rs.scriptreport.client.scriptreport.locale.ScriptReportMessages;
import net.datenwerke.rs.scriptreport.client.scriptreport.ui.ScriptReportForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ScriptReportConfigHooker implements ReportTypeConfigHook {
	
	private final Provider<ScriptReportForm> adminFormProvider;
	private final EnterpriseUiService enterpriseService;
	
	@Inject
	public ScriptReportConfigHooker(
			Provider<ScriptReportForm> adminFormProvider,
			EnterpriseUiService enterpriseService
	) {
		this.adminFormProvider = adminFormProvider;
		this.enterpriseService = enterpriseService;

	}

	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof ScriptReportDto;
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(ReportDto report) {
		return Collections.singleton(adminFormProvider.get());
	}

	@Override
	public Class<? extends ReportDto> getReportClass() {
		return ScriptReportDto.class;
	}

	@Override
	public ImageResource getReportIcon() {
		return BaseIcon.SCRIPT.toImageResource();
	}

	@Override
	public ImageResource getReportIconLarge() {
		return BaseIcon.SCRIPT.toImageResource(1);
	}

	@Override
	public ImageResource getReportLinkIcon() {
		return BaseIcon.SCRIPT_LINK.toImageResource();
	}

	@Override
	public ImageResource getReportLinkIconLarge() {
		return BaseIcon.SCRIPT_LINK.toImageResource(1);
	}

	@Override
	public String getReportName() {
		return ScriptReportMessages.INSTANCE.reportTypeName();
	}

	@Override
	public Class<? extends ReportDto> getReportVariantClass() {
		return ScriptReportVariantDto.class;
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
		return new ScriptReportDtoDec();
	}

	@Override
	public ReportDto instantiateReportVariant() {
		return new ScriptReportVariantDtoDec();
	}
	
	@Override
	public boolean isAvailable() {
		return enterpriseService.isEnterprise();
	}
}
