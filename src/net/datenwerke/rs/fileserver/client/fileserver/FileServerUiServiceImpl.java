package net.datenwerke.rs.fileserver.client.fileserver;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel.ToolBarEnhancer;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorTextArea;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.logs.LogFilesDao;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;

public class FileServerUiServiceImpl implements FileServerUiService {

	private final FileServerDao fileDao;
	private final HookHandlerService hookHandlerService;
	private final LogFilesDao logFilesDao;

	@Inject
	public FileServerUiServiceImpl(
		FileServerDao fileDao,
		HookHandlerService hookHandlerService,
		LogFilesDao logFilesDao
		){
		this.fileDao = fileDao;
		this.hookHandlerService = hookHandlerService;
		this.logFilesDao = logFilesDao;
	}
	
	@Override
	public void editFileDirectly(final FileServerFileDto file, final boolean editable, final boolean refreshable, final boolean emailable, final boolean scrollToEnd){
		fileDao.loadFileDataAsString(file, new RsAsyncCallback<String>() {
			@Override
			public void onSuccess(String result) {
				editFileDirectly(file, editable, refreshable, emailable, scrollToEnd, result);
			}
		});
	}

	@Override
	public void editFileDirectly(FileServerFileDto file, boolean editable, boolean refreshable, boolean emailable, boolean scrollToEnd, String data) {
		editFileDirectly(file.getName(), data, editable, refreshable, emailable, scrollToEnd, Optional.of(file));	
	}

	@Override
	public void editFileDirectly(String filename, String data, boolean editable, boolean refreshable, boolean emailable, boolean scrollToEnd, Optional<FileServerFileDto> file) {
		final DwWindow window = new DwWindow();
		window.setSize(800, 600);
		window.setHeading(filename);
		window.setOnEsc(false);
		window.setMaximizable(true);
		window.setCollapsible(true);
		window.setTitleCollapse(true);
		
		String mode = "none";
		if(filename.endsWith(".rs") || filename.endsWith(".groovy")){
			mode = "text/x-groovy";
		}
		if(filename.endsWith(".xml")){
			mode = "xml";
		}
		if(filename.endsWith(".cf")){
			mode = "xml";
		}
		
		final CodeMirrorPanel panel = new CodeMirrorPanel(mode, new ToolBarEnhancer() {
			
			@Override
			public void enhance(ToolBar toolbar, CodeMirrorPanel codeMirrorPanel) {
			}
			
			@Override
			public boolean allowPopup() {
				return false;
			}
		});
		
		TextArea ta = panel.getTextArea();
		if(ta instanceof CodeMirrorTextArea){
			CodeMirrorTextArea cmta = (CodeMirrorTextArea) ta;
			cmta.enableCodeMirrorPlugins(hookHandlerService);
			cmta.getCodeMirrorConfig().setLineNumbersVisible(true);
		}
		
		if(null != data)
			panel.getTextArea().setValue(data);
		
		panel.setNoOpacity();
		
		window.add(panel);
		
		if (emailable) {
			DwTextButton mail = new DwTextButton(UsermanagerMessages.INSTANCE.email());
			mail.setIcon(BaseIcon.MAIL_FORWARD);
			
			mail.addSelectHandler(event -> logFilesDao.emailFile(filename, new RsAsyncCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					InfoConfig infoConfig = new DefaultInfoConfig(ReportExporterMessages.INSTANCE.messageSend(), ReportExporterMessages.INSTANCE.messageSend());
					infoConfig.setWidth(350);
					Info.display(infoConfig);
				}
			}));
			window.addButton(mail);
		}
		
		if (refreshable) {
			DwTextButton refreshButton = new DwTextButton(BaseMessages.INSTANCE.refresh());
			refreshButton.setIcon(BaseIcon.REFRESH);
			refreshButton.addSelectHandler(event -> logFilesDao.readLastLines(filename, new RsAsyncCallback<List<String>>() {
				@Override
				public void onSuccess(List<String> result) {
					if (null != result) {
						String data = String.join("\n", result);
						if (null != data) 
							panel.getTextArea().setValue(data);
						
						scrollToEnd(panel);
						
						InfoConfig infoConfig = new DefaultInfoConfig(ReportmanagerMessages.INSTANCE.refreshed(), ReportmanagerMessages.INSTANCE.refreshed());
						infoConfig.setWidth(350);
						Info.display(infoConfig);
					}
				}
			}));
			window.addButton(refreshButton);
		}

		DwTextButton cancel = new DwTextButton(BaseMessages.INSTANCE.cancel());
		cancel.addSelectHandler( event -> window.hide() );
		window.addButton(cancel);
		
		if (editable && file.isPresent()) {
			DwTextButton submit = new DwTextButton(BaseMessages.INSTANCE.submit());
			submit.addSelectHandler( event -> {
				fileDao.updateFile(file.get(), panel.getTextArea().getValue(), new RsAsyncCallback<Void>());
				window.hide();
			});
			window.addButton(submit);
		}
		
		window.show();	
		
		if (scrollToEnd) {
			window.forceLayout();
			scrollToEnd(panel);
		}
	}
	
	private void scrollToEnd(CodeMirrorPanel panel) {
		panel.scrollTo(panel.getTextArea().getElement().getFirstChildElement().getScrollHeight());
	}

}
