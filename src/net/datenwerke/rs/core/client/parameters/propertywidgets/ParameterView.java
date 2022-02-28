package net.datenwerke.rs.core.client.parameters.propertywidgets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwHorizontalFlowLayoutContainer;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.rs.core.client.parameters.ParameterUIService;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfigurator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.DeselectionAwareView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ParameterView extends ReportExecutorMainPanelView implements DeselectionAwareView {

   public static final String VIEW_ID = "parameter";

   @Inject
   private static ParameterUIService parameterService;

   private VerticalLayoutContainer parameterContainer;

   private Map<ParameterDefinitionDto, Widget[]> componentMap = new HashMap<ParameterDefinitionDto, Widget[]>();

   private Map<ParameterDefinitionDto, ParameterConfigurator> configuratorMap = new HashMap<ParameterDefinitionDto, ParameterConfigurator>();

   private List<ParameterDefinitionDto> parameterDefinitions;
   private Set<ParameterInstanceDto> parameterInstances;
   
   private Set<List<Object>> parametersChanged = new HashSet<>();

   private ReportDto report;

   public ParameterView(List<ParameterDefinitionDto> parameterDefinitions,
         Set<ParameterInstanceDto> parameterInstances) {
      this.parameterDefinitions = parameterDefinitions;
      this.parameterInstances = parameterInstances;

   }

   public ParameterView(ReportDto report) {
      this.report = report;
      this.parameterDefinitions = report.getParameterDefinitions();
      this.parameterInstances = report.getParameterInstances();
   }

   @Override
   public String getViewId() {
      return VIEW_ID;
   }

   @Override
   public boolean wantsToBeDefault() {
      return true;
   }

   @Override
   public String getComponentHeader() {
      return ReportexecutorMessages.INSTANCE.parameterWidgetHeader();
   }

   public VerticalLayoutContainer getParameterContainer() {
      /* create panel */
      parameterContainer = new VerticalLayoutContainer();

      int labelWidth = -1;

      /* add all parameters */
      DwHorizontalFlowLayoutContainer colLayout = new DwHorizontalFlowLayoutContainer();
      for (final ParameterDefinitionDto definition : parameterDefinitions) {
         /* update label width */
         if (null != definition.getLabelWidth())
            labelWidth = definition.getLabelWidth();

         /* get instance */
         ParameterInstanceDto instance = parameterService.getParameterInstanceFor(parameterInstances, definition);
         Collection<ParameterInstanceDto> relevantInstances = parameterService
               .getRelevantInstancesFor(parameterDefinitions, parameterInstances, definition);

         /* get configurator */
         ParameterConfigurator configurator = getConfigurator(definition);
         if (null == configurator)
            throw new IllegalArgumentException("Should have a configurator for this parameter definition"); //$NON-NLS-1$

         /* get edit form */
         Widget editWidget = configurator.getEditComponentForInstance(instance, definition, relevantInstances, true,
               labelWidth, getExecuteReportToken(), report);
         componentMap.put(definition, new Widget[] { colLayout, editWidget });

         /* hide parameter if hidden */
         editWidget.setVisible(!definition.isHidden());

         /* listen to change events and update paramaters */
         listenToChangeEvents(definition);

         /* add edit form */
         colLayout.add(editWidget);

         /* if definition is block, create new colLayout */
         if (!definition.isDisplayInline()) {
            parameterContainer.add(colLayout, new VerticalLayoutData(1, -1));

            colLayout = new DwHorizontalFlowLayoutContainer();
         }
      }

      if (-1 == parameterContainer.getWidgetIndex(colLayout))
         parameterContainer.add(colLayout, new VerticalLayoutData(1, -1));

      return parameterContainer;
   }

   @Override
   public Widget getViewComponent() {
      this.parameterContainer = getParameterContainer();

      /* create wrapper */
      DwContentPanel wrapper = new DwContentPanel();
      wrapper.setLightDarkStyle();
      wrapper.setHeading(ReportexecutorMessages.INSTANCE.frontendHeadline());
      wrapper.add(parameterContainer);

      VerticalLayoutContainer scrollWrapper = new VerticalLayoutContainer();
      scrollWrapper.setScrollMode(ScrollMode.AUTO);
      scrollWrapper.add(wrapper, new VerticalLayoutData(1, -1, new Margins(10)));

      return scrollWrapper;
   }

   private void listenToChangeEvents(final ParameterDefinitionDto definition) {
      /* get instance */
      final ParameterInstanceDto instance = parameterService.getParameterInstanceFor(parameterInstances, definition);

      instance.addInstanceChangedHandler(new ObjectChangedEventHandler<Dto>() {

         @Override
         public void onObjectChangedEvent(ObjectChangedEvent<Dto> event) {
            if (null != report)
               report.fireObjectChangedEvent();

            /* instance is not default any longer */
            boolean silent = instance.isSilenceEvents();
            instance.silenceEvents(true);

            instance.setStillDefault(false);

            instance.silenceEvents(silent);
         }
      });

      instance.addInstanceChangedHandler(new ObjectChangedEventHandler<Dto>() {

         @Override
         public void onObjectChangedEvent(ObjectChangedEvent<Dto> event) {
            int labelWidth = -1;

            /* test all definitions if they are interested in that particular change */
            for (final ParameterDefinitionDto aDefinition : parameterDefinitions) {
               /* update label width */
               if (null != aDefinition.getLabelWidth())
                  labelWidth = aDefinition.getLabelWidth();

               if (!aDefinition.equals(definition) && aDefinition.getDependsOn() instanceof List) {
                  if (aDefinition.getDependsOn().contains(definition)) {
                     final int fLabelWidth = labelWidth;

                     Scheduler.get().scheduleDeferred(new Command() {
                        public void execute() {
                           if (aDefinition.isHidden())
                              return;

                           /* get instance */
                           Collection<ParameterInstanceDto> relevantInstances = parameterService
                                 .getRelevantInstancesFor(parameterDefinitions, parameterInstances, aDefinition);
                           ParameterInstanceDto instance = parameterService.getParameterInstanceFor(parameterInstances,
                                 aDefinition);

                           /* get configurator */
                           final ParameterConfigurator configurator = getConfigurator(aDefinition);
                           if (null == configurator)
                              throw new IllegalArgumentException(
                                    "Should have a configurator for this parameter definition"); //$NON-NLS-1$

                           /* update form only when value (manually) changes, not in first load */
                           List<Object> paramCache = new ArrayList<>();
                           paramCache.add(aDefinition);
                           paramCache.add(relevantInstances);
                           
                           if (parametersChanged.contains(paramCache))
                              configurator.dependeeInstanceChanged(instance, aDefinition, relevantInstances);
                           
                           parametersChanged.add(paramCache);
                           
                           Widget editWidget = configurator.getEditComponentForInstance(instance, aDefinition,
                                 relevantInstances, false, fLabelWidth, getExecuteReportToken(), report);

                           /* find position and remove component */
                           DwHorizontalFlowLayoutContainer colLayout = null;
                           if (componentMap.containsKey(aDefinition)) {
                              Widget[] oldStuff = componentMap.get(aDefinition);
                              colLayout = (DwHorizontalFlowLayoutContainer) oldStuff[0];
                              Widget oldComponent = oldStuff[1];

                              int position = 0;
                              for (position = 0; position < colLayout.getWidgetCount(); position++)
                                 if (oldComponent.equals(colLayout.getWidget(position)))
                                    break;
                              colLayout.remove(oldComponent);

                              colLayout.insert(editWidget, position);

                              /* store in component map */
                              parameterContainer.forceLayout();
                              componentMap.put(aDefinition, new Widget[] { colLayout, editWidget });
                           } else {
                              parameterContainer.add(editWidget);

                              /* store in component map */
                              parameterContainer.forceLayout();
                              componentMap.put(aDefinition, new Widget[] { parameterContainer, editWidget });
                           }
                        }
                     });

                  }
               }
            }
         }
      });
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.EDIT.toImageResource();
   }

   public void makeAwareOfDeselection() {
   }

   @Override
   public List<String> validateView() {
      List<String> errorList = new ArrayList<String>();
      for (ParameterDefinitionDto definition : componentMap.keySet()) {
         ParameterConfigurator configurator = getConfigurator(definition);

         ParameterInstanceDto instance = parameterService.getParameterInstanceFor(parameterInstances, definition);
         List<String> error = configurator.validateParameter(definition, instance, componentMap.get(definition)[1]);
         if (null != error)
            errorList.addAll(error);
      }
      return errorList;
   }

   protected ParameterConfigurator getConfigurator(ParameterDefinitionDto definition) {
      if (!configuratorMap.containsKey(definition))
         configuratorMap.put(definition, parameterService.getConfigurator(definition));
      return configuratorMap.get(definition);
   }

}
