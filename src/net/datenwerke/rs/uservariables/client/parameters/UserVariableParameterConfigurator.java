package net.datenwerke.rs.uservariables.client.parameters;

import java.util.Collection;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.WidgetComponent;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBaseModel;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfiguratorImpl;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.helper.ParameterFieldWrapperForFrontend;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterDefinitionDto;
import net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterInstanceDto;
import net.datenwerke.rs.uservariables.client.parameters.dto.decorator.UserVariableParameterInstanceDtoDec;
import net.datenwerke.rs.uservariables.client.parameters.dto.pa.UserVariableParameterDefinitionDtoPA;
import net.datenwerke.rs.uservariables.client.parameters.locale.UserVariableParameterMessages;
import net.datenwerke.rs.uservariables.client.uservariables.UserVariablesUIService;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.pa.UserVariableDefinitionDtoPA;

/**
 * 
 *
 */
public class UserVariableParameterConfigurator extends ParameterConfiguratorImpl<UserVariableParameterDefinitionDto, UserVariableParameterInstanceDto> {

	private static UserVariableDefinitionDtoPA uvDefPa = GWT.create(UserVariableDefinitionDtoPA.class);
	
	private final UserVariablesUIService userVariableService;

	@Inject
	public UserVariableParameterConfigurator(
		UserVariablesUIService userVariableService	
		){
		this.userVariableService = userVariableService;
	}

	public ImageResource getIcon() {
		return BaseIcon.USER_VARIABLE.toImageResource();
	}

	public String getName() {
		return UserVariableParameterMessages.INSTANCE.userVariable(); //$NON-NLS-1$
	}

	@Override
	protected UserVariableParameterDefinitionDto doGetNewDto() {
		return new UserVariableParameterDefinitionDto();
	}
	
	@Override
	public boolean consumes(Class<? extends ParameterDefinitionDto> type) {
		return UserVariableParameterDefinitionDto.class.equals(type);
	}

	@Override
	public Widget getEditComponentForDefinition(UserVariableParameterDefinitionDto definition, ReportDto report) {
		SimpleForm form = SimpleForm.getInlineInstance();
		
		form.addField(UserVariableDefinitionDto.class, UserVariableParameterDefinitionDtoPA.INSTANCE.userVariableDefinition(), UserVariableParameterMessages.INSTANCE.userVariable(), new SFFCBaseModel<UserVariableDefinitionDto>(){ 

			@Override
			public ListStore<UserVariableDefinitionDto> getAllItemsStore() {
				return userVariableService.getDefinedVariableDefinitionsStore();
			}

			@Override
			public HashMap<ValueProvider<UserVariableDefinitionDto, String>, String> getDisplayProperties() {
				HashMap<ValueProvider<UserVariableDefinitionDto, String>, String> properties = new HashMap<ValueProvider<UserVariableDefinitionDto, String>, String>();
				properties.put(uvDefPa.name(), BaseMessages.INSTANCE.name());
				return properties;
			}

			@Override
			public boolean isMultiSelect() {
				return false;
			}
		});
		
		/* perform binding */
		form.bind(definition);
		
		return form;
	}

	@Override
	public Widget doGetEditComponentForInstance(UserVariableParameterInstanceDto instance, Collection<ParameterInstanceDto> relevantInstances, UserVariableParameterDefinitionDto definition, boolean initial, int labelWidth, String executeReportToken, ReportDto report) {
		HTML text = new HTML(BaseMessages.INSTANCE.value() + " " + ((UserVariableParameterInstanceDtoDec)instance).getValue()); //$NON-NLS-1$
		return new ParameterFieldWrapperForFrontend(definition, instance, new WidgetComponent(text), labelWidth);
	}

}
