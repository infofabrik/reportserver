package net.datenwerke.rs.core.client.reportmanager.ui.forms;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Component;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormSubmissionCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleMultiForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SimpleFormAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldEquals;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeManagerDao;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec;

/**
 * 
 *
 */
public class BasicReportVariantForm extends SimpleFormView {

	private final ReportManagerTreeManagerDao dao;
	
	//original write protect and configuration protect values
	private boolean currentWpValue;
	private boolean currentCpValue;

	@Inject
	public BasicReportVariantForm(
		ReportManagerTreeManagerDao dao
		){
		this.dao = dao;
	}

	@Override
	public void configureSimpleForm(SimpleForm form) {
		form.setHeading(ReportmanagerMessages.INSTANCE.editReportVariant() + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));
		
		form.beginRow();
		form.addField(String.class, ReportDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name()); 
		form.addField(String.class, ReportDtoPA.INSTANCE.key(), ReportmanagerMessages.INSTANCE.key());
		form.endRow();
		
		form.addField(String.class, ReportDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.propertyDescription(), new SFFCTextAreaImpl());
	}
	
	@Override
	protected void callbackAfterBinding(SimpleMultiForm form, AbstractNodeDto selectedNode) {
		final SimpleForm xform = SimpleForm.getNewInstance();
		xform.getButtonBar().clear();
		final String wpKey = xform.addField(Boolean.class, ReportmanagerMessages.INSTANCE.writeProtect());
		final String cpKey = xform.addField(Boolean.class, ReportmanagerMessages.INSTANCE.configurationProtect());
		
		boolean isConfigProtected = ((ReportDto)selectedNode).isConfigurationProtected();
		
		xform.setValue(cpKey, isConfigProtected);
		
		// set and disable write protection field when config protection is set
		xform.addCondition(cpKey, new FieldEquals(true), new SimpleFormAction() {
			
			@Override
			public void onSuccess(SimpleForm form) {
				Widget field = form.getDisplayedField(wpKey);
				if(null == field)
					return;
				if(field instanceof Component) {
					form.setValue(wpKey, true);
					((Component)field).disable();
				}
				
				form.updateFormLayout();
			}
			
			@Override
			public void onFailure(SimpleForm form) {
			}
		});
		
		//enable write protection field when config protection is unset
		xform.addCondition(cpKey,  new FieldEquals(false), new SimpleFormAction() {
			
			@Override
			public void onSuccess(SimpleForm form) {
				Widget field = form.getDisplayedField(wpKey);
				if(null == field)
					return;
				if(field instanceof Component) {
					((Component)field).enable();
				}
				
				form.updateFormLayout();
			}
			
			@Override
			public void onFailure(SimpleForm form) {
			}
		});
		
		xform.setValue(wpKey, ((ReportDto)selectedNode).isWriteProtected());
		
		xform.loadFields();
		
		currentWpValue = ((Boolean) xform.getValue(wpKey));
		currentCpValue = ((Boolean) xform.getValue(cpKey));
		
		xform.addSubmissionCallback(new SimpleFormSubmissionCallback(form) {
			@Override
			public void formSubmitted() {
				
				boolean newWpValue = ((Boolean) xform.getValue(wpKey));
				boolean newCpValue = ((Boolean) xform.getValue(cpKey));
				
				long flagToSet = 0;
				long flagToUnset = 0;
				
				if (currentWpValue != newWpValue) {
					if (newWpValue) {
						flagToSet |= AbstractNodeDtoDec.FLAG_WRITE_PROTECT;
					} else {
						flagToUnset |= AbstractNodeDtoDec.FLAG_WRITE_PROTECT;
					}
				}
				
				if (currentCpValue != newCpValue) {
					if (newCpValue) {
						flagToSet |= AbstractNodeDtoDec.FLAG_CONFIGURATION_PROTECT;
					} else {
						flagToUnset |= AbstractNodeDtoDec.FLAG_CONFIGURATION_PROTECT;
					}
				}
				
				dao.setFlag(getSelectedNode(), flagToSet, flagToUnset, false, new RsAsyncCallback<AbstractNodeDto>());
				currentWpValue = ((Boolean) xform.getValue(wpKey));
				currentCpValue = ((Boolean) xform.getValue(cpKey));
			}
		});
		form.addSubForm(xform);
	}


}