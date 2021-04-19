package net.datenwerke.rs.fileserver.client.fileserver.fileeditors.text;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel.ToolBarEnhancer;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormSubmissionCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCodeMirror;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.model.StringBaseModel;
import net.datenwerke.gxtdto.client.model.pa.StringBaseModelPa;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerDao;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;

import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import com.google.inject.Inject;

public class TextFileView extends SimpleFormView{

	@Inject
	private FileServerDao fileServerDao;
	
	private StringBaseModel bindingEntity = new StringBaseModel();
	
	@Override
	public String getViewId() {
		return "_file_edit_view";
	}
	
	@Override
	public boolean isSticky() {
		return true;
	}
	
	@Override
	protected VerticalLayoutData getFormLayoutData() {
		return new VerticalLayoutData(1,1, new Margins(10));
	}
	
	@Override
	protected boolean useScrollWrapper() {
		return false;
	}

	@Override
	protected void configureSimpleForm(final SimpleForm form) {
		form.setHeading(FileServerMessages.INSTANCE.fileViewHeader() + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));
		form.setAutoHeight();
		
		form.setFieldWidth(-1);
		form.setFieldHeight(1);
		
		final String codeMirrorField = form.addField(String.class, StringBaseModelPa.INSTANCE.value(), new SFFCCodeMirror() {
			
			@Override
			public int getWidth() {
				return -1;
			}
			
			@Override
			public int getHeight() {
				return -1;
			}

			@Override
			public boolean lineNumbersVisible() {
				return true;
			}

			
			@Override
			public String getLanguage() {
				String ct = ((FileServerFileDto)getSelectedNode()).getContentType();
				String name = ((FileServerFileDto)getSelectedNode()).getName();
				name = null == name ? "" : name;
					
				if(null == ct)
					return "none";
				
				if(ct.contains("xml"))
					return "xml";
				
				if(ct.contains("groovy") || name.endsWith(".rs") || name.endsWith(".groovy"))
					return "text/x-groovy";
				
				return ct;
			}

			@Override
			public ToolBarEnhancer getEnhancer() {
				return null;
		 	}
		});
		
		mask(BaseMessages.INSTANCE.loadingMsg());
		fileServerDao.loadFileDataAsString((FileServerFileDto) getSelectedNode(), new RsAsyncCallback<String>(){
			@Override
			public void onSuccess(String result) {
				unmask();
				
				bindingEntity.setValue(result);
				form.setValue(codeMirrorField, result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				super.onFailure(caught);
				
				unmask();
			}
		});
	}
	
	@Override
	protected void onSubmit(SimpleFormSubmissionCallback callback) {
		mask(BaseMessages.INSTANCE.loadingMsg());
		fileServerDao.updateFile((FileServerFileDto) getSelectedNode(), bindingEntity.getValue(), new NotamCallback<Void>(BaseMessages.INSTANCE.changesApplied()){
			@Override
			public void doOnSuccess(Void result) {
				unmask();
			}
			
			@Override
			public void doOnFailure(Throwable caught) {
				unmask();
			}
		});
	}
	
	
	@Override
	public String getComponentHeader() {
		return FileServerMessages.INSTANCE.fileViewHeader();
	}
	
	@Override
	protected Object getBindingEntity() {
		return bindingEntity;
	}
	
}
