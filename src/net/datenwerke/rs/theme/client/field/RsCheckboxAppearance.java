package net.datenwerke.rs.theme.client.field;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.cell.core.client.form.CheckBoxCell;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.dom.XDOM;
import com.sencha.gxt.theme.neptune.client.base.field.Css3CheckBoxAppearance;

public class RsCheckboxAppearance extends Css3CheckBoxAppearance {

	@Override
	public void render(SafeHtmlBuilder sb, Boolean value, CheckBoxCell.CheckBoxCellOptions options) {
		String checkBoxId = XDOM.getUniqueId();

		String nameParam = options.getName() != null ? " name='" + options.getName() + "' " : "";
		String disabledParam = options.isDisabled() ? " disabled=true" : "";
		String readOnlyParam = options.isReadonly() ? " readonly" : "";
		String idParam = " id=" + checkBoxId;
		String typeParam = " type=" + type;
		String checkedParam = value ? " checked" : "";

		sb.appendHtmlConstant("<div class=\"" + style.wrap() + " rs-field-cb\">");
		sb.appendHtmlConstant(
				"<input " + typeParam + nameParam + disabledParam + readOnlyParam + idParam + checkedParam + " />");
		// on IE11, clicking the checkbox label fires an event for both the
		// label and the input.
		final String labelHtml;
		if (GXT.isIE11()) {
			labelHtml = "<label class=" + style.checkBoxLabel() + ">";
		} else {
			labelHtml = "<label for=" + checkBoxId + " class=" + style.checkBoxLabel() + ">";
		}
		sb.appendHtmlConstant(labelHtml);
		if (options.getBoxLabel() != null) {
			sb.append(options.getBoxLabel());
		}
		sb.appendHtmlConstant("</label></div>");
	}

}
