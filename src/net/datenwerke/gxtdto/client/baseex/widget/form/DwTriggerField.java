package net.datenwerke.gxtdto.client.baseex.widget.form;

import com.sencha.gxt.cell.core.client.form.TriggerFieldCell;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.TriggerField;

import net.datenwerke.rs.theme.client.field.RsTriggerFieldAppearance;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DwTriggerField<D> extends TriggerField<D> {

   public DwTriggerField(TriggerFieldCell<D> cell) {
      super(cell);
   }

   public DwTriggerField(TriggerFieldCell<D> cell, PropertyEditor<D> propertyEditor) {
      super(cell, propertyEditor);
   }

   public void setTriggerIcon(BaseIcon icon) {
      ((RsTriggerFieldAppearance) getCell().getAppearance()).setTriggerIcon(icon);
   }

}
