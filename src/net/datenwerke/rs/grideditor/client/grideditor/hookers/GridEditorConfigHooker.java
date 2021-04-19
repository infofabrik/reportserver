package net.datenwerke.rs.grideditor.client.grideditor.hookers;

import java.util.Collection;
import java.util.Collections;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportVariantDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorReportDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.locale.GridEditorMessages;
import net.datenwerke.rs.grideditor.client.grideditor.ui.GridEditorReportForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class GridEditorConfigHooker implements ReportTypeConfigHook {
	
	private final Provider<GridEditorReportForm> adminFormProvider;
	private EnterpriseUiService enterpriseService;
	
	@Inject
	public GridEditorConfigHooker(
			Provider<GridEditorReportForm> adminFormProvider,
			EnterpriseUiService enterpriseService
	) {
		this.adminFormProvider = adminFormProvider;
		this.enterpriseService = enterpriseService;
	}

	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof GridEditorReportDto;
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(ReportDto report) {
		return Collections.singleton(adminFormProvider.get());
	}

	@Override
	public Class<? extends ReportDto> getReportClass() {
		return GridEditorReportDto.class;
	}

	@Override
	public ImageResource getReportIcon() {
		return BaseIcon.REPORT_GE.toImageResource();
	}

	@Override
	public ImageResource getReportIconLarge() {
		return BaseIcon.REPORT_GE.toImageResource(1);
	}

	@Override
	public ImageResource getReportLinkIcon() {
		return BaseIcon.REPORT_GE_LINK.toImageResource();
	}

	@Override
	public ImageResource getReportLinkIconLarge() {
		return BaseIcon.REPORT_GE_LINK.toImageResource(1);
	}

	@Override
	public String getReportName() {
		return GridEditorMessages.INSTANCE.reportTypeName();
	}

	@Override
	public Class<? extends ReportDto> getReportVariantClass() {
		return GridEditorReportVariantDto.class;
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
		return new GridEditorReportDtoDec();
	}

	@Override
	public ReportDto instantiateReportVariant() {
		return null;
	}
	
	@Override
	public boolean isAvailable() {
		return enterpriseService.isEnterprise();
	}
}
