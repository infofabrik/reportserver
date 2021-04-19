package net.datenwerke.rs.uservariables.client.variabletypes.string;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCTextArea;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.uservariables.client.uservariables.UserVariableConfiguratorImpl;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.variabletypes.locale.UserVariablesTypesMessages;
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto;
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.pa.StringUserVariableDefinitionDtoPA;
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.pa.StringUserVariableInstanceDtoPA;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;

public class StringConfigurator extends UserVariableConfiguratorImpl<StringUserVariableDefinitionDto, StringUserVariableInstanceDto> {

	public UserVariableDefinitionDto createDTOInstance() {
		return new StringUserVariableDefinitionDto();
	}

	public ImageResource getIcon() {
		return BaseIcon.PARAGRAPH.toImageResource();
	}

	public String getName() {
		return UserVariablesTypesMessages.INSTANCE.textVariable(); //$NON-NLS-1$
	}

	public Widget getEditComponent(StringUserVariableDefinitionDto definition) {
		SimpleForm form = SimpleForm.getInlineInstance();

		form.addField(Integer.class, StringUserVariableDefinitionDtoPA.INSTANCE.width(), BaseMessages.INSTANCE.width()); 
		form.addField(Integer.class, StringUserVariableDefinitionDtoPA.INSTANCE.height(), BaseMessages.INSTANCE.height()); 
		form.bind(definition);
		
		return form;
	}

	@Override
	public Widget getEditComponent(StringUserVariableInstanceDto instance, final StringUserVariableDefinitionDto definition) {
		SimpleForm form = SimpleForm.getInlineInstance();
		
		if(definition.getHeight() == 1)
			form.addField(String.class, StringUserVariableInstanceDtoPA.INSTANCE.value(), BaseMessages.INSTANCE.value()); 
		else
			form.addField(String.class, StringUserVariableInstanceDtoPA.INSTANCE.value(), BaseMessages.INSTANCE.value(), new SFFCTextArea(){ 
				public int getHeight() {
					return definition.getHeight();
				}
	
				public int getWidth() {
					return definition.getWidth();
				}
			});
		
		/* bind instance */
		form.bind(instance);
		
		return form;
	}

	public Object getDisplayValue(StringUserVariableInstanceDto instance, StringUserVariableDefinitionDto definition) {
		return String.valueOf(instance.getValue());
	}
	
	@Override
	public void configureEditWindow(StringUserVariableDefinitionDto definition,
			Window window) {
		window.setWidth(400);
		window.setHeight(220);
	}

	@Override
	public void configureEditWindow(StringUserVariableInstanceDto instance,
			StringUserVariableDefinitionDto definition, Window window) {
		window.setWidth(500);
		window.setHeight(170 + definition.getHeight());
	}

}
