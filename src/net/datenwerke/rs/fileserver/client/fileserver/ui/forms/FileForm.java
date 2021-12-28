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
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCustomComponent;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCSpace;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticTextField;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticTextFieldWithCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.CustomComponent;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.Separator;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.StaticLabel;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec;
import net.datenwerke.rs.fileserver.client.fileserver.dto.pa.FileServerFileDtoPA;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;

/**
 * 
 *
 */
public class FileForm extends SimpleFormView {

   @Inject
   private FileUploadUiService fileUploadService;

   @Override
   public void configureSimpleForm(final SimpleForm form) {
      form.setHeading(FileServerMessages.INSTANCE.editFile()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.addField(String.class, FileServerFileDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.propertyName()); // $NON-NLS-1$

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
      }
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