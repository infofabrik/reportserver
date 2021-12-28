package net.datenwerke.gf.client.upload.fileselectionsource;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.util.DelayedTask;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ProgressMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.SubmitEvent;
import com.sencha.gxt.widget.core.client.event.SubmitEvent.SubmitHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.download.dto.DownloadProperties;
import net.datenwerke.gf.client.fileselection.FileSelectionWidget;
import net.datenwerke.gf.client.fileselection.FileSelectorSource;
import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.gf.client.upload.FileUploadUIModule;
import net.datenwerke.gf.client.upload.FileUploadUiService;
import net.datenwerke.gf.client.upload.FileUploadUiService.UploadHandler;
import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.dto.UploadProperties;
import net.datenwerke.gf.client.upload.dto.UploadResponse;
import net.datenwerke.gf.client.upload.filter.FileUploadFilter;
import net.datenwerke.gf.client.upload.html5.Html5FileUploadListener;
import net.datenwerke.gf.client.upload.html5.Html5FileUploadListener.UploadCallback;
import net.datenwerke.gf.client.upload.locale.UploadMessages;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwFlowContainer;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class FileUploadSource implements FileSelectorSource {

   @Inject
   private static FileUploadUiService fileUploadService;

   private boolean displayLegacyUpload = true;
   private String legacyUploadBtnText = UploadMessages.INSTANCE.uploadBtn();
   private ImageResource legacyUploadBtnIcon = BaseIcon.UPLOAD.toImageResource();

   private String uploadDialogHeadingText = UploadMessages.INSTANCE.uploadHeading();
   private String uploadDialogBtnText = UploadMessages.INSTANCE.uploadBtn();
   private String uploadDialogLabelText = UploadMessages.INSTANCE.uploadLabel();

   private String invalidFileTitle = BaseMessages.INSTANCE.error();
   private String invalidFileMessage = UploadMessages.INSTANCE.invalidFileMessage();

   private String uploadedFileDescription = UploadMessages.INSTANCE.uploadedFileType();

   private ImageResource uploadedFileIcon = BaseIcon.UPLOAD.toImageResource();

   private FileUploadFilter uploadFilter = FileUploadFilter.DUMMY_UPLOAD_FILTER;

   private FileSelectionWidget fileSelectionWidget;

   private List<Request> currentRequests = new ArrayList<Request>();

   private boolean enableNameEditing = true;
   private boolean enableDownload = true;

   public FileUploadSource() {
      super();
   }

   @Override
   public void init(FileSelectionWidget fileSelectionWidget) {
      this.fileSelectionWidget = fileSelectionWidget;
   }

   @Override
   public void configureToolbar(FileSelectionWidget fileSelectionWidget, ToolBar toolbar) {
      if (!displayLegacyUpload)
         return;

      DwTextButton btn = new DwTextButton(getLegacyUploadBtnText(), getLegacyUploadBtnIcon());
      toolbar.add(btn);
      btn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            displayLegacyDialog();
         }
      });
   }

   @Override
   public void configureGrid(final FileSelectionWidget fileSelectionWidget, Grid<SelectedFileWrapper> grid) {
      new Html5FileUploadListener(new UploadCallback() {

         private int filesToUpload = 0;
         private int filesUploaded = 0;
         private int supposedNumberOfFiles = 0;

         private int processCnt = 0;
         private String error;
         private List<UploadResponse> responseList = new ArrayList<UploadResponse>();
         private ProgressMessageBox waitBox;
         private DelayedTask delayedTask;
         private boolean cancel;

         @Override
         public void fileUploaded(String name, long size, String base64) {
            if (cancel)
               return;

            processCnt++;
            String errorMsg = uploadFilter.doProcess(name, size, base64);
            if (null != errorMsg && null == error)
               error = errorMsg;

            if (null != error) {
               if (processCnt == supposedNumberOfFiles)
                  displayInvalidFileMessage(error);
               fileSelectionWidget.unmask();
               currentRequests.clear();
               delayedTask.cancel();
               waitBox.hide();
               return;
            }

            filesToUpload++;
            fileSelectionWidget.mask(UploadMessages.INSTANCE.uploadInProgress(filesToUpload, filesUploaded));

            Request request = fileUploadService.uploadInterimFile(new FileToUpload(name, size, base64),
                  new RsAsyncCallback<UploadResponse>() {
                     @Override
                     public void onSuccess(UploadResponse result) {
                        responseList.add(result);

                        filesUploaded++;
                        fileSelectionWidget
                              .mask(UploadMessages.INSTANCE.uploadInProgress(filesToUpload, filesUploaded));

                        /* done? */
                        if (filesUploaded == filesToUpload) {
                           fileSelectionWidget.unmask();
                           waitBox.hide();
                           processResponses(responseList);
                        }
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                     }
                  });

            currentRequests.add(request);
         }

         @Override
         public void allFilesUploaded(List<FileToUpload> list) {
         }

         @Override
         public void fireOnDropStart(int nrOfFiles) {
            if (fileSelectionWidget.getRemainingFileSpace() < nrOfFiles) {
               fileSelectionWidget.displayMaxFilesReachedMessage();
               cancel = true;
               return;
            }

            /* reset properties and mask */
            filesToUpload = 0;
            filesUploaded = 0;
            supposedNumberOfFiles = nrOfFiles;
            processCnt = 0;
            error = null;
            cancel = false;
            currentRequests.clear();
            responseList.clear();

            fileSelectionWidget.mask(UploadMessages.INSTANCE.uploadInProgress(filesToUpload, filesUploaded));

            waitBox = new ProgressMessageBox(BaseMessages.INSTANCE.waitMsg(), BaseMessages.INSTANCE.waitMsg());
            waitBox.setPredefinedButtons(PredefinedButton.CANCEL);
            waitBox.setProgressText(BaseMessages.INSTANCE.progressMsg());
            waitBox.setClosable(false);

            delayedTask = new DelayedTask() {
               @Override
               public void onExecute() {
                  for (Request req : currentRequests) {
                     if (req.isPending()) {
                        waitBox.show();
                        return;
                     }
                  }
               }
            };

            TextButton cancelBtn = waitBox.getButton(PredefinedButton.CANCEL);
            cancelBtn.setVisible(true);
            cancelBtn.addSelectHandler(new SelectHandler() {
               @Override
               public void onSelect(SelectEvent event) {
                  for (Request req : currentRequests)
                     req.cancel();
                  waitBox.hide();
                  fileSelectionWidget.unmask();
               }
            });

            delayedTask.delay(5000);
         }
      }, grid);
   }

   @Override
   public boolean consumes(SelectedFileWrapper value) {
      return null != value && FileUploadUIModule.UPLOADED_FILE_TYPE.equals(value.getType());
   }

   @Override
   public ImageResource getIconFor(SelectedFileWrapper value) {
      return uploadedFileIcon;
   }

   public ImageResource getUploadedFileIcon() {
      return uploadedFileIcon;
   }

   public void setUploadedFileIcon(ImageResource uploadedFileIcon) {
      this.uploadedFileIcon = uploadedFileIcon;
   }

   public String getUploadedFileDescription() {
      return uploadedFileDescription;
   }

   public void setUploadedFileDescription(String uploadedFileDescription) {
      this.uploadedFileDescription = uploadedFileDescription;
   }

   @Override
   public boolean isEditNameEnabled(SelectedFileWrapper selectedItem) {
      return enableNameEditing;
   }

   public void setEnableNameEditing(boolean enableNameEditing) {
      this.enableNameEditing = enableNameEditing;
   }

   public boolean isEnableNameEditing() {
      return enableNameEditing;
   }

   @Override
   public boolean isDownloadEnabled(SelectedFileWrapper item) {
      return enableDownload;
   }

   public boolean isEnableDownload() {
      return enableDownload;
   }

   public void setEnableDownload(boolean enableDownload) {
      this.enableDownload = enableDownload;
   }

   protected void processResponses(List<UploadResponse> responseList) {
      List<UploadResponse> errorList = new ArrayList<UploadResponse>();
      List<UploadResponse> successList = new ArrayList<UploadResponse>();
      for (UploadResponse response : responseList) {
         if (response.isSuccess())
            successList.add(response);
         else
            errorList.add(response);
      }

      if (!errorList.isEmpty()) {
         String title = BaseMessages.INSTANCE.error();
         String msg = UploadMessages.INSTANCE.uploadErrorMsg(errorList.size());
         StringBuilder detail = new StringBuilder();
         for (UploadResponse r : errorList)
            detail.append(r.getErrorMsg()).append("\n");

         new DetailErrorDialog(title, msg, detail.toString()).show();
      }

      for (UploadResponse r : successList) {
         SelectedFileWrapper sfw = new SelectedFileWrapper();
         sfw.setId(r.getId());
         sfw.setName(r.getName());
         sfw.setType(FileUploadUIModule.UPLOADED_FILE_TYPE);

         fileSelectionWidget.add(sfw);
      }

   }

   public boolean isDisplayLegacyUpload() {
      return displayLegacyUpload;
   }

   public void setDisplayLegacyUpload(boolean displayLegacyUpload) {
      this.displayLegacyUpload = displayLegacyUpload;
   }

   public String getLegacyUploadBtnText() {
      return legacyUploadBtnText;
   }

   public void setLegacyUploadBtnText(String legacyUploadBtnText) {
      this.legacyUploadBtnText = legacyUploadBtnText;
   }

   public String getUploadDialogHeadingText() {
      return uploadDialogHeadingText;
   }

   public void setUploadDialogHeadingText(String uploadHeadingText) {
      this.uploadDialogHeadingText = uploadHeadingText;
   }

   public String getUploadDialogBtnText() {
      return uploadDialogBtnText;
   }

   public void setUploadDialogBtnText(String uploadDialogBtnText) {
      this.uploadDialogBtnText = uploadDialogBtnText;
   }

   public String getUploadDialogLabelText() {
      return uploadDialogLabelText;
   }

   public void setUploadDialogLabelText(String uploadDialogLabelText) {
      this.uploadDialogLabelText = uploadDialogLabelText;
   }

   public FileUploadFilter getUploadFilter() {
      return uploadFilter;
   }

   public void setUploadFilter(FileUploadFilter uploadFilter) {
      this.uploadFilter = uploadFilter;
   }

   public ImageResource getLegacyUploadBtnIcon() {
      return legacyUploadBtnIcon;
   }

   public void setLegacyUploadBtnIcon(ImageResource legacyUploadBtnIcon) {
      this.legacyUploadBtnIcon = legacyUploadBtnIcon;
   }

   protected void displayLegacyDialog() {
      if (fileSelectionWidget.getRemainingFileSpace() < 1) {
         fileSelectionWidget.displayMaxFilesReachedMessage();
         return;
      }

      final DwWindow uploadDialog = new DwWindow();
      uploadDialog.setHeading(getUploadDialogHeadingText());
      uploadDialog.setWidth(350);
      uploadDialog.setHeight(120);
      uploadDialog.getButtonBar().clear();

      final FormPanel form = new FormPanel();
      DwFlowContainer fieldContainer = new DwFlowContainer();
      form.setWidget(fieldContainer);

      fileUploadService.prepareForUpload(form);

      fileUploadService.handleUploadReturn(form, new UploadHandler() {
         @Override
         public void onSuccess(JSONValue result) {
            try {
               String id = ((JSONString) ((JSONObject) result).get("id")).stringValue();
               String name = ((JSONString) ((JSONObject) result).get("name")).stringValue();

               SelectedFileWrapper sfw = new SelectedFileWrapper();
               sfw.setId(id);
               sfw.setName(name);
               sfw.setType(FileUploadUIModule.UPLOADED_FILE_TYPE);

               fileSelectionWidget.add(sfw);

            } catch (Exception e) {
               new DetailErrorDialog(e).show();
            }
            uploadDialog.hide();

         }

         @Override
         public void onError() {
            uploadDialog.hide();
         }
      });

      /* mask panel on upload */
      form.addSubmitHandler(new SubmitHandler() {
         @Override
         public void onSubmit(SubmitEvent event) {
            uploadDialog.mask(BaseMessages.INSTANCE.storingMsg());
         }
      });

      /* file upload */
      UploadProperties uploadProperties = new UploadProperties("rs_x_fileupload",
            FileUploadUIModule.INTERIM_FILE_UPLOAD_HANDLER);

      final FileUploadField fileUploadField = fileUploadService.addBaseUploadField(uploadProperties, fieldContainer);
      fileUploadField.setAllowBlank(false);

      fieldContainer.add(new FieldLabel(fileUploadField, getUploadDialogLabelText()));

      /* add form to dialog */
      uploadDialog.add(form, new MarginData(10));

      /* Cancel */
      DwTextButton cnlButton = new DwTextButton(BaseMessages.INSTANCE.cancel());
      cnlButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            uploadDialog.hide();
         }
      });
      uploadDialog.addButton(cnlButton);

      /* ok Button */
      DwTextButton okButton = new DwTextButton(getUploadDialogBtnText());
      okButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            long size = fileUploadService.getSize(fileUploadField);

            String value = fileUploadField.getValue();
            String error = uploadFilter.doProcess(value, size, null);
            if (null != error)
               displayInvalidFileMessage(error);
            else
               form.submit();
         }
      });
      uploadDialog.addButton(okButton);

      uploadDialog.show();
   }

   protected void displayInvalidFileMessage(String error) {
      new DwAlertMessageBox(getInvalidFileTitle(), getInvalidFileMessage() + ": " + error).show();
   }

   @Override
   public DownloadProperties getDownloadPropertiesFor(SelectedFileWrapper selectedItem) {
      return null;
   }

   @Override
   public String getTypeDescription(SelectedFileWrapper value) {
      return getUploadedFileDescription();
   }

   public void setInvalidFileMessage(String invalidFileMessage) {
      this.invalidFileMessage = invalidFileMessage;
   }

   public String getInvalidFileMessage() {
      return invalidFileMessage;
   }

   public void setInvalidFileTitle(String invalidFileTitle) {
      this.invalidFileTitle = invalidFileTitle;
   }

   public String getInvalidFileTitle() {
      return invalidFileTitle;
   }

   @Override
   public boolean isValid() {
      for (Request req : currentRequests) {
         if (req.isPending())
            return true;
      }

      return true;
   }

}
