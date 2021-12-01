package net.datenwerke.rs.incubator.client.reportmetadata.hookers;

import java.util.Arrays;
import java.util.List;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.incubator.client.reportmetadata.ui.ReportMetadataView;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 *
 */
public class MainPanelViewProviderHooker implements MainPanelViewProviderHook {

	private final Provider<ReportMetadataView> propertiesViewProvider;
	
	@Inject
	public MainPanelViewProviderHooker(
		Provider<ReportMetadataView> propertiesViewProvider
		){

		/* store objects */
		this.propertiesViewProvider = propertiesViewProvider;
	}
	
	public List<MainPanelView> mainPanelViewProviderHook_getView(AbstractNodeDto node) {
		if(node instanceof ReportDto)
			return getViewForReport();

		return null;
	}

	private List<MainPanelView> getViewForReport() {
		return Arrays.asList(new MainPanelView[]{propertiesViewProvider.get()});
	}

}
