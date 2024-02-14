package net.datenwerke.rs.fileserver.client.fileserver.ui.forms;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gf.client.uiutils.ClientDownloadHelper;
import net.datenwerke.gf.client.upload.FileUploadUiService;
import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.dto.UploadProperties;
import net.datenwerke.gf.client.upload.simpleform.FileUpload;
import net.datenwerke.gf.client.upload.simpleform.SFFCFileUpload;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCustomComponent;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCSpace;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticTextField;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticTextFieldWithCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorRegex;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.CustomComponent;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.Separator;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.StaticLabel;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.dot.client.dot.DotUiModule;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerDao;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec;
import net.datenwerke.rs.fileserver.client.fileserver.dto.pa.FileServerFileDtoPA;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.markdown.client.markdown.MarkdownUiModule;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.utils.validator.shared.SharedRegex;
/**
 * 
 *
 */
public class FileForm extends SimpleFormView {

   @Inject
   private FileUploadUiService fileUploadService;
   @Inject
   private UtilsUIService utilsUIService;
   @Inject
   private FileServerDao fileServerDao;
   @Inject
   private ToolbarService toolbarUtils;

   @Override
   public void configureSimpleForm(final SimpleForm form) {
      form.setHeading(FileServerMessages.INSTANCE.editFile()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.beginFloatRow();
      form.setFieldWidth(500);
      
      form.addField(String.class, FileServerFileDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.propertyName()); // $NON-NLS-1$
      
      /* key */
      form.addField(String.class, FileServerFileDtoPA.INSTANCE.key(), BaseMessages.INSTANCE.key(),
            new SFFCStringValidatorRegex(SharedRegex.KEY_REGEX, BaseMessages.INSTANCE.invalidKey()),
            new SFFCAllowBlank() {
               @Override
               public boolean allowBlank() {
                  return false;
               }
            }); // $NON-NLS-1$
      
      form.endRow();
      
      form.setFieldWidth(1);

      form.addField(String.class, FileServerFileDtoPA.INSTANCE.description(),
            BaseMessages.INSTANCE.propertyDescription(), new SFFCTextAreaImpl());

      form.addField(String.class, FileServerFileDtoPA.INSTANCE.contentType(),
            FileServerMessages.INSTANCE.contentTypeLabel());

      /* upload */
      form.addField(FileUpload.class, FileServerUiModule.UPLOAD_SERVLET_KEY_UPLOAD,
            FileServerMessages.INSTANCE.fileUploadLabel(), new SFFCFileUpload() {
               @Override
               public UploadProperties getProperties() {
                  UploadProperties uploadProperties = new UploadProperties("file",
                        FileServerUiModule.FILE_UPLOAD_HANDLER_ID);
                  uploadProperties.addMetadata(FileServerUiModule.UPLOAD_FILE_ID_FIELD,
                        String.valueOf(getSelectedNode().getId()));

                  return uploadProperties;
               }

               @Override
               public boolean enableHTML5() {
                  return true;
               }

               @Override
               public void filesUploaded(List<FileToUpload> list) {
               }

            });

      form.addField(Separator.class, new SFFCSpace());

      form.setLabelAlign(LabelAlign.LEFT);
      form.setLabelWidth(140);
      form.addField(StaticLabel.class, FileServerMessages.INSTANCE.sizeLabel(), new SFFCStaticTextField() {
         @Override
         public String getLabel() {
            return FileServerMessages.INSTANCE.sizeLabel();
         }

         @Override
         public String getValue() {
            if (1024 > ((FileServerFileDtoDec) getSelectedNode()).getSize())
               return FileServerMessages.INSTANCE.sizeValue(((FileServerFileDtoDec) getSelectedNode()).getSize());
            if (1024 * 1024 > ((FileServerFileDtoDec) getSelectedNode()).getSize())
               return FileServerMessages.INSTANCE
                     .sizeValueKb(((FileServerFileDtoDec) getSelectedNode()).getSize() / (double) 1024);
            return FileServerMessages.INSTANCE
                  .sizeValueMb(((FileServerFileDtoDec) getSelectedNode()).getSize() / (double) 1024 / (double) 1024);
         }
      });

      form.addField(StaticLabel.class, FileServerMessages.INSTANCE.previewUrlLabel(),
            new SFFCStaticTextFieldWithCallback() {
               @Override
               public String getLabel() {
                  return FileServerMessages.INSTANCE.previewUrlLabel();
               }

               @Override
               public void onClick() {
                  int nonce = Random.nextInt();
                  String url = GWT.getModuleBaseURL() + FileServerUiModule.FILE_ACCESS_SERVLET + "?nonce=" + nonce //$NON-NLS-1$
                        + "&id=" + getSelectedNode().getId() + "&download=true";
                  ClientDownloadHelper.triggerDownload(url);
               }

               @Override
               public String getValue() {
                  return GWT.getModuleBaseURL() + FileServerUiModule.FILE_ACCESS_SERVLET + "?id=" //$NON-NLS-1$
                        + getSelectedNode().getId();
               }
            });

      String type = ((FileServerFileDtoDec) getSelectedNode()).getContentType();
      if (null != type && ("image/png".equals(type) || "image/jpg".equals(type) || "image/jpeg".equals(type))) {
         form.addField(CustomComponent.class, new SFFCCustomComponent() {
            @Override
            public Widget getComponent() {
               Image img = new Image();
               img.setUrl(((FileServerFileDtoDec) getSelectedNode()).getThumbnailUrl());
               img.setWidth(100 + "px");

               VerticalLayoutContainer cont = new VerticalLayoutContainer();
               cont.add(img, new VerticalLayoutData(100, -1));

               FieldLabel label = new FieldLabel(cont, FileServerMessages.INSTANCE.previewLabel());
               return label;
            }
         });
      } else if (null != type && DotUiModule.MIME_TYPE.equals(type)) {
         DwTextButton previewButton = toolbarUtils
               .createSmallButtonLeft(FileServerMessages.INSTANCE.previewLabel() + " SVG", BaseIcon.SITEMAP);
         FileForm mainPanel = this;
         previewButton.addSelectHandler(event -> {
            mainPanel.mask(BaseMessages.INSTANCE.loadingMsg());
            fileServerDao.loadDotAsSVG((FileServerFileDto) getSelectedNode(), getCallbackPopupWindow(mainPanel));
         });
         addPreviewButtonToForm(form, previewButton);
      } else if (null != type && MarkdownUiModule.MIME_TYPE.equals(type)) {
         DwTextButton previewButton = toolbarUtils.createSmallButtonLeft(FileServerMessages.INSTANCE.previewLabel(), BaseIcon.SITEMAP);
         FileForm mainPanel = this;
         previewButton.addSelectHandler(event -> {
            mainPanel.mask(BaseMessages.INSTANCE.loadingMsg());
            fileServerDao.loadMarkdownAsHtml((FileServerFileDto) getSelectedNode(), getCallbackPopupWindow(mainPanel));
         });
         addPreviewButtonToForm(form, previewButton);
      }
   }
   private RsAsyncCallback<String> getCallbackPopupWindow(FileForm mainPanel) {
      return new RsAsyncCallback<String>() {
         @Override
         public void onSuccess(String result) { 
            mainPanel.unmask();
            utilsUIService.showHtmlPopupWindows(((FileServerFileDtoDec) getSelectedNode()).getName(),
                  result, false, BaseIcon.SITEMAP, false, false, true, true);
         }
         @Override
         public void onFailure(Throwable caught) {
            mainPanel.unmask();
            super.onFailure(caught);
         }
      };
   }
   
   private void addPreviewButtonToForm(SimpleForm form, DwTextButton previewButton) {
      VerticalLayoutContainer cont = new VerticalLayoutContainer();
      cont.add(previewButton, new VerticalLayoutData(100, -1));
      form.addField(CustomComponent.class, new SFFCCustomComponent() {      
         @Override
         public Widget getComponent() {
            return cont;
         }
      });
   }

   @Override
   protected boolean isUploadForm() {
      return true;
   }

   @Override
   protected String getFormAction() {
      return fileUploadService.getFormAction();
   }

}