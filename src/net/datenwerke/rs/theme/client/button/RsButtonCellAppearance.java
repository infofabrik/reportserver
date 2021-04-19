package net.datenwerke.rs.theme.client.button;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safecss.shared.SafeStylesBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.sencha.gxt.cell.core.client.ButtonCell;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonScale;
import com.sencha.gxt.cell.core.client.ButtonCell.IconAlign;
import com.sencha.gxt.cell.core.client.SplitButtonCell;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.theme.neptune.client.base.button.Css3ButtonCellAppearance;
import com.sencha.gxt.themebuilder.base.client.config.ButtonDetails;
import com.sencha.gxt.themebuilder.base.client.config.EdgeDetails;

import net.datenwerke.rs.theme.client.icon.CssIconImageResource;

public class RsButtonCellAppearance<M> extends Css3ButtonCellAppearance<M>{

	private Css3ButtonResources resources;
	private Css3ButtonStyle style;

	public RsButtonCellAppearance() {
		super();
		resources = GWT.create(Css3ButtonResources.class);
		style = resources.style();

		StyleInjectorHelper.ensureInjected(resources.style(), true);
	}

	@Override
	public void render(ButtonCell<M> cell, Context context, M value, SafeHtmlBuilder sb) {
		String constantHtml = cell.getHTML();
		boolean hasConstantHtml = constantHtml != null && constantHtml.length() != 0;
		boolean isBoolean = value != null && value instanceof Boolean;
		// is a boolean always a toggle button?
		String text = hasConstantHtml ? cell.getText() : (value != null && !isBoolean)
				? SafeHtmlUtils.htmlEscape(value.toString()) : "";

				ImageResource icon = cell.getIcon();
				IconAlign iconAlign = cell.getIconAlign();

				String arrowClass = "";
				String scaleClass = "";
				String iconClass = "";

				int width = cell.getWidth();
				int height = cell.getHeight();
				boolean hasIcon = cell.getIcon() != null;
				boolean isSplitButton = cell instanceof SplitButtonCell;
				boolean hasMenu = cell.getMenu() != null;

				boolean hasWidth = width != -1;

				if (cell.getMenu() != null) {

					if (cell instanceof SplitButtonCell) {
						switch (cell.getArrowAlign()) {
						case RIGHT:
							arrowClass = ""; //CHANGE style.split();
							break;
						case BOTTOM:
							arrowClass = ""; //CHANGE style.splitBottom();
							break;
						}

					} else {
						switch (cell.getArrowAlign()) {
						case RIGHT:
							arrowClass = ""; //CHANGE style.arrow();
							break;
						case BOTTOM:
							arrowClass = ""; //CHANGE style.arrowBottom();
							break;
						}
					}

				}

				ButtonScale scale = cell.getScale();

				switch (scale) {
				case SMALL:
					scaleClass = style.small();
					break;

				case MEDIUM:
					scaleClass = style.medium();
					break;

				case LARGE:
					scaleClass = style.large();
					break;
				}

				if (icon != null) {
					switch (iconAlign) {
					case TOP:
						iconClass = style.iconTop();
						break;
					case BOTTOM:
						iconClass = style.iconBottom();
						break;
					case LEFT:
						iconClass = style.iconLeft();
						break;
					case RIGHT:
						iconClass = style.iconRight();
						break;
					}
				}

				String buttonClass = style.button() + (scale == ButtonScale.LARGE ?  " rs-btn-large" : "") + (iconAlign == IconAlign.TOP ? " rs-btn-top" : "");
				boolean hasText = text != null && !text.equals("");
				if (!hasText) {
					buttonClass += " " + style.noText();
				}

				// toggle button
				if (value == Boolean.TRUE) {
					buttonClass += " " + style.pressed() + " rs-btn-pressed";
				}

				String innerClass = style.buttonInner() + " " + iconClass;

				innerClass += " " + arrowClass;
				innerClass += " " + scaleClass;

				SafeHtmlBuilder builder = new SafeHtmlBuilder();

				SafeStylesBuilder ss = new SafeStylesBuilder();

				if (height != -1) {
					ButtonDetails bd = resources.theme().button();
					EdgeDetails padding = bd.padding();
					EdgeDetails paddingInner = bd.radiusMinusBorderWidth();
					int ah = height;
					ah -= padding.top();
					ah -= padding.bottom();
					ah -= paddingInner.top();
					ah -= paddingInner.bottom();

					ss.appendTrustedString("line-height: " + ah + "px;");
				}

				builder.appendHtmlConstant("<div class='" + buttonClass + "'>");

				// get iconbuilder ready
				SafeHtmlBuilder iconBuilder = new SafeHtmlBuilder();
				if (icon != null) {
					int iconWidth = icon != null ? icon.getWidth() : 0;
					int iconHeight = icon != null ? icon.getHeight() : 0;
					String styles = "width: " + iconWidth + "px; height: " + iconHeight + "px;";

					iconBuilder.appendHtmlConstant("<div class='" + iconClass + "' style='" + styles + "'>");
					// CHANGE
					if(icon instanceof CssIconImageResource)
						iconBuilder.append(scale==ButtonScale.LARGE ? ((CssIconImageResource)icon).getCssIcon(1) : ((CssIconImageResource)icon).getCssIcon());
					else
						iconBuilder.append(AbstractImagePrototype.create(icon).getSafeHtml());
					// CHANGE END
					iconBuilder.appendHtmlConstant("</div>");
				}

				// for left / right aligned icons with a fixed button width we render the icon outside of the inner div
				if (hasWidth && hasIcon && iconAlign == IconAlign.LEFT) {
					builder.append(iconBuilder.toSafeHtml());
				}

				if (hasWidth && hasIcon && (iconAlign == IconAlign.LEFT)) {
					int tw = width - (resources.theme().button().borderRadius() * 2);
					if (isSplitButton && cell.getArrowAlign() == ButtonArrowAlign.RIGHT) {
						tw -= resources.split().getWidth() + 10;
					}

					if (!isSplitButton && iconAlign == IconAlign.LEFT && hasMenu && cell.getArrowAlign() == ButtonArrowAlign.RIGHT) {
						tw -= resources.arrow().getWidth() + 10;
					}

					if (hasIcon && iconAlign == IconAlign.LEFT) {
						tw -= icon.getWidth();
					} 

					ss.appendTrustedString("width: " + tw + "px;");
				}

				builder.appendHtmlConstant("<div class='" + innerClass + "' style='" + ss.toSafeStyles().asString() + "'>");

				//CHANGE
				if(hasMenu || isSplitButton)
					builder.appendHtmlConstant("<span class=\"rs-btn-menu\"><span class=\"rs-btn-menu-sep\"></span><i class=\"fa fa-chevron-down\"></i></span>");
				//CHANGE END

				
				if (icon != null) {
					if ((!hasWidth && iconAlign == IconAlign.LEFT) || iconAlign == IconAlign.TOP) {
						builder.append(iconBuilder.toSafeHtml());
					}
					if(iconAlign == IconAlign.TOP)
						builder.appendHtmlConstant("<span class=\"rs-btn-txt\">");
					builder.appendHtmlConstant(text);
					if(iconAlign == IconAlign.TOP)
						builder.appendHtmlConstant("</span>");
					if (iconAlign == IconAlign.RIGHT || iconAlign == IconAlign.BOTTOM) {
						builder.append(iconBuilder.toSafeHtml());
					}
				} else {
					builder.appendHtmlConstant(text);
				}
				
				builder.appendHtmlConstant("</div></div>");
				sb.append(builder.toSafeHtml());
	}
	
	  @Override
	  public void onToggle(XElement parent, boolean pressed) {
	    parent.getFirstChildElement().<XElement> cast().setClassName(style.pressed(), pressed);
	    parent.getFirstChildElement().<XElement> cast().setClassName("rs-btn-pressed", pressed);
	  }
}
