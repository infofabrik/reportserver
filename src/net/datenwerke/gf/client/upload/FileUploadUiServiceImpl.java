package net.datenwerke.gf.client.upload;

import java.util.List;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent.SubmitCompleteHandler;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.Encoding;
import com.sencha.gxt.widget.core.client.form.FormPanel.Method;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.dto.UploadProperties;
import net.datenwerke.gf.client.upload.dto.UploadResponse;
import net.datenwerke.gf.client.upload.html5.Html5FileUploadListener;
import net.datenwerke.gf.client.upload.html5.Html5FileUploadListener.UploadCallback;
import net.datenwerke.gf.client.upload.locale.UploadMessages;
import net.datenwerke.gxtdto.client.baseex.widget.form.DwFileUploadField;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;

public class FileUploadUiServiceImpl implements FileUploadUiService {

   private static final UploadHandler UPLOAD_DUMMY_HANDLER = new UploadHandler() {
      @Override
      public void onSuccess(JSONValue result) {
      }

      @Override
      public void onError() {
      }
   };

   private final FileUploadServiceDao fileUploadDao;

   @CssClassConstant
   public static final String CSS_FILENAME_STYLE = "rs-upl-filename";
   @CssClassConstant
   public static final String CSS_BACKUP_STYLE = "rs-upl-backup";
   @CssClassConstant
   private static final String CSS_DRAG_STYLE = "rs-upl-drag";

   public interface CombinedFiedlTemplate extends XTemplates {
      @XTemplate(source = "combinedUpload.html")
      SafeHtml getTemplate(UploadMessages messages);

   }

   @Inject
   public FileUploadUiServiceImpl(FileUploadServiceDao dao) {
      super();
      this.fileUploadDao = dao;
   }

   @Override
   public FileUploadField addBaseUploadField(UploadProperties uploadProperties, Container container) {
      /* generate upload field */
      FileUploadField fileUploadField = new DwFileUploadField();
      fileUploadField.setWidth(210);
      fileUploadField.setName(FileUploadUIModule.UPLOAD_FILE_FILE_PREFIX + uploadProperties.getId());

      /* generate hidden fields */
      Hidden idField = new Hidden();
      idField.setName(FileUploadUIModule.UPLOAD_FILE_ID_PREFIX + uploadProperties.getId());
      idField.setValue("1");
      container.add(idField);

      Hidden handlerField = new Hidden();
      handlerField.setName(FileUploadUIModule.UPLOAD_FILE_HANDLER_PREFIX + uploadProperties.getId());
      handlerField.setValue(uploadProperties.getHandler());
      container.add(handlerField);

      for (Entry<String, String> e : uploadProperties.getMetadata().entrySet()) {
         String key = e.getKey();
         String value = e.getValue();

         Hidden metaField = new Hidden();
         metaField.setName(FileUploadUIModule.UPLOAD_FILE_META_PREFIX + uploadProperties.getId() + "_" + key);
         metaField.setValue(value);
         container.add(metaField);
      }

      return fileUploadField;
   }

   @Override
   public void attachHtmlUploadTo(Component component, UploadProperties properties) {
      Html5FileUploadListener uploadListener = new Html5FileUploadListener(new UploadCallback() {

         @Override
         public void fileUploaded(String name, long size, String base64) {
            // TODO Auto-generated method stub

         }

         @Override
         public void allFilesUploaded(List<FileToUpload> list) {
            // TODO Auto-generated method stub

         }

         @Override
         public void fireOnDropStart(int nrOfFiles) {
            // TODO Auto-generated method stub

         }
      }, component);
   }

   @Override
   public Component addCombinedUploadField(final UploadProperties properties) {
      CombinedFiedlTemplate tpl = GWT.create(CombinedFiedlTemplate.class);

      /* get layout */
      HtmlUploadFieldContainer container = new HtmlUploadFieldContainer(tpl.getTemplate(UploadMessages.INSTANCE));

      /* add additional hidden fields */
      final Hidden xhrContentField = new Hidden();
      xhrContentField.setName(FileUploadUIModule.UPLOAD_FILE_XHR_CONTENT_PREFIX + properties.getId());
      container.add(xhrContentField);
      final Hidden xhrLengthField = new Hidden();
      xhrLengthField.setName(FileUploadUIModule.UPLOAD_FILE_XHR_LENGTH_PREFIX + properties.getId());
      container.add(xhrLengthField);
      final Hidden xhrNameField = new Hidden();
      xhrNameField.setName(FileUploadUIModule.UPLOAD_FILE_XHR_NAME_PREFIX + properties.getId());
      container.add(xhrNameField);

      container.setNameField(xhrNameField);

      /* create label */
      final Label filenameLabel = new Label();

      /* get upload field */
      final FileUploadField field = addBaseUploadField(properties, container);
      container.setBaseField(field);
      field.addChangeHandler(new ChangeHandler() {
         @Override
         public void onChange(ChangeEvent event) {
            long size = getSize(field);

            String value = field.getValue();
            String errorMsg = properties.getFilter().doProcess(value, size, null);
            if (null != errorMsg) {
               field.clear();
               errorMsg = "".equals(errorMsg.trim()) ? UploadMessages.INSTANCE.invalidFileMessage() : errorMsg;
               new DwAlertMessageBox(BaseMessages.INSTANCE.error(), errorMsg).show();
               return;
            }

            filenameLabel.setText("");

            /* clear hidden fields */
            xhrContentField.setValue("");
            xhrLengthField.setValue("");
            xhrNameField.setValue("");
         }
      });

      /* add field and label to container */
      container.add(field, new HtmlData("." + CSS_BACKUP_STYLE));
      container.add(filenameLabel, new HtmlData("." + CSS_FILENAME_STYLE));

      /* init XHR */
      Html5FileUploadListener uploadListener = new Html5FileUploadListener(new UploadCallback() {

         @Override
         public void fileUploaded(String name, long size, String base64) {
            String errorMsg = properties.getFilter().doProcess(name, size, base64);
            if (null != errorMsg) {
               errorMsg = "".equals(errorMsg.trim()) ? UploadMessages.INSTANCE.invalidFileMessage() : errorMsg;
               new DwAlertMessageBox(BaseMessages.INSTANCE.error(), errorMsg).show();
               return;
            }

            field.clear();
            filenameLabel.setText(UploadMessages.INSTANCE.fileName(name));

            /* store content in hidden fields */
            xhrContentField.setValue(base64);
            xhrLengthField.setValue(String.valueOf(size));
            xhrNameField.setValue(name);
         }

         @Override
         public void allFilesUploaded(List<FileToUpload> list) {

         }

         @Override
         public void fireOnDropStart(int nrOfFiles) {

         }
      }, container);
      uploadListener.setHoverClassName(CSS_DRAG_STYLE);

      return container;
   }

   @Override
   public String getValueOf(Component component) {
      Container c = (Container) component;
      for (Widget w : c) {
         if (w instanceof FileUploadField) {
            String v = ((FileUploadField) w).getValue();
            if (null != v && !"".equals(v))
               return v;
         } else if (w instanceof Hidden) {
            Hidden h = (Hidden) w;
            if (h.getName().startsWith(FileUploadUIModule.UPLOAD_FILE_XHR_NAME_PREFIX)) {
               String v = h.getValue();
               if (null != v && !"".equals(v))
                  return v;
            }
         }
      }
      return null;
   }

   @Override
   public String getFormAction() {
      return GWT.getModuleBaseURL() + FileUploadUIModule.SERVLET_NAME;
   }

   @Override
   public void handleUploadReturn(FormPanel form) {
      handleUploadReturn(form, UPLOAD_DUMMY_HANDLER);
   }

   @Override
   public void handleUploadReturn(FormPanel form, final UploadHandler handler) {
      form.addSubmitCompleteHandler(new SubmitCompleteHandler() {

         @Override
         public void onSubmitComplete(SubmitCompleteEvent event) {
            String results = event.getResults();
            /* try to work around strange GWT behavior */
            if (null != results) {
               results = new HTML(results).getText();
               if (results.startsWith(FileUploadUIModule.UPLOAD_SUCCESSFUL_PREFIX)) {
                  /* try to get json */
                  JSONValue json = null;

                  if (results.contains("{") && results.contains("}")) {
                     String jsonString = results.substring(results.indexOf("{"), results.lastIndexOf("}") + 1);
                     json = JSONParser.parseStrict(jsonString);
                  }

                  handler.onSuccess(json);
                  return;
               }
            }

            String detail = "";
            if (null != results && results.contains("IllegalArgumentException:")) {
               int idx = results.indexOf("IllegalArgumentException:") + "IllegalArgumentException:".length();
               int endidx = results.indexOf("\n", idx);
               if (-1 == endidx)
                  detail = results.substring(idx).trim();
               else
                  detail = results.substring(idx, endidx).trim();
            }

            DetailErrorDialog dialog = new DetailErrorDialog(BaseMessages.INSTANCE.error(),
                  BaseMessages.INSTANCE.uploadErrorDetail(detail), event.getResults());

            dialog.show();
         }
      });
   }

   @Override
   public void handleUploadReturn(SimpleForm form) {
      handleUploadReturn(form.getFormPanel());
   }

   @Override
   public void handleUploadReturn(SimpleForm form, final UploadHandler handler) {
      handleUploadReturn(form.getFormPanel(), handler);
   }

   @Override
   public void prepareForUpload(SimpleForm form) {
      form.setMethod(Method.POST);
      form.setEncoding(Encoding.MULTIPART);
      form.setAction(getFormAction());
   }

   @Override
   public void prepareForUpload(FormPanel form) {
      form.setMethod(Method.POST);
      form.setEncoding(Encoding.MULTIPART);
      form.setAction(getFormAction());
   }

   @Override
   public Request uploadInterimFile(FileToUpload uploadedFile, AsyncCallback<UploadResponse> callback) {
      return fileUploadDao.uploadInterimFile(uploadedFile, callback);
   }

   @Override
   public long getSize(FileUploadField field) {
      if (null == field.getValue())
         return -1l;

      try {
         String id = field.getId();
         return (long) getSize(id);
      } catch (Exception e) {
         return -1l;
      }
   }

   protected static native int getSize(String id) /*-{
		var f = $wnd.$('#' + id).find("input");
		if(f.length > 0 && f[0].files.length > 0)
			return f[0].files[0].size;
		return 0;
	}-*/;

}
