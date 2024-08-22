package net.datenwerke.rs.dashboard.client.dashboard.dadgets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwFlowContainer;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.propertywidgets.ParameterView;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardDao;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ParameterDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ParameterDadgetDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DadgetProcessorHook;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ParameterDadgetProcessor implements DadgetProcessorHook {

   private DashboardDao dashboardDao;

   @Inject
   public ParameterDadgetProcessor(DashboardDao dashboardDao) {
      this.dashboardDao = dashboardDao;
   }

   @Override
   public BaseIcon getIcon() {
      return BaseIcon.FILTER;
   }

   @Override
   public boolean isRedrawOnMove() {
      return false;
   }

   @Override
   public String getTitle() {
      return DashboardMessages.INSTANCE.parameterTitle();
   }

   @Override
   public String getDescription() {
      return DashboardMessages.INSTANCE.parameterDescription();
   }

   @Override
   public boolean consumes(DadgetDto dadget) {
      return dadget instanceof ParameterDadgetDto;
   }

   @Override
   public DadgetDto instantiateDadget() {
      return new ParameterDadgetDtoDec();
   }

   @Override
   public void draw(final DadgetDto dadget, final DadgetPanel panel) {
      DashboardDtoDec dashboard = (DashboardDtoDec) panel.getView().getDashboard();
      if (null != dashboard && null != dashboard.getTemporaryInstanceMap()) {
         draw(dadget, panel, dashboard.getTemporaryInstanceMap());
      } else {
         panel.mask();
         dashboardDao.getDashboardParameterInstances(panel.getView().getDashboard(),
               new RsAsyncCallback<Map<String, ParameterInstanceDto>>() {
                  @Override
                  public void onSuccess(Map<String, ParameterInstanceDto> instanceMap) {
                     draw(dadget, panel, instanceMap);
                  }
               });
      }

      panel.setHeading(getTitle());
      panel.setHeaderIcon(BaseIcon.SEARCH);
      panel.addStyleName("rs-dadget-parameter");
   }

   private void draw(final DadgetDto dadget, final DadgetPanel panel,
         final Map<String, ParameterInstanceDto> instanceMap) {
      final List<ParameterDefinitionDto> definitions = new ArrayList<>();
      final Set<ParameterInstanceDto> instances = new HashSet<>();

      for (ParameterInstanceDto inst : instanceMap.values()) {
         definitions.add(inst.getDefinition());
         instances.add(inst);
      }

      TextButton ok = new DwTextButton(BaseMessages.INSTANCE.submit(), BaseIcon.CHECK);
      ok.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            panel.getView().mask(BaseMessages.INSTANCE.storingMsg());
            dashboardDao.setDashboardParameterInstances(panel.getView().getDashboard(), instances,
                  new AsyncCallback<List<DadgetDto>>() {

                     @Override
                     public void onFailure(Throwable caught) {
                        panel.getView().unmask();
                     }

                     @Override
                     public void onSuccess(List<DadgetDto> result) {
                        panel.getView().unmask();

                        for (DadgetDto dadget : result) {
                           final DadgetPanel dadgetPanel = panel.getView().getPanel(dadget);
                           dadgetPanel.updateDadget(dadget);
                           Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                              @Override
                              public void execute() {
                                 dadgetPanel.refresh();
                              }
                           });
                        }
                     }
                  });
         }
      });

      ParameterView pv = new ParameterView(definitions, instances);
      VerticalLayoutContainer parameterContainer = pv.getParameterContainer();

      ButtonBar bb = new ButtonBar();
      bb.add(new FillToolItem());
      bb.add(ok);
      parameterContainer.add(bb);

      DwFlowContainer wrapper = new DwFlowContainer();
      wrapper.setScrollMode(ScrollMode.AUTO);
      wrapper.add(parameterContainer);

      panel.add(wrapper);

      panel.unmask();

      /* needed to activate scroll */
      Scheduler.get().scheduleDeferred(new ScheduledCommand() {
         @Override
         public void execute() {
            panel.forceLayout();
         }
      });
   }

   @Override
   public Widget getAdminConfigDialog(DadgetDto dadget, SimpleForm wrappingForm) {
      if (dadget.getHeight() < 1)
         dadget.setHeight(250);

      // no configuration
      final SimpleForm form = SimpleForm.getInlineInstance();
      form.setFieldWidth(400);
      form.setLabelWidth(90);

      return form;
   }

   @Override
   public boolean supportsDadgetLibrary() {
      return true;
   }

   @Override
   public boolean readyToDisplayParameters(DadgetPanel dadgetPanel) {
      return false;
   }

   @Override
   public void displayConfigDialog(DadgetDto dadget, DadgetConfigureCallback dadgetConfigureCallback) {
      // no configuration
   }

   @Override
   public boolean hasConfigDialog() {
      return false;
   }

   @Override
   public void addTools(DadgetPanel dadgetPanel) {
   }

}
