package net.datenwerke.gxtdto.client.baseex.widget.form;

import net.datenwerke.rs.theme.client.field.RsTriggerFieldAppearance;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.ComboBox;

public class DwComboBox<M> extends ComboBox<M> {

	public DwComboBox(ComboBoxCell<M> cell) {
		super(cell);

		adaptListContainer();
	}

	public DwComboBox(ListStore<M> store, LabelProvider<? super M> labelProvider) {
		super(store, labelProvider);

		adaptListContainer();
	}

	public DwComboBox(ListStore<M> store, LabelProvider<? super M> labelProvider, ListView<M, ?> listView) {
		super(store, labelProvider, listView);
		
		adaptListContainer();
	}

	protected void adaptListContainer() {
		VerticalLayoutContainer listContainer = getListContainer(getCell());
		listContainer.setBorders(false);
	}

	/* resort to violator pattern */
	protected native VerticalLayoutContainer getListContainer(ComboBoxCell cbc) /*-{
	  return cbc.@com.sencha.gxt.cell.core.client.form.ComboBoxCell::listContainer;
	}-*/;

	public void plainAppearance() {
		((RsTriggerFieldAppearance)getCell().getAppearance()).setPlainAppearance(true);
		addValueChangeHandler(new ValueChangeHandler<M>() {
			@Override
			public void onValueChange(ValueChangeEvent<M> event) {
				((RsTriggerFieldAppearance)getCell().getAppearance()).updatePlainValue(getElement());
			}
		});
	}

	public void setTriggerIcon(BaseIcon icon){
		((RsTriggerFieldAppearance)getCell().getAppearance()).setTriggerIcon(icon);
	}
	
}
