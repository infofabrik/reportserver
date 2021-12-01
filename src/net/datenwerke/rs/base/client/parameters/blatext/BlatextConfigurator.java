package net.datenwerke.rs.base.client.parameters.blatext;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCRichTextEditor;
import net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.blatext.dto.BlatextParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
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
public class BlatextConfigurator extends ParameterConfiguratorImpl<BlatextParameterDefinitionDto, BlatextParameterInstanceDto> {

	private final Resources resources = GWT.create(Resources.class);
	
	interface Resources extends ClientBundle {
		@Source("blatext.gss")
		Style css();    
	}

	interface Style extends CssResource {
		@ClassName("rs-parameter-text")
		String parameterText();
	}	
	
	public BlatextConfigurator(){
		super();
		
		resources.css().ensureInjected();
	}
	
	@Override
	public Widget getEditComponentForDefinition(BlatextParameterDefinitionDto definition, ReportDto report) {
		SimpleForm form = SimpleForm.getInlineInstance();
		
		form.addField(String.class, BlatextParameterDefinitionDto.PROPERTY_VALUE, RsMessages.INSTANCE.text(), new SFFCRichTextEditor() { 
			public int getWidth() {
				return 300;
			}
			
			public int getHeight() {
				return 400;
			}
		});
		
		/* bind definition */
		form.setValue(BlatextParameterDefinitionDto.PROPERTY_VALUE, definition.getValue());
		
		form.loadFields();
		
		return form;
	}
	
	@Override
	public void updateDefinitionOnSubmit(
			BlatextParameterDefinitionDto definition, Widget component) {
		SimpleForm form = (SimpleForm) component;
		
		String value = (String) form.getValue(BlatextParameterDefinitionDto.PROPERTY_VALUE);
		
		definition.setValue(value);
	}

	@Override
	public Widget doGetEditComponentForInstance(BlatextParameterInstanceDto instance, Collection<ParameterInstanceDto> relevantInstances, final BlatextParameterDefinitionDto definition, boolean initial, int labelWidth, String executeReportToken, ReportDto report) {
		HTML text = new HTML(definition.getValue());
		text.addStyleName(resources.css().parameterText());
		return text;
	}

	public String getName() {
		return RsMessages.INSTANCE.displayText();
	}

	@Override
	protected BlatextParameterDefinitionDto doGetNewDto() {
		return new BlatextParameterDefinitionDto();
	}

	@Override
	public boolean consumes(Class<? extends ParameterDefinitionDto> type) {
		return BlatextParameterDefinitionDto.class.equals(type);
	}
	
	public ImageResource getIcon() {
		return BaseIcon.ALIGN_LEFT.toImageResource(); 
	}

	@Override
	public ParameterType getType(){
		return ParameterType.Separator;
	}


}
