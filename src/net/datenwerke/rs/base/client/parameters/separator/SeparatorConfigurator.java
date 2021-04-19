package net.datenwerke.rs.base.client.parameters.separator;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.separator.dto.SeparatorParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfiguratorImpl;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
@Singleton
public class SeparatorConfigurator extends ParameterConfiguratorImpl<SeparatorParameterDefinitionDto, SeparatorParameterInstanceDto> {

	private final Resources resources = GWT.create(Resources.class);
	
	interface Resources extends ClientBundle {
		@Source("separator.gss")
		Style css();    
	}

	interface Style extends CssResource {
		@ClassName("rs-parameter-horizontal-separator")
		String horizontalSeparator();
	}	
	
	public SeparatorConfigurator(){
		super();
		
		resources.css().ensureInjected();
	}	
	
	@Override
	public Widget getEditComponentForDefinition(SeparatorParameterDefinitionDto definition, ReportDto report) {
		SimpleForm form = SimpleForm.getInlineInstance();
		
		/* bind definition */
		form.bind(definition);
		
		return form;
	}


	@Override
	public Widget doGetEditComponentForInstance(SeparatorParameterInstanceDto instance, Collection<ParameterInstanceDto> relevantInstances, final SeparatorParameterDefinitionDto definition, boolean initial, int labelWidth, String executeReportToken, ReportDto report) {
		Label dummy = new Label();
		dummy.setStyleName(resources.css().horizontalSeparator());
		return dummy;
	}

	public String getName() {
		return RsMessages.INSTANCE.separator();
	}

	@Override
	protected SeparatorParameterDefinitionDto doGetNewDto() {
		return new SeparatorParameterDefinitionDto();
	}

	@Override
	public boolean consumes(Class<? extends ParameterDefinitionDto> type) {
		return SeparatorParameterDefinitionDto.class.equals(type);
	}
	
	public ImageResource getIcon() {
		return BaseIcon.DISCONNECT.toImageResource();
	}

	@Override
	public ParameterType getType(){
		return ParameterType.Separator;
	}

}
