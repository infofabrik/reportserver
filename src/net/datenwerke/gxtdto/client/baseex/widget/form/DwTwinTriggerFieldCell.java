package net.datenwerke.gxtdto.client.baseex.widget.form;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.sencha.gxt.cell.core.client.form.TwinTriggerFieldCell;
import com.sencha.gxt.core.client.dom.XElement;

public class DwTwinTriggerFieldCell<T> extends TwinTriggerFieldCell<T> {

	@Override
	protected void onClick(com.google.gwt.cell.client.Cell.Context context,
			XElement parent, NativeEvent event, T value, ValueUpdater<T> updater) {
		Element target = event.getEventTarget().cast();

		if (!isReadOnly() && getAppearance().twinTriggerIsOrHasChild(parent, target)) {
			onTwinTriggerClick(context, parent, event, value, updater);
			return; // no trigger click if twin was clicked!
		}

		if (!isReadOnly() && getAppearance().triggerIsOrHasChild(parent, target)) {
			onTriggerClick(context, parent, event, value, updater);
		}
	}
}
