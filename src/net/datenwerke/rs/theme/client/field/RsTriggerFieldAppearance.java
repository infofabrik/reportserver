package net.datenwerke.rs.theme.client.field;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safecss.shared.SafeStylesBuilder;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.cell.core.client.form.FieldCell.FieldAppearanceOptions;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.theme.neptune.client.base.field.Css3TriggerFieldAppearance;
import com.sencha.gxt.themebuilder.base.client.config.FieldDetails;

import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class RsTriggerFieldAppearance extends Css3TriggerFieldAppearance {

	protected final Css3TriggerFieldResources resources;
	protected final Css3TriggerFieldStyle style;
	protected boolean plainApperance;
	protected BaseIcon triggerIcon = BaseIcon.CHEVRON_DOWN;
	private boolean plainTriggerApperance;

	public RsTriggerFieldAppearance() {
		this(GWT.<Css3TriggerFieldResources>create(Css3TriggerFieldResources.class));
	}

	public RsTriggerFieldAppearance(Css3TriggerFieldResources resources) {
		super(resources);

		this.resources = resources;
		this.style = resources.style();
	}

	public void setPlainAppearance(boolean b) {
		plainApperance = true;
	}
	
	public void setTriggerPlainAppearance(boolean b) {
		plainTriggerApperance = true;
	}


	@Override
	public boolean triggerIsOrHasChild(XElement parent, Element target) {
		if (! parent.isOrHasChild(target)) 
			return false;

		XElement t = parent.selectNode(".rs-field-tf-t");

		return null != t && t.isOrHasChild(target);
	}

	@Override
	public void render(SafeHtmlBuilder sb, String value, FieldAppearanceOptions options) {
		int width = options.getWidth();
		boolean hideTrigger = options.isHideTrigger();

		if (width == -1) {
			width = 150;
		}

		SafeStylesBuilder inputStylesBuilder = new SafeStylesBuilder();
		if(!plainApperance)
			inputStylesBuilder.appendTrustedString("width:100%;");

		// outer div needed for widgets like comboBox that need the full width to set for listview width
		String className = plainApperance ? "rs-trigger-plain" : "rs-trigger";
		sb.appendHtmlConstant("<div class=\"" + className + "\" " + (plainApperance ? ">" : "style='width:" + width + "px;'>"));

		if (hideTrigger) {
			sb.appendHtmlConstant("<div class='" + style.wrap() + "'>");
			renderInput(sb, value, inputStylesBuilder.toSafeStyles(), options);
		} else {
			FieldDetails fieldDetails = getResources().theme().field();
			int rightPadding = fieldDetails.padding().right();
			sb.appendHtmlConstant("<div class='" + style.wrap() + "' style='padding-right:"
					+ (getResources().triggerArrow().getWidth() + rightPadding) + "px;'>");
			renderInput(sb, value, inputStylesBuilder.toSafeStyles(), options);

			int fieldHeight = fieldDetails.height();
			int right = fieldDetails.borderWidth() + 1;

			StringBuilder triggerStyleSB = new StringBuilder();
			// default height to the height of the input element for both
			// desktop and touch
			triggerStyleSB.append("height:").append(fieldHeight).append("px;");
			// default right position for both desktop and touch
			triggerStyleSB.append("right:").append(right).append("px;");
			/*
			 * The height/width of the trigger is generated based off the
			 * dimensions of the image, which can negatively impact user
			 * experience on touch devices. For touch devices, we're going to
			 * use the height of the input element to create a large square
			 * around the trigger.
			 */
			if (GXT.isTouch()) {
				// default width to height of input element to give touch users
				// some extra width to work with
				triggerStyleSB.append("width:").append(fieldHeight).append("px;");
				// now that we've widened the trigger field, need to apply a
				// margin so that it's positioned correctly
				int deltaWidth = fieldHeight - getResources().triggerArrow().getWidth();
				int rightMargin = -1 * (deltaWidth / 2);
				triggerStyleSB.append("margin-right:").append(rightMargin).append("px;");
			}
			SafeStyles triggerStyle = SafeStylesUtils.fromTrustedString(triggerStyleSB.toString());
			sb.appendHtmlConstant("<div class='rs-field-tf-t " + getStyle().trigger()
					+ (plainTriggerApperance ? " rs-field-tf-pt" : "") + "' style='" + triggerStyle.asString() + "'>");
			sb.append(triggerIcon.toSafeHtml());
			sb.appendHtmlConstant("</div>");
		}

		sb.appendHtmlConstant("</div></div>");
	}

	protected void renderInput(SafeHtmlBuilder shb, String value, SafeStyles inputStyles, FieldAppearanceOptions options) {
		StringBuilder sb = new StringBuilder();
		sb.append("<input ");

		if (options.isDisabled()) {
			sb.append("disabled=true");
		}

		if (options.getName() != null) {
			sb.append("name='").append(SafeHtmlUtils.htmlEscape(options.getName())).append("' ");
		}

		if (options.isReadonly() || !options.isEditable()) {
			sb.append("readonly ");
		}

		if (inputStyles != null) {
			sb.append("style='").append(inputStyles.asString()).append("' ");
		}

		sb.append("class='").append(getStyle().field()).append(" ").append(getStyle().text());

		String placeholder = options.getEmptyText() != null ? " placeholder='" + SafeHtmlUtils.htmlEscape(options.getEmptyText()) + "' " : "";

		if ("".equals(value) && options.getEmptyText() != null) {
			sb.append(" ").append(getStyle().empty());
			if (GXT.isIE8() || GXT.isIE9()) {
				value = options.getEmptyText();
			}
		}

		if (!options.isEditable()) {
			sb.append(" ").append(getStyle().noedit());
		}

		sb.append("' ");
		sb.append(placeholder);

		sb.append("type='text' value='").append(SafeHtmlUtils.htmlEscape(value)).append("' ");

		sb.append("/>");

		if(plainApperance){
			sb.append("<span class=\"field-tf-t-plain\">").append(SafeHtmlUtils.htmlEscape(value)).append("</span>");
		}

		shb.append(SafeHtmlUtils.fromTrustedString(sb.toString()));
	}


	public void updatePlainValue(XElement parent) {
		InputElement el = getInputElement(parent).cast();
		String value = el.getValue();
		parent.selectNode(".field-tf-t-plain").setInnerHTML(SafeHtmlUtils.htmlEscape(value));
	}

	public void setTriggerIcon(BaseIcon icon) {
		this.triggerIcon = icon;
	}

	@Override
	public void onFocus(Element parent, boolean focus) {
		parent.<XElement>cast().setClassName(getResources().style().focus() + " .rs-focus", focus);
	}


}
