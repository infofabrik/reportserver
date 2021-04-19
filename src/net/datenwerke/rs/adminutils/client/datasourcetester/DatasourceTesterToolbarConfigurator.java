package net.datenwerke.rs.adminutils.client.datasourcetester;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.ModalAsyncCallback;
import net.datenwerke.gxtdto.client.servercommunication.callback.TimeoutCallback;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.adminutils.client.datasourcetester.locale.DatasourceTesterMessages;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class DatasourceTesterToolbarConfigurator implements MainPanelViewToolbarConfiguratorHook {

	final DatasourceTesterMessages messages = GWT.create(DatasourceTesterMessages.class);

	private final DatasourceTesterDao datasourceTesterDao;
	private final ToolbarService toolbarUtils;

	@Inject
	public DatasourceTesterToolbarConfigurator(
			ToolbarService toolbarUtils,
			DatasourceTesterDao datasourceTestRPCService
			){

		this.toolbarUtils = toolbarUtils;
		this.datasourceTesterDao = datasourceTestRPCService;
	}

	@Override
	public void mainPanelViewToolbarConfiguratorHook_addLeft(MainPanelView view, ToolBar toolbar, AbstractNodeDto selectedNode) {
		if(! (view instanceof SimpleFormView))
			return;
		if(! (selectedNode instanceof DatabaseDatasourceDto))
			return;

		final DatabaseDatasourceDto databaseDto = (DatabaseDatasourceDto) selectedNode;
		
		DwTextButton datasourceTestBtn = toolbarUtils.createSmallButtonLeft(messages.testConnection(), BaseIcon.LINK); 
		datasourceTestBtn.addSelectHandler( event -> {
			ModalAsyncCallback<Boolean> callback = new ModalAsyncCallback<Boolean>(BaseMessages.INSTANCE.error(), messages.testFailed(), messages.success(), messages.testSuccess(),  messages.pleaseWait(), messages.testingTitle(),   messages.testingProgressMessage()){}; 
			Request request = datasourceTesterDao.testConnection(databaseDto, new TimeoutCallback<Boolean>(120000, callback));
			callback.setRequest(request);
		});
		
		toolbar.add(datasourceTestBtn);
	}

	@Override
	public void mainPanelViewToolbarConfiguratorHook_addRight(MainPanelView view, ToolBar toolbar, AbstractNodeDto selectedNode) {

	}

}
