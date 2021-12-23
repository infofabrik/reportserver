package net.datenwerke.rs.fileserver.client.fileserver;

import static net.datenwerke.rs.core.client.datasinkmanager.helper.forms.simpleform.DatasinkSimpleFormProvider.extractSingleTreeSelectionField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel.ToolBarEnhancer;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorTextArea;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCDatasinkDao;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.logs.LogFilesDao;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkDao;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkTreeManagerDao;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;
import net.datenwerke.rs.core.client.helper.ObjectHolder;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.eximport.client.eximport.locale.ExImportMessages;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;

public class FileServerUiServiceImpl implements FileServerUiService {

	private final FileServerDao fileDao;
	private final HookHandlerService hookHandlerService;
	private final LogFilesDao logFilesDao;
	private final Provider<DatasinkDao> datasinkDaoProvider;

	@Inject
	public FileServerUiServiceImpl(
		FileServerDao fileDao,
		HookHandlerService hookHandlerService,
		LogFilesDao logFilesDao,
		Provider<DatasinkDao> datasinkDaoProvider,
		Provider<DatasinkTreeManagerDao> datasinkTreeManagerProvider
		){
		this.fileDao = fileDao;
		this.hookHandlerService = hookHandlerService;
		this.logFilesDao = logFilesDao;
		this.datasinkDaoProvider = datasinkDaoProvider;
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

   @Override
   public void displayFileSendToDatasinkDialog(
         final Class<? extends DatasinkDefinitionDto> datasinkType,
         final String filename, 
         final Provider<UITree> treeProvider,
         final Provider<? extends HasDefaultDatasink> datasinkDaoProvider,
         final AbstractFileServerNodeDto toExport,
         final AsyncCallback<Map<String,Object>> onSelectHandler) {
      
      final Optional<DatasinkSendToFormConfiguratorHook> formConfiguratorOpt = 
            hookHandlerService.getHookers(DatasinkSendToFormConfiguratorHook.class)
            .stream()
            .filter(hooker -> hooker.consumes(datasinkType))
            .findAny();
      if (!formConfiguratorOpt.isPresent())
         throw new IllegalStateException("no form configurator found for datasink " + datasinkType);
      final DatasinkSendToFormConfiguratorHook formConfigurator = formConfiguratorOpt.get();
      
      final DwWindow window = new DwWindow();
      window.setHeaderIcon(formConfigurator.getIcon());
      window.setHeading(formConfigurator.getWindowTitle());
      window.setWidth(500);
      window.setHeight(formConfigurator.getWindowHeight());
      window.setCenterOnShow(true);

      DwContentPanel wrapper = new DwContentPanel();
      wrapper.setHeading(BaseMessages.INSTANCE.configuration());
      wrapper.setLightDarkStyle();
      wrapper.setBodyBorder(false);
      wrapper.setBorders(false);

      VerticalLayoutContainer formWrapper = new VerticalLayoutContainer();

      /* configure form */
      final SimpleForm form = SimpleForm.getInlineInstance();

      formWrapper.add(form);

      form.setFieldWidth(215);
      form.beginFloatRow();
      
      final String datasinkKey = form.addField(DatasinkSelectionField.class, formConfigurator.getWindowTitle(), new SFFCGenericTreeNode() {
         @Override
         public UITree getTreeForPopup() {
            return treeProvider.get();
         }
      }, new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      }, new SFFCDatasinkDao() {
         @Override
         public Provider<? extends HasDefaultDatasink> getDatasinkDaoProvider() {
            return datasinkDaoProvider;
         }

         @Override
         public BaseIcon getIcon() {
            return formConfigurator.getIcon();
         }
      });
      
      final ObjectHolder<String> folderKey = new ObjectHolder<>();
      if (formConfigurator.isFolderedDatasink()) {
         folderKey.set(form.addField(String.class, ScheduleAsFileMessages.INSTANCE.folder(),
            new SFFCAllowBlank() {
               @Override
               public boolean allowBlank() {
                  return false;
               }
         }));
      }
      
      form.endRow();
      form.setFieldWidth(1);

      final String nameKey = form.addField(String.class, ScheduleAsFileMessages.INSTANCE.nameLabel(),
         new SFFCAllowBlank() {
            @Override
            public boolean allowBlank() {
               return false;
            }
            });
      
      
      /* add additional fields */
      formConfigurator.installAdditionalFields(form);
      
      final ObjectHolder<String> compressedKey = new ObjectHolder<>();
      
      if (toExport instanceof FileServerFileDto) {
         compressedKey.set(form.addField(Boolean.class, "", new SFFCBoolean() {
            @Override
            public String getBoxLabel() {
               return FileServerMessages.INSTANCE.fileCompress();
            }
         }));
      }
      
      wrapper.setWidget(formWrapper);
      window.add(wrapper, new MarginData(10));

      /* set properties */
      if(filename.contains("."))
         form.setValue(nameKey, filename.substring(0, filename.lastIndexOf(".")));
      else
         form.setValue(nameKey, filename);

      /* load fields */
      form.loadFields();
      
      final SingleTreeSelectionField datasinkField = extractSingleTreeSelectionField(form.getField(datasinkKey));

      if (formConfigurator.isFolderedDatasink()) {
         datasinkField.addValueChangeHandler(event -> {
            if (null == event.getValue())
               return;

            this.datasinkDaoProvider.get().getDefaultFolder((DatasinkDefinitionDto) event.getValue(),
                  new RsAsyncCallback<String>() {
                     @Override
                     public void onSuccess(String result) {
                        form.setValue(folderKey.get(), result);
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                        super.onFailure(caught);
                     }
                  });
         });
      }
      
      window.addCancelButton();

      DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
      submitBtn.addSelectHandler(event -> {
         form.clearInvalid();

         if (!form.isValid())
            return;

         InfoConfig infoConfig = new DefaultInfoConfig(ExImportMessages.INSTANCE.quickExportProgressTitle(),
               ExImportMessages.INSTANCE.exportWait());
         infoConfig.setWidth(350);
         infoConfig.setDisplay(3500);
         Info.display(infoConfig);
         
         Map<String,Object> values = new HashMap<>();
         values.put(DatasinkUIModule.DATASINK_KEY, form.getValue(datasinkKey));
         values.put(DatasinkUIModule.DATASINK_FILENAME, ((String) form.getValue(nameKey)).trim());
         if (formConfigurator.isFolderedDatasink())
            values.put(DatasinkUIModule.DATASINK_FOLDER, ((String) form.getValue(folderKey.get())).trim());
         if(toExport instanceof FileServerFileDto)
            values.put(DatasinkUIModule.DATASINK_COMPRESSED_KEY, (boolean) form.getValue(compressedKey.get()));
         else
            values.put(DatasinkUIModule.DATASINK_COMPRESSED_KEY, (boolean) true);
         
         /* add additional values */
         Optional<Map<String, Object>> additionalValues = formConfigurator.getAdditionalFieldsValues(form);
         if (additionalValues.isPresent())
            values.putAll(additionalValues.get());
         
         onSelectHandler.onSuccess(values);
         window.hide();
      });
      window.addButton(submitBtn);

      window.show();
   }

}
