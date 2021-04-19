package net.datenwerke.rs.theme.client.menu;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.IconHelper;
import com.sencha.gxt.theme.neptune.client.base.menu.Css3MenuItemAppearance;

import net.datenwerke.rs.theme.client.icon.CssIconImageResource;

public class RsMenuItemAppearance extends Css3MenuItemAppearance {

	private Css3MenuItemStyle style;

	public interface RsMenuItemTemplate extends MenuItemTemplate {

		@XTemplates.XTemplate(source = "RsMenuItem.html")
		SafeHtml template(MenuItemStyle style);

	}

	public RsMenuItemAppearance() {
		this(GWT.<Css3MenuItemResources>create(Css3MenuItemResources.class),
				GWT.<MenuItemTemplate>create(RsMenuItemTemplate.class));

	}

	public RsMenuItemAppearance(Css3MenuItemResources resources, MenuItemTemplate template) {
		super(resources, template);

		this.style = resources.style();
	}

	public void setIcon(XElement parent, ImageResource icon) {
		XElement anchor = getAnchor(parent);
		XElement oldIcon = parent.selectNode("." + style.menuItemIcon());
		if (oldIcon != null) {
			oldIcon.removeFromParent();
		}
		if (icon != null) {
			Element e = null;
			if(icon instanceof CssIconImageResource)
				e = ((CssIconImageResource)icon).getCssElement();
			else 
				e = IconHelper.getElement(icon);

			e.addClassName(style.menuItemIcon());
			anchor.insertChild(e, 0);
		}
	}

	@Override
	public void onAddSubMenu(XElement parent) {
		parent.addClassName("rs-menuitem-hassub");
	}

	@Override
	public void onRemoveSubMenu(XElement parent) {
		parent.removeClassName("rs-menuitem-hassub");
	}

	public void onActivate(XElement parent) {
		super.onActivate(parent);
		parent.addClassName("rs-menuitem-active");
	}

	public void onDeactivate(XElement parent) {
		super.onDeactivate(parent);
		parent.removeClassName("rs-menuitem-active");
	}
}
