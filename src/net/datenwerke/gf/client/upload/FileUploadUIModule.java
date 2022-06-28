package net.datenwerke.gf.client.upload;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.gf.client.upload.fileselectionsource.FileUploadSource;

public class FileUploadUIModule extends AbstractGinModule {

   public static final String UPLOAD_FILE_PREFIX = "rs_file_up_";
   public static final String UPLOAD_FILE_ID_PREFIX = UPLOAD_FILE_PREFIX + "_id_";
   public static final String UPLOAD_FILE_FILE_PREFIX = UPLOAD_FILE_PREFIX + "_file_";
   public static final String UPLOAD_FILE_HANDLER_PREFIX = UPLOAD_FILE_PREFIX + "_handler_";
   public static final String UPLOAD_FILE_META_PREFIX = UPLOAD_FILE_PREFIX + "_meta_";
   public static final String UPLOAD_FILE_XHR_CONTENT_PREFIX = UPLOAD_FILE_PREFIX + "_xhr_";
   public static final String UPLOAD_FILE_XHR_LENGTH_PREFIX = UPLOAD_FILE_PREFIX + "_xhrlength_";
   public static final String UPLOAD_FILE_XHR_NAME_PREFIX = UPLOAD_FILE_PREFIX + "_xhrname_";
   public static final String UPLOAD_FILE_CONTEXT_PREFIX = UPLOAD_FILE_PREFIX + "_context_";

   public static final String UPLOAD_SUCCESSFUL_PREFIX = "RS_UPLOAD_SUCCESS:";

   public static final String SERVLET_NAME = "fileupload";
   public static final String INTERIM_FILE_UPLOAD_HANDLER = "fileupload_fileselection_handler";

   public static final String UPLOADED_FILE_TYPE = "fileupload_uploadedfile";

   @Override
   protected void configure() {
      bind(FileUploadUiService.class).to(FileUploadUiServiceImpl.class);

      bind(FileUploadUiStartup.class).asEagerSingleton();

      requestStaticInjection(FileUploadSource.class);
   }

}
