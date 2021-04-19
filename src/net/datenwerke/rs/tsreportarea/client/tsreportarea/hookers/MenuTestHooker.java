package net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers;

import java.util.ArrayList;
import java.util.List;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.reportdoc.client.ReportDocumentationUiService;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.ItemSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

public class MenuTestHooker implements TsFavoriteMenuHook {

	private final ReportDocumentationUiService reportDocService;
	private final TeamSpaceUIService teamspaceService;

	@Inject
	public MenuTestHooker(ReportDocumentationUiService reportDocService, TeamSpaceUIService teamspaceService) {
		this.reportDocService = reportDocService;
		this.teamspaceService = teamspaceService;
	}

	@Override
	public boolean addContextMenuEntries(Menu menu, final List<AbstractTsDiskNodeDto> items, ItemSelector selector,
			final TsDiskMainComponent mainComponent) {
		if (null == items || items.isEmpty() || items.size() > 1)
			return false;
		if (!teamspaceService.isGuest(mainComponent.getCurrentSpace()))
			return false;
		if (!(items.get(0) instanceof TsDiskReportReferenceDto))
			return false;

		final TsDiskReportReferenceDto reference = (TsDiskReportReferenceDto) items.get(0);
		if (null != reference.getReport()) {
			MenuItem testItem = new DwMenuItem(BaseMessages.INSTANCE.test(), BaseIcon.REPORT);
			testItem.addSelectionHandler(event -> {
				InfoConfig infoConfig = new DefaultInfoConfig(
						ReportExporterMessages.INSTANCE.reportIsBeingExportedTitle(),
						ReportExporterMessages.INSTANCE.reportIsBeingExportedMsg("TEST"));
				infoConfig.setWidth(350);
				infoConfig.setDisplay(3500);
				Info.display(infoConfig);
				reportDocService.openVariantTestForopen(reference.getReport(), new ArrayList<DatasourceDefinitionDto>());
			});

			menu.add(testItem);
		}
		return true;
	}

}