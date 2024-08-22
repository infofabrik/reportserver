package net.datenwerke.rs.fileserver.client.fileserver.ui.forms;

import java.util.List;

import javax.inject.Provider;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.MessageBox;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.simpleform.FileUpload;
import net.datenwerke.gf.client.upload.simpleform.SFFCDnDFileUpload;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerDao;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.pa.FileServerFolderDtoPA;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.pkg.client.pkg.PkgDao;
import net.datenwerke.rs.pkg.client.pkg.locale.PkgMessages;

/**
 * 
 *
 */
public class FolderForm extends SimpleFormView {

   private final FileServerDao fileServerDao;
   private final Provider<PkgDao> pkgDaoProvider;

   @Inject
   public FolderForm(
         FileServerDao fileServerDao,
         Provider<PkgDao> pkgDaoProvider
         ) {
      this.fileServerDao = fileServerDao;
      this.pkgDaoProvider = pkgDaoProvider;
   }

   @Override
   public void configureSimpleForm(SimpleForm form) {
      form.setHeading(FileServerMessages.INSTANCE.editFolder()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.addField(String.class, FileServerFolderDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.propertyName());

      form.addField(String.class, FileServerFolderDtoPA.INSTANCE.description(),
            BaseMessages.INSTANCE.propertyDescription(), new SFFCTextAreaImpl());

      form.addField(Boolean.class, FileServerFolderDtoPA.INSTANCE.publiclyAccessible(),
            FileServerMessages.INSTANCE.publiclyAccessibleLabel());

      form.addField(FileUpload.class, FileServerMessages.INSTANCE.fileUploadLabel(), new SFFCDnDFileUpload() {
         @Override
         public void filesUploaded(final List<FileToUpload> files) {
            if (null != getSelectedNode() && getSelectedNode() instanceof FileServerFolderDto) {
               if (null != files && files.size() == 1) {
                  String name = files.get(0).getName();
                  if (null != name && name.endsWith(".zip")) {
                     MessageBox mb = new DwMessageBox(FileServerMessages.INSTANCE.zipUploadedTitle(),
                           FileServerMessages.INSTANCE.zipUploadedMsg());
                     mb.setPredefinedButtons(PredefinedButton.NO, PredefinedButton.YES);
                     mb.addDialogHideHandler(event -> {
                        switch (event.getHideButton()) {
                        case YES:
                           uploadAndExtract(files.get(0));
                           break;
                        default:
                           upload(files);
                        }
                     });
                     mb.show();
                  } else if (null != name && name.endsWith(".rsp")) {
                     MessageBox mb = new DwMessageBox(PkgMessages.INSTANCE.rspUploadedTitle(),
                           PkgMessages.INSTANCE.rspUploadedMsg());
                     mb.setPredefinedButtons(PredefinedButton.YES, PredefinedButton.NO);
                     mb.addDialogHideHandler(event -> {
                        switch (event.getHideButton()) {
                        case YES:
                           uploadAndExecute(files.get(0));
                           break;
                        default:
                           upload(files);
                        }
                     });
                     mb.show();
                  } else 
                     upload(files);
               } else
                  upload(files);
            }
         }

         private void upload(List<FileToUpload> files) {
            mask(BaseMessages.INSTANCE.storingMsg());
            fileServerDao.uploadFiles((FileServerFolderDto) getSelectedNode(), files,
                  new RsAsyncCallback<List<FileServerFileDto>>() {
                     @Override
                     public void onSuccess(List<FileServerFileDto> result) {
                        super.onSuccess(result);
                        unmask();
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                        super.onFailure(caught);
                        unmask();
                     }
                  });
         }

         private void uploadAndExtract(FileToUpload fileToUpload) {
            mask(BaseMessages.INSTANCE.storingMsg());
            fileServerDao.uploadAndExtract((FileServerFolderDto) getSelectedNode(), fileToUpload,
                  new RsAsyncCallback<List<AbstractFileServerNodeDto>>() {
                     @Override
                     public void onSuccess(List<AbstractFileServerNodeDto> result) {
                        super.onSuccess(result);
                        unmask();
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                        super.onFailure(caught);
                        unmask();
                     }
                
            });
         }
         
         private void uploadAndExecute(FileToUpload fileToUpload) {
            mask(PkgMessages.INSTANCE.executingRspMsg());
            pkgDaoProvider.get().uploadAndExecute((FileServerFolderDto) getSelectedNode(), fileToUpload,
                  new RsAsyncCallback<String>() {
                     @Override
                     public void onSuccess(String result) {
                        super.onSuccess(result);
                        unmask();
                        MessageBox mb = new DwMessageBox(PkgMessages.INSTANCE.rspExecution(),
                              PkgMessages.INSTANCE.rspExecutionResult() + ": " + result);
                        mb.show();
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                        super.onFailure(caught);
                        unmask();
                     }
                
            });
         }
      });
   }

}