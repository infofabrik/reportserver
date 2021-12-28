package net.datenwerke.gxtdto.client.baseex.widget.form;

import com.sencha.gxt.cell.core.client.form.NumberInputCell;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;

import net.datenwerke.gxtdto.client.theme.CssClassConstant;

public class DwNumberField<N extends Number & Comparable<N>> extends NumberField<N> {

	@CssClassConstant
	public static final String CSS_NAME = "rs-number-field";
	
	public DwNumberField(NumberInputCell<N> cell, NumberPropertyEditor<N> editor) {
		super(cell, editor);
		initCss();
	}

	public DwNumberField(NumberPropertyEditor<N> editor) {
		super(editor);
		initCss();
	}

	private void initCss() {
		getElement().addClassName(CSS_NAME);
	}

	

}
