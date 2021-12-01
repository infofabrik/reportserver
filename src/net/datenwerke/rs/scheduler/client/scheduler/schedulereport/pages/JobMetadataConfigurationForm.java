package net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.forms.wizard.Validatable;
import net.datenwerke.gxtdto.client.forms.wizard.WizardPageChangeListener;
import net.datenwerke.gxtdto.client.forms.wizard.WizardResizer;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.utils.misc.Nullable;

public class JobMetadataConfigurationForm extends DwContentPanel
      implements Validatable, WizardResizer, WizardPageChangeListener {

   private String titleFieldKey;
   private String descriptionFieldKey;

   private SimpleForm form;

   private VerticalLayoutContainer verticalContainer;

   private ReportScheduleDefinition jobDefinition;

   @Inject
   public JobMetadataConfigurationForm(
         @Nullable @Assisted ReportScheduleDefinition jobDefinition
         ) {
      super();

      this.jobDefinition = jobDefinition;

      setBorders(false);
      setBodyBorder(false);
      setHeaderVisible(false);
      enableScrollContainer();

      verticalContainer = new VerticalLayoutContainer();

      DwContentPanel wrapper = new DwContentPanel();
      wrapper.setLightDarkStyle();
      wrapper.setHeading(SchedulerMessages.INSTANCE.jobMetadata());
      wrapper.setInfoText(SchedulerMessages.INSTANCE.jobMetadataConfigDescription());
      wrapper.setHeight(350);
      wrapper.setWidget(verticalContainer);

      initForm();

      setWidget(wrapper);

   }

   protected void initForm() {
      form = SimpleForm.getInlineInstance();

      form.setLabelAlign(LabelAlign.TOP);

      titleFieldKey = form.addField(String.class, SchedulerMessages.INSTANCE.jobTitle());
      descriptionFieldKey = form.addField(String.class, SchedulerMessages.INSTANCE.jobDescription(),
            new SFFCTextAreaImpl(200));

      if (null != jobDefinition) {
         if (null != jobDefinition.getTitle())
            form.setValue(titleFieldKey, jobDefinition.getTitle());

         if (null != jobDefinition.getDescription())
            form.setValue(descriptionFieldKey, jobDefinition.getDescription());
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
   public int getPageHeight() {
      return 426;
   }

   @Override
   public void onPageChange(int pageNr, Widget page) {
      if (null == jobDefinition && page instanceof JobReportConfigurationForm) {
         JobReportConfigurationForm reportPage = (JobReportConfigurationForm) page;
         ReportDto report = reportPage.getReport();
         // default values are fetched from the report if no definition is set
         form.setValue(titleFieldKey, report.getName());
         form.setValue(descriptionFieldKey, report.getDescription());
      }

   }

}
