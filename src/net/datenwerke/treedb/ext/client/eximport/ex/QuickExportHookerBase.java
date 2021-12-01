package net.datenwerke.treedb.ext.client.eximport.ex;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gf.client.uiutils.ClientDownloadHelper;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringNoHtmlDecode;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCTextArea;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.eximport.client.eximport.locale.ExImportMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public abstract class QuickExportHookerBase implements MainPanelViewToolbarConfiguratorHook{

	private static final String DOWNLOAD_URL = "quickExportDownload";

	protected final ToolbarService toolbarUtils;
	protected final UtilsUIService utilsUiService;
	
	public QuickExportHookerBase(
		ToolbarService toolbarUtils,
		UtilsUIService utilsUiService
		) {

		/* store objects */
		this.toolbarUtils = toolbarUtils;
		this.utilsUiService = utilsUiService;
	}

	@Override
	public void mainPanelViewToolbarConfiguratorHook_addLeft(MainPanelView view, ToolBar toolbar, AbstractNodeDto selectedNode) {
	}

	@Override
	public void mainPanelViewToolbarConfiguratorHook_addRight(MainPanelView view, ToolBar toolbar, final AbstractNodeDto selectedNode) {
		if(! viewApplies(view, selectedNode))
			return;
		
		/* add buttons */
		DwTextButton quickExportBtn = toolbarUtils.createSmallButtonLeft(ExImportMessages.INSTANCE.quickExportText(), BaseIcon.REPORT_DISK);
		quickExportBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				quickExportClicked(selectedNode);
			}
		});
		toolbar.add(quickExportBtn);
	}

	protected void startProgress() {
		try{
			InfoConfig infoConfig = new DefaultInfoConfig(ExImportMessages.INSTANCE.quickExportProgressTitle(), ExImportMessages.INSTANCE.exportWait());
			infoConfig.setWidth(350);
			infoConfig.setDisplay(3500);
			Info.display(infoConfig);
		}catch(Exception e){}
		
	}

	abstract protected void quickExportClicked(final AbstractNodeDto selectedNode);

	protected AsyncCallback<Void> getExportCallback() {
		return new RsAsyncCallback<Void>(){
			@Override
			public void onSuccess(Void result) {
				exportSucceded();
			}
			@Override
			public void onFailure(Throwable caught) {
			}
		};
	}

	protected void exportSucceded() {
		final DwWindow window = new DwWindow();
		window.setModal(true);
		window.setClosable(false);
		window.setTitle(ExImportMessages.INSTANCE.exportSuccededTitle());
		window.setHeaderIcon(BaseIcon.EXPORT);
		
		DwContentPanel wrapper = DwContentPanel.newInlineInstance();
		wrapper.add(new Label(ExImportMessages.INSTANCE.exportSuccededMessage()), new MarginData(5));
		window.add(wrapper);
		
		DwTextButton displayBtn = new DwTextButton(ExImportMessages.INSTANCE.quickExportDisplayDirectLabel());
		displayBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				window.hide();
				startProgress();
				loadAndDisplayResult(getLoadAndDisplayResultCallback());
			}
		});
		window.addButton(displayBtn);
		
		DwTextButton downloadBtn = new DwTextButton(ExImportMessages.INSTANCE.quickExportDownloadLabel());
		downloadBtn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				window.hide();	
				downloadResult();
			}
		});
		window.addButton(downloadBtn);
		
		window.show();
	}

	protected void downloadResult() {
		String url = GWT.getModuleBaseURL() + DOWNLOAD_URL;
		ClientDownloadHelper.triggerDownload(url);
	}

	abstract protected void loadAndDisplayResult(AsyncCallback<String> loadAndDisplayResultCallback);

	protected AsyncCallback<String> getLoadAndDisplayResultCallback() {
		return new RsAsyncCallback<String>(){
			@Override
			public void onSuccess(String result) {
				displayQuickExportResult(result);
			}
		};
	}

	abstract protected boolean viewApplies(MainPanelView view, AbstractNodeDto selectedNode);

	protected void displayQuickExportResult(String result) {
		final DwWindow window = new DwWindow();
		window.setSize(640, 480);
		
		SimpleForm form = SimpleForm.getInlineLabelessInstance();
		window.add(form);
		
		String key = form.addField(String.class, new SFFCTextArea() {
			
			@Override
			public int getWidth() {
				return 580;
			}
			
			@Override
			public int getHeight() {
				return 400;
			}
		}, new SFFCStringNoHtmlDecode(){});
		form.setValue(key, result);
		
		form.loadFields();
		
		DwTextButton closeBtn = new DwTextButton(BaseMessages.INSTANCE.close());
		closeBtn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				window.hide();
			}
		});
		window.addButton(closeBtn);
		
		window.show();
	}

}