package net.datenwerke.rs.scriptreport.client.scriptreport.parameters;

import com.sencha.gxt.widget.core.client.Component;

import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.helper.DefaultValueSetter;
import net.datenwerke.rs.core.client.parameters.helper.ParameterFieldWrapperForFrontend;

public class ScriptParameterParameterWrapper extends ParameterFieldWrapperForFrontend {

	private ScriptParameterClientEditComponent configurator;

	public ScriptParameterParameterWrapper(ParameterDefinitionDto definition, ParameterInstanceDto instance, Component component, int labelWidth ){
		super(definition, instance, component, labelWidth, null);
	}
	
	public ScriptParameterParameterWrapper(ParameterDefinitionDto definition, ParameterInstanceDto instance, Component component, int labelWidth, DefaultValueSetter defaultValueSetter){
		super(definition, instance, component, labelWidth, defaultValueSetter);
	}
	
	public void setScriptParameterConfigurator(
			ScriptParameterClientEditComponent scriptParameterClientEditComponent) {
		this.configurator = scriptParameterClientEditComponent;
	}

	public ScriptParameterClientEditComponent getConfigurator() {
		return configurator;
	}
}
