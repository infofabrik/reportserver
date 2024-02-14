package net.datenwerke.rs.core.client.parameters.helper;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.layout.DwFlowContainer;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwHorizontalFlowLayoutContainer;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.rs.core.client.parameters.ParameterUIService;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.locale.ParametersMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.theme.client.icon.BaseIconComponent;

public class ParameterFieldWrapperForFrontend extends DwHorizontalFlowLayoutContainer {

   @Inject
   private static ParameterUIService parameterUIService;

   private final static ParametersMessages messages = GWT.create(ParametersMessages.class);

   private Component component;

   public ParameterFieldWrapperForFrontend(ParameterDefinitionDto definition, ParameterInstanceDto instance,
         Component component, int labelWidth) {
      this(definition, instance, component, labelWidth, null);
   }

   public ParameterFieldWrapperForFrontend(ParameterDefinitionDto definition, final ParameterInstanceDto instance,
         Component component, int labelWidth, DefaultValueSetter defaultValueSetter) {
      super();
      this.component = component;

      /* prepare menu */
      if (null != defaultValueSetter) {
         final Menu menu = createMenu(defaultValueSetter, instance);

         /* add menu button */
         final BaseIconComponent menuBtn = BaseIcon.GEAR.toComponent();
         menuBtn.addStyleName("rs-paramview-b");
         menuBtn.addClickHandler(event -> {
            if (instance.isStillDefault())
               disable(menu);
            else
               enable(menu);
            menu.show(menuBtn);
         });
         if (labelWidth != 0)
            add(menuBtn);
      }

      /* create name */
      Widget name = getNameComponent(definition);
      if (labelWidth > -1)
         name.setWidth(labelWidth + "px");
      if (labelWidth == 0)
         name.setVisible(false);
      name.addStyleName("rs-paramview-l");
      add(name);

      /* add component */
      add(component);

      /* set properties */
      addStyleName("rs-paramview-w");
   }

   protected void disable(Menu menu) {
      for (int i = 0; i < menu.getWidgetCount(); i++)
         if (menu.getWidget(i) instanceof MenuItem)
            ((MenuItem) menu.getWidget(i)).disable();
   }

   protected void enable(Menu menu) {
      for (int i = 0; i < menu.getWidgetCount(); i++)
         if (menu.getWidget(i) instanceof MenuItem)
            ((MenuItem) menu.getWidget(i)).enable();
   }

   protected Menu createMenu(final DefaultValueSetter defaultValueSetter, ParameterInstanceDto instance) {
      Menu menu = new DwMenu();

      MenuItem defaultItem = new DwMenuItem(messages.setDefaultValues());
      menu.add(defaultItem);
      defaultItem.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            defaultValueSetter.setDefaultValue();
         }
      });

      return menu;
   }

   private Widget getNameComponent(ParameterDefinitionDto definition) {
      DwFlowContainer wrapper = new DwFlowContainer();

      String labeltxt = "";
      labeltxt += definition.isMandatory() ? parameterUIService.getMandatoryPrefix()
            : parameterUIService.getOptionalPrefix();
      labeltxt += definition.getName();
      labeltxt += definition.isMandatory() ? parameterUIService.getMandatorySuffix()
            : parameterUIService.getOptionalSuffix();

      Label text = new Label(labeltxt);
      text.addStyleName("rs-paramview-ln");
      wrapper.add(text);

      if (null != definition.getDescription() && !"".equals(definition.getDescription())) {
         Label description = new Label(definition.getDescription());
         description.addStyleName("rs-paramview-ld");
         wrapper.add(description);
      }

      return wrapper;
   }

   public Component getComponent() {
      return component;
   }
}
