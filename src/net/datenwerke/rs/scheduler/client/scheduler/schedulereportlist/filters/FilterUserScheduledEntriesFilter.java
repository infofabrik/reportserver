package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.filters;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.SchedulerAdminModule;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListFilter;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeBasic;

public class FilterUserScheduledEntriesFilter implements ScheduledReportListFilter {

   private final Provider<UITree> tree;

   private FormFieldProviderHook executorHook;
   private Widget executorField;
   private FormFieldProviderHook ownerHook;
   private Widget ownerField;
   private FormFieldProviderHook toUserHook;
   private Widget scheduledByField;
   private FormFieldProviderHook scheduledByHook;
   private Widget toUserField;

   @Inject
   public FilterUserScheduledEntriesFilter(@UserManagerTreeBasic Provider<UITree> tree) {
      this.tree = tree;
   }

   public Iterable<Widget> getFilter(final ScheduledReportListPanel scheduledReportListPanel) {
      executorHook = SimpleForm.getResponsibleHooker(UserDto.class, new SFFCGenericTreeNode() {
         @Override
         public UITree getTreeForPopup() {
            return tree.get();
         }
      });
      executorField = executorHook.createFormField();

      executorHook.addValueChangeHandler(new ValueChangeHandler() {
         @Override
         public void onValueChange(ValueChangeEvent event) {
            scheduledReportListPanel.reload();
         }
      });

      FieldLabel executor = new FieldLabel(executorField, SchedulerMessages.INSTANCE.executor());
      executor.setLabelWidth(120);

      ownerHook = SimpleForm.getResponsibleHooker(UserDto.class, new SFFCGenericTreeNode() {
         @Override
         public UITree getTreeForPopup() {
            return tree.get();
         }
      });
      ownerField = ownerHook.createFormField();

      ownerHook.addValueChangeHandler(new ValueChangeHandler() {
         @Override
         public void onValueChange(ValueChangeEvent event) {
            scheduledReportListPanel.reload();
         }
      });

      FieldLabel owner = new FieldLabel(ownerField, SchedulerMessages.INSTANCE.owner());
      owner.setLabelWidth(120);

      scheduledByHook = SimpleForm.getResponsibleHooker(UserDto.class, new SFFCGenericTreeNode() {
         @Override
         public UITree getTreeForPopup() {
            return tree.get();
         }
      });
      scheduledByField = scheduledByHook.createFormField();

      scheduledByHook.addValueChangeHandler(new ValueChangeHandler() {
         @Override
         public void onValueChange(ValueChangeEvent event) {
            scheduledReportListPanel.reload();
         }
      });

      FieldLabel scheduledBy = new FieldLabel(scheduledByField, SchedulerMessages.INSTANCE.scheduledBy());
      scheduledBy.setLabelWidth(120);

      toUserHook = SimpleForm.getResponsibleHooker(UserDto.class, new SFFCGenericTreeNode() {
         @Override
         public UITree getTreeForPopup() {
            return tree.get();
         }
      });
      toUserField = toUserHook.createFormField();

      toUserHook.addValueChangeHandler(new ValueChangeHandler() {
         @Override
         public void onValueChange(ValueChangeEvent event) {
            scheduledReportListPanel.reload();
         }
      });

      FieldLabel toUser = new FieldLabel(toUserField, SchedulerMessages.INSTANCE.toUser());
      toUser.setLabelWidth(120);

      ArrayList<Widget> widgets = new ArrayList<Widget>();
      widgets.add(executor);
      widgets.add(owner);
      widgets.add(toUser);
      widgets.add(scheduledBy);

      return widgets;
   }

   public void configure(ScheduledReportListPanel scheduledReportListPanel, JobFilterConfigurationDto config,
         List<JobFilterCriteriaDto> addCriterions) {
      if (config instanceof ReportServerJobFilterDto) {
         ((ReportServerJobFilterDto) config).setFromUser(null);
         ((ReportServerJobFilterDto) config).setToUser(null);

         if (null != executorField && null != ownerField && null != toUserField) {
            UserDto executorUser = (UserDto) executorHook.getValue(executorField);
            UserDto ownerUser = (UserDto) ownerHook.getValue(ownerField);
            UserDto toUser = (UserDto) toUserHook.getValue(toUserField);
            UserDto scheduledByUser = (UserDto) scheduledByHook.getValue(scheduledByField);

            if (null != executorUser)
               ((ReportServerJobFilterDto) config).setFromUser(executorUser);
            if (null != ownerUser)
               ((ReportServerJobFilterDto) config).setOwner(ownerUser);
            if (null != toUser)
               ((ReportServerJobFilterDto) config).setToUser(toUser);
            if (null != scheduledByUser)
               ((ReportServerJobFilterDto) config).setScheduledBy(scheduledByUser);
         }
      }
   }

   @Override
   public boolean appliesTo(String panelName) {
      return SchedulerAdminModule.ADMIN_FILTER_PANEL.equals(panelName);
   }

}
