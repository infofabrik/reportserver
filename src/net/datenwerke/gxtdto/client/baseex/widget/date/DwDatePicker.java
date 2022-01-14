package net.datenwerke.gxtdto.client.baseex.widget.date;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.widget.core.client.DatePicker;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;

public class DwDatePicker extends DatePicker {

   @Override
   public XElement getElement() {
      /* very mean hack */
      if (!(todayBtn instanceof DwTextButton)) {
         todayBtn = new DwTextButton(getMessages().todayText());
         todayBtn.addSelectHandler(new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
               setValue(new DateWrapper().asDate());
            }
         });

         todayBtn.setToolTip(
               getMessages().todayTip(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(new Date())));
      }
      if (!(monthPickerOkButton instanceof DwTextButton)) {
         final TextButton oldBtn = monthPickerOkButton;
         monthPickerOkButton = new DwTextButton(getMessages().okText());
         monthPickerOkButton.addSelectHandler(new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
               oldBtn.fireEvent(new SelectEvent());
            }
         });

         monthPickerCancelButton = new DwTextButton(getMessages().cancelText());
         monthPickerCancelButton.addSelectHandler(new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
               focus();
               hideMonthPicker();
            }
         });
      }

      return super.getElement();
   }
}
