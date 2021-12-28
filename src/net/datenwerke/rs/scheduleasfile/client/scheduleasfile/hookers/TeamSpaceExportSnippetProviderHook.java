package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.hookers;

import java.util.Collection;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.ShowHideFieldAction;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SimpleFormAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldEquals;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.SimpleFormCondition;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ScheduleAsFileInformation;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.JobMetadataConfigurationForm;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.config.TeamSpaceViewConfig;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.simpleform.SFFCTsTeamSpaceSelector;

public class TeamSpaceExportSnippetProviderHook implements ScheduleExportSnippetProviderHook {

   private String isExportAsFileKey;
   private String teamSpaceKey;
   private String folderKey;
   private String nameKey;
   private String descriptionKey;

   @Override
   public boolean appliesFor(ReportDto report, Collection<ReportViewConfiguration> configs) {
      return true;
   }

   @Override
   public void configureSimpleForm(final SimpleForm xform, ReportDto report,
         Collection<ReportViewConfiguration> configs) {
      xform.setFieldWidth(250);

      xform.setLabelWidth(0);
      xform.setLabelAlign(LabelAlign.LEFT);
      isExportAsFileKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return ScheduleAsFileMessages.INSTANCE.askStoreAsFile();
         }
      });

      xform.setLabelAlign(LabelAlign.TOP);
      xform.setLabelWidth(300);
      xform.beginRow();
      teamSpaceKey = xform.addField(TeamSpaceDto.class, ScheduleAsFileMessages.INSTANCE.teamspace());

      folderKey = xform.addField(TsDiskFolderDto.class, ScheduleAsFileMessages.INSTANCE.folder(),
            new SFFCTsTeamSpaceSelector() {
               @Override
               public TeamSpaceDto getTeamSpace() {
                  return (TeamSpaceDto) xform.getValue(teamSpaceKey);
               }
            });
      xform.endRow();

      xform.setLabelAlign(LabelAlign.TOP);
      xform.setFieldWidth(1);
      nameKey = xform.addField(String.class, BaseMessages.INSTANCE.propertyName());
      descriptionKey = xform.addField(String.class, BaseMessages.INSTANCE.propertyDescription(),
            new SFFCTextAreaImpl());

      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(folderKey));
      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(teamSpaceKey));
      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(nameKey));
      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(descriptionKey));

      /* We set the folder to null when the teamspace changes. */
      xform.addCondition(teamSpaceKey, new SimpleFormCondition() {

         /*
          * We want the condition to met when the user changes the form value, NOT when
          * the form is first loaded.
          */
         private boolean firstCall = true;

         @Override
         public boolean isMet(Widget formField, FormFieldProviderHook responsibleHook, SimpleForm form) {
            if (firstCall) {
               firstCall = false;
               return false;
            }
            return true;
         }
      }, new SimpleFormAction() {
         public void onSuccess(SimpleForm form) {
            if (form.isFieldsLoaded()) {

            }
            Widget widget = form.getField(folderKey);
            if (widget instanceof SingleTreeSelectionField) {
               SingleTreeSelectionField stsf = (SingleTreeSelectionField) widget;
               /* Set the folder to null when the teamspace changes. */
               stsf.setValue(null);
            }
         }

         public void onFailure(SimpleForm form) {
         }
      });

      TeamSpaceViewConfig config = getConfig(configs);
      if (null != config)
         xform.setValue(teamSpaceKey, config.getTeamSpace());
   }

   private TeamSpaceViewConfig getConfig(Collection<ReportViewConfiguration> configs) {
      for (ReportViewConfiguration conf : configs)
         if (conf instanceof TeamSpaceViewConfig)
            return (TeamSpaceViewConfig) conf;
      return null;
   }

   @Override
   public boolean isValid(SimpleForm simpleForm) {
      TeamSpaceDto ts = (TeamSpaceDto) simpleForm.getValue(teamSpaceKey);
      AbstractTsDiskNodeDto folder = (AbstractTsDiskNodeDto) simpleForm.getValue(folderKey);
      String name = (String) simpleForm.getValue(nameKey);

      return (null != ts && null != folder && null != name && !name.isEmpty());
   }

   @Override
   public void configureConfig(ReportScheduleDefinition configDto, SimpleForm form) {
      if (!isActive(form))
         return;

      ScheduleAsFileInformation info = new ScheduleAsFileInformation();
      info.setName((String) form.getValue(nameKey));
      info.setDescription((String) form.getValue(descriptionKey));
      info.setFolder((AbstractTsDiskNodeDto) form.getValue(folderKey));

      configDto.addAdditionalInfo(info);
   }

   @Override
   public boolean isActive(SimpleForm simpleForm) {
      return (Boolean) simpleForm.getValue(isExportAsFileKey);
   }

   @Override
   public void loadFields(SimpleForm form, ReportScheduleDefinition definition, ReportDto report) {
      if (null != definition) {
         form.setValue(nameKey, "${now} - " + definition.getTitle());
         ScheduleAsFileInformation info = definition.getAdditionalInfo(ScheduleAsFileInformation.class);
         if (null != info) {
            form.setValue(teamSpaceKey, info.getTeamSpace());
            form.setValue(isExportAsFileKey, true);
            form.setValue(nameKey, info.getName());
            form.setValue(descriptionKey, info.getDescription());
            form.setValue(folderKey, info.getFolder());
         }
      }

      form.loadFields();
   }

   @Override
   public void onWizardPageChange(int pageNr, Widget page, SimpleForm form, ReportScheduleDefinition definition,
         ReportDto report) {
      if (!(page instanceof JobMetadataConfigurationForm))
         return;

      JobMetadataConfigurationForm metadataForm = (JobMetadataConfigurationForm) page;

      String jobTitle = metadataForm.getTitleValue();

      form.setValue(nameKey, "${now} - " + jobTitle);
      if (null != definition) {
         ScheduleAsFileInformation info = definition.getAdditionalInfo(ScheduleAsFileInformation.class);
         if (null != info)
            form.setValue(nameKey, info.getName());
      }

   }

}
