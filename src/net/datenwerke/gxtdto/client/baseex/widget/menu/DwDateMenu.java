package net.datenwerke.gxtdto.client.baseex.widget.menu;

import java.util.Date;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.widget.core.client.DatePicker;
import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.gxtdto.client.baseex.widget.date.DwDatePicker;

public class DwDateMenu extends Menu implements HasValueChangeHandlers<Date>{

	private DwDatePicker picker;

	public DwDateMenu() {
		picker = new DwDatePicker();
		picker.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				onPickerSelect(event);
			}
		});

		add(picker);
		getAppearance().applyDateMenu(getElement());
		plain = true;
		showSeparator = false;
		setEnableScrolling(false);
	}
	

	  @Override
	  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> handler) {
	    return addHandler(handler, ValueChangeEvent.getType());
	  }

	  @Override
	  public void focus() {
	    super.focus();
	    picker.getElement().focus();
	  }

	  /**
	   * Returns the selected date.
	   * 
	   * @return the date
	   */
	  public Date getDate() {
	    return picker.getValue();
	  }

	  /**
	   * Returns the date picker.
	   * 
	   * @return the date picker
	   */
	  public DatePicker getDatePicker() {
	    return picker;
	  }

	  /**
	   * Sets the menu's date.
	   * 
	   * @param date the date
	   */
	  public void setDate(Date date) {
	    picker.setValue(date);
	  }

	  protected void onPickerSelect(ValueChangeEvent<Date> event) {
	    fireEvent(event);
	  }
}
