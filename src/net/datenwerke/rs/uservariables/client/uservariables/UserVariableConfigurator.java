package net.datenwerke.rs.uservariables.client.uservariables;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Window;

import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto;

public interface UserVariableConfigurator<D extends UserVariableDefinitionDto, I extends UserVariableInstanceDto> {

   public String getName();

   public ImageResource getIcon();

   public UserVariableDefinitionDto createDTOInstance();

   public Widget getEditComponent(D definition);

   public Widget getEditComponent(I instance, D definition);

   public Object getDisplayValue(I instance, D definition);

   public void configureEditWindow(D definition, Window window);

   public void configureEditWindow(I instance, D definition, Window window);
}
