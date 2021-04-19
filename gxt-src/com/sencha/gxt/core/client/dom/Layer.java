/**
 * Sencha GXT 4.0.2 - Sencha for GWT
 * Copyright (c) 2006-2016, Sencha Inc.
 *
 * licensing@sencha.com
 * http://www.sencha.com/products/gxt/license/
 *
 * ================================================================================
 * Commercial License
 * ================================================================================
 * This version of Sencha GXT is licensed commercially and is the appropriate
 * option for the vast majority of use cases.
 *
 * Please see the Sencha GXT Licensing page at:
 * http://www.sencha.com/products/gxt/license/
 *
 * For clarification or additional options, please contact:
 * licensing@sencha.com
 * ================================================================================
 *
 *
 *
 *
 *
 *
 *
 *
 * ================================================================================
 * Disclaimer
 * ================================================================================
 * THIS SOFTWARE IS DISTRIBUTED "AS-IS" WITHOUT ANY WARRANTIES, CONDITIONS AND
 * REPRESENTATIONS WHETHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES AND CONDITIONS OF MERCHANTABILITY, MERCHANTABLE QUALITY,
 * FITNESS FOR A PARTICULAR PURPOSE, DURABILITY, NON-INFRINGEMENT, PERFORMANCE AND
 * THOSE ARISING BY STATUTE OR FROM CUSTOM OR USAGE OF TRADE OR COURSE OF DEALING.
 * ================================================================================
 */
package com.sencha.gxt.core.client.dom;

import java.util.Stack;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.resources.CommonStyles;
import com.sencha.gxt.core.client.util.Rectangle;

/**
 * Wraps a <code>XElement</code> and provides support for shadows and shimming.
 */
public class Layer {

  public interface LayerAppearance {

    int getShadowOffset();

    void renderShadow(SafeHtmlBuilder sb);

    String shimClass();

  }

  public static class LayerBaseAppearance implements LayerAppearance {

    private final LayerStyle style;
    private final LayerTemplates templates;

    public LayerBaseAppearance() {
      this(GWT.<LayerResources> create(LayerResources.class), GWT.<LayerTemplates> create(LayerTemplates.class));
    }

    public LayerBaseAppearance(LayerResources resources, LayerTemplates templates) {
      this.templates = templates;
      style = resources.style();
      style.ensureInjected();
    }

    @Override
    public int getShadowOffset() {
      return style.shadowOffset();
    }

    @Override
    public void renderShadow(SafeHtmlBuilder sb) {
      sb.append(templates.shadow(style, CommonStyles.get().ignore()));
    }

    @Override
    public String shimClass() {
      return style.shim();
    }

  }

  public static class LayerBaseAppearanceIe implements LayerAppearance {

    private LayerStyleIe style;
    private LayerTemplatesIe templates;

    public LayerBaseAppearanceIe() {
      this(GWT.<LayerResourcesIe> create(LayerResourcesIe.class), GWT.<LayerTemplatesIe> create(LayerTemplatesIe.class));
    }

    public LayerBaseAppearanceIe(LayerResourcesIe resources, LayerTemplatesIe templates) {
      this.templates = templates;
      style = resources.style();
      style.ensureInjected();
    }

    @Override
    public int getShadowOffset() {
      return style.shadowOffset();
    }

    @Override
    public void renderShadow(SafeHtmlBuilder sb) {
      sb.append(templates.shadow(style, CommonStyles.get().ignore()));
    }

    @Override
    public String shimClass() {
      return style.shim();
    }

  }

  public interface LayerResources extends ClientBundle {

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource bottomCenter();

    ImageResource bottomLeft();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource bottomRight();

    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource middleCenter();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    ImageResource middleLeft();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    ImageResource middleRight();

    @Source("Layer.gss")
    LayerStyle style();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource topCenter();

    ImageResource topLeft();

    ImageResource topRight();

  }

  public interface LayerResourcesIe extends ClientBundle {

    @Source("LayerIe.gss")
    LayerStyleIe style();

  }

  public interface LayerStyle extends CssResource {

    String bottom();

    String bottomCenter();

    String bottomLeft();

    String bottomRight();

    String middle();

    String middleCenter();

    String middleLeft();

    String middleRight();

    String shadow();

    int shadowOffset();

    String shim();

    String top();

    String topCenter();

    String topLeft();

    String topRight();

  }

  public interface LayerStyleIe extends CssResource {

    String shadow();

    int shadowOffset();

    String shim();

  }

  public interface LayerTemplates extends XTemplates {

    @XTemplate(source = "Layer.html")
    SafeHtml shadow(LayerStyle style, String ignoreClass);

  }

  public interface LayerTemplatesIe extends XTemplates {

    @XTemplate("<div class=\"{style.shadow} {ignoreClass}\"></div>")
    SafeHtml shadow(LayerStyleIe style, String ignoreClass);

  }

  /**
   * Shadow position enumeration.
   */
  public enum ShadowPosition {
    DROP, FRAME, SIDES
  }

  public interface ShimTemplates extends XTemplates {

    @XTemplate("<iframe frameborder=\"no\" frameBorder=\"no\" class=\"{shimClass} {ignoreClass}\" tabIndex=\"-1\" style=\"visibility:hidden;display:none\" role=\"presentation\"></iframe>")
    SafeHtml template(String shimClass, String ignoreClass);

  }
  private static Stack<XElement> shadows = new Stack<XElement>();

  private static Stack<XElement> shims = new Stack<XElement>();

  private final LayerAppearance appearance;
  private XElement elem;
  private XElement shadow;
  private Rectangle shadowAdjusts;
  private boolean shadowEnabled;
  private ShadowPosition shadowPosition;
  private XElement shim;

  private boolean shimEnabled;
  private boolean isLegacyIELayer = GXT.isIE8() || GXT.isIE9();

  public Layer(XElement elem) {
    this(elem, GWT.<LayerAppearance> create(LayerAppearance.class));
  }

  public Layer(XElement elem, LayerAppearance appearance) {
    this.elem = elem;
    this.appearance = appearance;
    bind(elem);
    setShadowPosition(ShadowPosition.SIDES);
  }

  /**
   * Disables the shadow.
   */
  public void disableShadow() {
    shadowEnabled = false;
    hideShadow();
  }

  /**
   * Disables the shim.
   */
  public void disableShim() {
    shimEnabled = false;
    hideShim();
  }

  /**
   * Disables the shim and the shadow.
   */
  public void disableUnders() {
    disableShadow();
    disableShim();
  }

  /**
   * Enables the shadow.
   */
  public void enableShadow() {
    shadowEnabled = true;
  }

  /**
   * Enables the shim.
   */
  public void enableShim() {
    shimEnabled = true;
  }

  public LayerAppearance getAppearance() {
    return appearance;
  }

  public XElement getElement() {
    return elem;
  }

  /**
   * Returns the layer's shadow.
   *
   * @return the shadow or null
   */
  public XElement getShadow() {
    XElement pn = elem.getParentElement().<XElement> cast();
    if (pn == null || !shadowEnabled) {
      hideShadow();
      return null;
    }
    if (shadow != null) {
      return shadow;
    }
    shadow = shadows.size() > 0 ? shadows.pop() : null;
    if (shadow == null) {
      shadow = createShadow();
    }
    pn.insertBefore(shadow, elem);
    shadow.setZIndex(elem.getZIndex() - 1);
    return shadow;
  }

  /**
   * Returns the shadow offset.
   *
   * @return the shadow offset
   */
  public int getShadowOffset() {
    return appearance.getShadowOffset();
  }

  /**
   * Returns the shadow position.
   *
   * @return the shadow position
   */
  public ShadowPosition getShadowPosition() {
    return shadowPosition;
  }

  /**
   * Returns the layer's shim.
   *
   * @return the shim
   */
  public XElement getShim() {
    XElement pn = elem.getParentElement().<XElement> cast();
    if (pn == null || !shimEnabled) {
      hideShim();
      return null;
    }
    if (shim != null) {
      return shim;
    }
    shim = shims.size() > 0 ? shims.pop() : null;
    if (shim == null) {
      shim = createShim();
    }
    pn.insertBefore(shim, elem);
    shim.getStyle().setZIndex(elem.getZIndex() - 2);
    
    // IE10 shows the menu behind pdf, this fixes that
    if (GXT.isIE11() || GXT.isIE10() || GXT.isIE9() || GXT.isIE8()) {
      shim.setVisibility(true);
      shim.setZIndex(1);
    }
    
    return shim;
  }

  /**
   * Hides the layer's shadow.
   */
  public void hideShadow() {
    if (shadow != null) {
      shadow.hide();
      shadow.removeFromParent();
      shadows.push(shadow);
      shadow = null;
    }
  }

  /**
   * Hides the shim.
   */
  public void hideShim() {
    if (shim != null) {
      shim.hide();
      shim.removeFromParent();
      shims.push(shim);
      shim = null;
    }
  }

  /**
   * Hides the shim and the shadow.
   */
  public void hideUnders() {
    hideShadow();
    hideShim();
  }

  public boolean isShadow() {
    return shadowEnabled;
  }

  public boolean isShim() {
    return shimEnabled;
  }

  /**
   * Sets the shadow position (defaults to SIDES).
   *
   * @param shadowPosition the position
   */
  public void setShadowPosition(ShadowPosition shadowPosition) {
    this.shadowPosition = shadowPosition;
    int shadowOffset = appearance.getShadowOffset();
    int radius = shadowOffset / 2;
    shadowAdjusts = new Rectangle();
    switch (shadowPosition) {
      case SIDES:
        shadowAdjusts.setWidth(shadowOffset * 2);
        shadowAdjusts.setX(-shadowOffset);
        shadowAdjusts.setY(shadowOffset - 1);
        if (isLegacyIELayer) {
          shadowAdjusts.setX(shadowAdjusts.getX() - (shadowOffset - radius));
          shadowAdjusts.setY(shadowAdjusts.getY() - (shadowOffset + radius));
          shadowAdjusts.setX(shadowAdjusts.getX() + 1);
          shadowAdjusts.setWidth(shadowAdjusts.getWidth() - (shadowOffset - radius) * 2);
          shadowAdjusts.setWidth(shadowAdjusts.getWidth() - (radius + 1));
          shadowAdjusts.setHeight(shadowAdjusts.getHeight() - 1);
        }
        break;
      case FRAME:
        shadowAdjusts.setWidth(shadowOffset * 2);
        shadowAdjusts.setHeight(shadowOffset * 2);
        shadowAdjusts.setX(-shadowOffset);
        shadowAdjusts.setY(-shadowOffset);
        shadowAdjusts.setY(shadowAdjusts.getY() + 1);

        shadowAdjusts.setHeight(shadowAdjusts.getHeight() - 2);

        if (isLegacyIELayer) {
          shadowAdjusts.setX(shadowAdjusts.getX() - (shadowOffset - radius));
          shadowAdjusts.setY(shadowAdjusts.getY() - (shadowOffset - radius));
          shadowAdjusts.setWidth(shadowAdjusts.getWidth() - (shadowOffset + radius) + 1);
          shadowAdjusts.setHeight(shadowAdjusts.getHeight() - (shadowOffset + radius) + 3);
        }
        break;
      default:
        shadowAdjusts.setWidth(0);
        shadowAdjusts.setY(shadowOffset);
        shadowAdjusts.setX(shadowAdjusts.getY());
        shadowAdjusts.setY(shadowAdjusts.getY() - 1);

        if (isLegacyIELayer) {
          shadowAdjusts.setX(shadowAdjusts.getX() - (shadowOffset + radius));
          shadowAdjusts.setY(shadowAdjusts.getX());
          shadowAdjusts.setWidth(shadowAdjusts.getWidth() - radius);
          shadowAdjusts.setHeight(shadowAdjusts.getWidth());
          shadowAdjusts.setY(shadowAdjusts.getY() + 1);
        }
        break;
    }
  }

  /**
   * Syncs the layer with its element.
   *
   * @param show true to show the layer
   */
  public void sync(boolean show) {
    if (elem.isVisible() && (shadowEnabled || shimEnabled)) {
      int w = elem.getOffsetWidth();
      int h = elem.getOffsetHeight();
      int l = elem.getLeft();
      int t = elem.getTop();

      if (shadowEnabled && elem.getParentElement() != null) {
        if (shadow == null) {
          shadow = getShadow();
        }
        if (show) {
          shadow.show();
          shadow.getStyle().setDisplay(Display.BLOCK);
        }
        shadow.setLeft(l + shadowAdjusts.getX());
        shadow.setTop(t + shadowAdjusts.getY());

        int sw = w + shadowAdjusts.getWidth();
        int sh = h + shadowAdjusts.getHeight();
        if (shadow.getOffsetWidth() != sw || shadow.getOffsetHeight() != sh) {
          shadow.setSize(sw, sh);
          if (!isLegacyIELayer) {
            int width = Math.max(0, sw - 12);
            XElement.as(shadow.getChildNodes().getItem(0).getChildNodes().getItem(1)).setWidth(width);
            XElement.as(shadow.getChildNodes().getItem(1).getChildNodes().getItem(1)).setWidth(width);
            XElement.as(shadow.getChildNodes().getItem(2).getChildNodes().getItem(1)).setWidth(width);
            int height = Math.max(0, sh - 12);
            XElement.as(shadow.getChildNodes().getItem(1)).setHeight(height);
          }
        }
      }
      if (shimEnabled) {
        if (show) {
          if (shim == null) {
            shim = getShim();
          }
          shim.show();
        } else {
          if (shim == null) {
            return;
          }
        }
        Rectangle a = shadow == null ? new Rectangle(0, 0, 0, 0) : shadowAdjusts;

        if (isLegacyIELayer && shadow != null && shadow.isVisible()) {
          int shadowOffset = appearance.getShadowOffset();
          w += shadowOffset * 2;
          l -= shadowOffset;
          t -= shadowOffset;
          h += shadowOffset * 2;
        }

        try {
          shim.setLeft(l + a.getX());
          shim.setTop(t + a.getY());
          shim.setWidth(w + a.getWidth());
          shim.setHeight(h + a.getHeight());
        } catch (Exception e) {
          GWT.log("shim error", e);
        }

      }
    }
  }
  


  private final native void bind(XElement elem) /*-{
    elem.layer = this;
  }-*/;

  private XElement createShadow() {
    XElement el;
    SafeHtmlBuilder sb = new SafeHtmlBuilder();
    appearance.renderShadow(sb);
    el = XDOM.create(sb.toSafeHtml()).cast();
    el.hide();
    return el;
  }

  /**
   * Creates an iframe shim for this element to keep selects and other windowed
   * objects from showing through.
   *
   * @return the new shim element
   */
  private XElement createShim() {
    ShimTemplates shimTemplates = GWT.create(ShimTemplates.class);
    XElement shimElement = XDOM.create(shimTemplates.template(appearance.shimClass(), CommonStyles.get().ignore())).cast();
    if (GXT.isIE() && GXT.isSecure()) {
      shimElement.setPropertyString("src", GXT.getSslSecureUrl());
    }
    return shimElement;
  }

}
