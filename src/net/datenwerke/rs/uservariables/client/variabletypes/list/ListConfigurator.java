package net.datenwerke.rs.uservariables.client.variabletypes.list;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.Window;

import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.uservariables.client.uservariables.UserVariableConfiguratorImpl;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto;
import net.datenwerke.rs.uservariables.client.variabletypes.locale.UserVariablesTypesMessages;

public class ListConfigurator extends UserVariableConfiguratorImpl<ListUserVariableDefinitionDto, ListUserVariableInstanceDto> {

	public ImageResource getIcon() {
		return BaseIcon.LINK.toImageResource();
	}

	public String getName() {
		return UserVariablesTypesMessages.INSTANCE.listVariable(); //$NON-NLS-1$
	}

	@Override
	public UserVariableDefinitionDto createDTOInstance() {
		return new ListUserVariableDefinitionDto();
	}

	@Override
	public Widget getEditComponent(ListUserVariableDefinitionDto definition) {
		new DwAlertMessageBox(UserVariablesTypesMessages.INSTANCE.noEditTitle(), UserVariablesTypesMessages.INSTANCE.noEditMessage()).show();
		return null;
	}

	@Override
	public Widget getEditComponent(final ListUserVariableInstanceDto instance,
			ListUserVariableDefinitionDto definition) {
		SimpleForm form = SimpleForm.getInlineInstance();

		form.addField(String.class, new ValueProvider<ListUserVariableInstanceDto, String>() {
			@Override
			public String getValue(ListUserVariableInstanceDto instance) {
				String value = "";
				Iterator<String> it = null == instance.getValue() ? new HashSet().iterator() : instance.getValue().iterator();
				if(it.hasNext()){
					value = it.next();
					while(it.hasNext())
						value += " | " + it.next();
				}
				
				return value;
			}

			@Override
			public void setValue(ListUserVariableInstanceDto instance, String value) {
				Set<String> values = new HashSet<String>();
				if(null != value){
					List<String> pValues = Arrays.asList(value.split("\\|"));
					
					for(String v : pValues){
						v = v.trim();
						if(! "".equals(v))
							values.add(v);
					}
				} 
				
				instance.setValue(values);
			}

			@Override
			public String getPath() {
				return null;
			}
		}, BaseMessages.INSTANCE.value(), new SFFCTextAreaImpl());
		
		/* bind instance */
		form.bind(instance);
		
		return form;
	}

	@Override
	public Object getDisplayValue(ListUserVariableInstanceDto instance,
			ListUserVariableDefinitionDto definition) {
		if(null == instance.getValue() || instance.getValue().isEmpty())
			return "[]";

		Iterator<String> it = instance.getValue().iterator();
		
		String display = it.next();
		while(it.hasNext())
			display += ", " + it.next();
		
		return display;
	}

	@Override
	public void configureEditWindow(ListUserVariableDefinitionDto definition, Window window) {
		window.setWidth(500);
		window.setHeight(280);
	}

	@Override
	public void configureEditWindow(ListUserVariableInstanceDto instance,
			ListUserVariableDefinitionDto definition, Window window) {
		window.setWidth(500);
		window.setHeight(280);
	}

}
