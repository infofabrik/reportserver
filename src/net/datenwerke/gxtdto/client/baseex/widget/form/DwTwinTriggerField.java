package net.datenwerke.gxtdto.client.baseex.widget.form;

import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.TwinTriggerField;

import net.datenwerke.rs.theme.client.field.RsTwinTriggerFieldAppearance;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DwTwinTriggerField<D> extends TwinTriggerField<D> {

   public DwTwinTriggerField(DwTwinTriggerFieldCell<D> cell) {
      this(cell, (PropertyEditor<D>) PropertyEditor.DEFAULT);
   }

   public DwTwinTriggerField(DwTwinTriggerFieldCell<D> cell, PropertyEditor<D> propertyEditor) {
      super(cell, propertyEditor);
   }

   public void setHideTwinTrigger(boolean hide) {
      ((RsTwinTriggerFieldAppearance) getCell().getAppearance()).setHideTwinTrigger(hide);
   }

   public void setTriggerIcon(BaseIcon icon) {
      ((RsTwinTriggerFieldAppearance) getCell().getAppearance()).setTriggerIcon(icon);
   }

   public void setTwinTriggerIcon(BaseIcon icon) {
      ((RsTwinTriggerFieldAppearance) getCell().getAppearance()).setTwinTriggerIcon(icon);
   }

}
