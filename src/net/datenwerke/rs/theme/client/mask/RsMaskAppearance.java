package net.datenwerke.rs.theme.client.mask;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.dom.Mask.MessageTemplates;
import com.sencha.gxt.core.client.dom.XDOM;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.theme.neptune.client.base.mask.Css3MaskAppearance;

public class RsMaskAppearance extends Css3MaskAppearance {

	private final Css3MaskResources resources = GWT.create(Css3MaskResources.class);
	private final MessageTemplates template = GWT.create(MessageTemplates.class);

	public RsMaskAppearance() {
		super();
		StyleInjectorHelper.ensureInjected(resources.styles(), true);
	}

	@Override
	public void mask(XElement parent, String message) {
		XElement mask = XElement.createElement("div");
		mask.setClassName(resources.styles().mask());
		mask.addClassName("rs-mask");
		parent.appendChild(mask);

		XElement box = null;
		if (message != null) {
			box = XDOM.create(template.template(resources.styles(), SafeHtmlUtils.htmlEscape(message))).cast();
			box.addClassName("rs-mask-box");
			parent.appendChild(box);
		}

		if (GXT.isIE() && "auto".equals(parent.getStyle().getHeight())) {
			mask.setSize(parent.getOffsetWidth(), parent.getOffsetHeight());
		}

		if (box != null) {
			box.updateZIndex(0);
			box.center(parent);
		}

	}
}
