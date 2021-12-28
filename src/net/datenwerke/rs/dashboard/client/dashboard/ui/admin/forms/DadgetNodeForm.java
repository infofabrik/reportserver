package net.datenwerke.rs.dashboard.client.dashboard.ui.admin.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.locale.ManagerhelperMessages;
import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormSubmissionCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCustomComponent;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticLabel;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.CustomComponent;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.StaticLabel;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dashboard.client.dashboard.dadgets.ReportDadgetProcessor;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DadgetNodeDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DadgetProcessorHook;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class DadgetNodeForm extends SimpleFormView {

   private final HookHandlerService hookHandler;

   private String adminFormKey;

   @Inject
   public DadgetNodeForm(HookHandlerService hookHandler) {
      super();
      this.hookHandler = hookHandler;
   }

   @Override
   public void configureSimpleForm(final SimpleForm form) {
      form.setHeading(DashboardMessages.INSTANCE.editDadgetNode()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      form.addField(String.class, DadgetNodeDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.propertyName());

      form.addField(String.class, DadgetNodeDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.propertyDescription(),
            new SFFCTextAreaImpl());

      final DadgetNodeDto dadgetNode = (DadgetNodeDto) getSelectedNode();
      if (null == dadgetNode.getDadget()) {
         form.setFieldWidth(0.3);
         final String dadgetKey = form.addField(List.class, new SFFCStaticDropdownList<DadgetDto>() {
            private Map<String, DadgetDto> map;

            @Override
            public Map<String, DadgetDto> getValues() {
               if (null == map) {
                  map = new HashMap<String, DadgetDto>();

                  for (DadgetProcessorHook processor : hookHandler.getHookers(DadgetProcessorHook.class)) {
                     if (processor.supportsDadgetLibrary())
                        map.put(processor.getTitle(), processor.instantiateDadget());
                  }
               }

               return map;
            }
         });

         form.addValueChangeHandler(dadgetKey, new ValueChangeHandler() {
            @Override
            public void onValueChange(ValueChangeEvent event) {
               dadgetNode.setDadget((DadgetDto) form.getValue(dadgetKey));
            }
         });
      } else {
         DadgetDto dadget = dadgetNode.getDadget();
         for (final DadgetProcessorHook processor : hookHandler.getHookers(DadgetProcessorHook.class)) {
            if (processor.consumes(dadget) && processor.hasConfigDialog()) {
               form.addField(StaticLabel.class, new SFFCStaticLabel() {
                  @Override
                  public String getLabel() {
                     return processor.getTitle();
                  }
               });

               final Widget adminConfigDialog = processor.getAdminConfigDialog(dadget, form);
               if (null != adminConfigDialog) {
                  adminFormKey = form.addField(CustomComponent.class, new SFFCCustomComponent() {
                     @Override
                     public Widget getComponent() {
                        return adminConfigDialog;
                     }
                  });
               }
            } else if (processor.consumes(dadget) && !processor.hasConfigDialog()) {
               form.addField(StaticLabel.class, new SFFCStaticLabel() {
                  @Override
                  public String getLabel() {
                     return processor.getTitle();
                  }
               });
            }
         }
      }
   }

   @Override
   protected void onSuccessfulSubmit() {
      reloadView(getSelectedNode());
   }

   @Override
   protected void onSubmit(final SimpleFormSubmissionCallback callback) {
      final DadgetNodeDto dadgetNode = (DadgetNodeDto) getSelectedNode();

      if (null != adminFormKey && dadgetNode.getDadget() instanceof ReportDadgetDto) {

         final DadgetDto dadget = dadgetNode.getDadget();
         SimpleForm adminForm = (SimpleForm) form.getField(adminFormKey);

         ((ReportDadgetDto) dadget)
               .setShowExecuteButton((boolean) adminForm.getValue(ReportDadgetProcessor.SHOW_EXECUTE_KEY));
         ((ReportDadgetDto) dadget)
               .setReport((ReportDto) adminForm.getValue(ReportDadgetProcessor.REPORT_PROPERTY_KEY));
      }

      /* perform server call */
      treeManager.updateNode(dadgetNode, new NotamCallback<AbstractNodeDto>(ManagerhelperMessages.INSTANCE.updated()) {
         @Override
         public void doOnSuccess(AbstractNodeDto result) {
            callback.cbSuccess();
            onSuccessfulSubmit();
         }

         @Override
         public void doOnFailure(Throwable caught) {
            callback.cbFailure(caught);
         }
      });
   }

}