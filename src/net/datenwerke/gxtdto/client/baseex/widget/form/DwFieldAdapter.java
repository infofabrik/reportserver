package net.datenwerke.gxtdto.client.baseex.widget.form;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.BlurEvent;
import com.sencha.gxt.widget.core.client.event.BlurEvent.BlurHandler;
import com.sencha.gxt.widget.core.client.form.AdapterField;
import com.sencha.gxt.widget.core.client.form.Field;

public class DwFieldAdapter<D> extends AdapterField<D> {

   protected Field<D> field;

   public DwFieldAdapter(Widget widget, Field<D> field) {
      super(widget);
      this.field = field;

      addListeners();
   }

   protected void addListeners() {
      field.addValueChangeHandler(new ValueChangeHandler<D>() {
         @Override
         public void onValueChange(ValueChangeEvent<D> event) {
            DwFieldAdapter.this.fireEvent(event);
         }
      });

      field.addBlurHandler(new BlurHandler() {

         @Override
         public void onBlur(BlurEvent event) {
            DwFieldAdapter.this.fireEvent(new BlurEvent());
         }
      });
   }

   @Override
   public D getValue() {
      return field.getValue();
   }

   public void setValue(final Object value) {
      field.setValue((D) value, true);
   }

   @Override
   public void setSize(String width, String height) {
      getWidget().setSize(width, height);
   }
}
