package net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages;

import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.forms.wizard.Validatable;
import net.datenwerke.gxtdto.client.forms.wizard.WizardAware;
import net.datenwerke.gxtdto.client.forms.wizard.WizardDialog;
import net.datenwerke.gxtdto.client.forms.wizard.WizardResizer;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.utils.misc.Nullable;

public class JobMetadataConfigurationForm extends DwContentPanel implements Validatable, WizardAware, WizardResizer {

	private String titleFieldKey;
	private String descriptionFieldKey;
	
	private SimpleForm form;
	
	private VerticalLayoutContainer verticalContainer;
	
	@Inject
	public JobMetadataConfigurationForm(
			@Assisted ReportDto report,
			@Assisted Collection<ReportViewConfiguration> configs,
			@Nullable @Assisted ReportScheduleDefinition definition
			) {
		super();
		
		setBorders(false);
		setBodyBorder(false);
		setHeaderVisible(false);
		enableScrollContainer();
		
		verticalContainer = new VerticalLayoutContainer();
		
		DwContentPanel wrapper = new DwContentPanel();
		wrapper.setLightDarkStyle();
		wrapper.setHeading(SchedulerMessages.INSTANCE.gridJobIdLabel());
		wrapper.setHeight(350);
		wrapper.setWidget(verticalContainer);
		
		initForm(report, configs, definition);
		
		add(wrapper, new MarginData(10));
		
	}

	protected void initForm(ReportDto report, Collection<ReportViewConfiguration> configs, ReportScheduleDefinition definition) {
		form = SimpleForm.getInlineInstance();
		
		form.setLabelAlign(LabelAlign.TOP);
		
		titleFieldKey = form.addField(String.class, SchedulerMessages.INSTANCE.jobTitle());
		descriptionFieldKey = form.addField(String.class, SchedulerMessages.INSTANCE.jobDescription(), new SFFCTextAreaImpl(200));
		
		if (null != definition) {
			if (null != definition.getTitle())
				form.setValue(titleFieldKey, definition.getTitle());
			
			if (null != definition.getDescription())
				form.setValue(descriptionFieldKey, definition.getDescription());
		} else {
			// default values are fetched from the report
			if (null != report.getName())
				form.setValue(titleFieldKey, report.getName());
			
			if (null != report.getDescription())
				form.setValue(descriptionFieldKey, report.getDescription());
		}
		
		form.loadFields();
		
		verticalContainer.add(form);
	}
	
	public String getTitleValue() {
		return (String) form.getValue(titleFieldKey);
	}
	
	public String getDescriptionValue() {
		return (String) form.getValue(descriptionFieldKey);
	}
	
	@Override
	public boolean isValid() {
		return null != getTitleValue() && !"".equals(getTitleValue());
	}

	@Override
	public void setWizard(WizardDialog dialog) {
	}

	@Override
	public int getPageHeight() {
		return 460;
	}

}
