package net.datenwerke.gf.client.upload;

import com.google.gwt.http.client.Request;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.form.FormPanel;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.dto.UploadProperties;
import net.datenwerke.gf.client.upload.dto.UploadResponse;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;

public interface FileUploadUiService {

	public interface UploadHandler {
		void onSuccess(JSONValue json);
		
		void onError();
	}
	
	FileUploadField addBaseUploadField(UploadProperties uploadProperties,
			Container fieldWrapper);

	String getFormAction();

	void handleUploadReturn(FormPanel form);
	
	void handleUploadReturn(FormPanel form, UploadHandler handler);
	
	void handleUploadReturn(SimpleForm form);
	
	void handleUploadReturn(SimpleForm form, UploadHandler handler);

	void prepareForUpload(SimpleForm form);
	
	void prepareForUpload(FormPanel form);

	void attachHtmlUploadTo(Component component,
			UploadProperties uploadProperties);
	
	Component addCombinedUploadField(UploadProperties properties);

	Request uploadInterimFile(FileToUpload uploadedFile,
			AsyncCallback<UploadResponse> rsAsyncCallback);

	String getValueOf(Component component);

	long getSize(FileUploadField field);


}
