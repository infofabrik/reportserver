package net.datenwerke.gf.client.upload.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface UploadMessages extends Messages {

   public static final UploadMessages INSTANCE = GWT.create(UploadMessages.class);

   String uploadSingleDropzoneDescription();

   String fileName(String name);

   String uploadHeading();

   String uploadBtn();

   String uploadLabel();

   String uploadInProgress(int filesToUpload, int filesUploaded);

   String uploadErrorMsg(int size);

   String uploadedFileType();

   String invalidFileMessage();

}