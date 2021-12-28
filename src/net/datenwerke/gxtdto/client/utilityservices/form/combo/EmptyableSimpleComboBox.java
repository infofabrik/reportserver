package net.datenwerke.gxtdto.client.utilityservices.form.combo;

import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;

import net.datenwerke.gxtdto.client.forms.binding.HasValueFieldBinding;

public class EmptyableSimpleComboBox<T> extends SimpleComboBox<Object> {

	public EmptyableSimpleComboBox() {
		this(new StringLabelProvider<Object>());
	}
	
	public EmptyableSimpleComboBox(LabelProvider<? super Object> labelProvider) {
		super(labelProvider);
		add(new EmptyOption());
		setAllowBlank(true);
		setForceSelection(true);
		
	}

	public <C> void createValueBinding(C obj, ValueProvider<C, T> vp) {
		new HasValueFieldBinding(this, obj, vp, new ConverterWithEmpty<T>());
	}

	public void setSorted(SortDir dir){
		getStore().addSortInfo(new StoreSortInfo<Object>(new ComparatorWithEmpty(), dir));
	}
	
	public void addAll(List<T> values) {
		getStore().addAll(values);
	}
}
