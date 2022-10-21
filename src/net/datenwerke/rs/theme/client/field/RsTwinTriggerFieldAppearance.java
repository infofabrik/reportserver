package net.datenwerke.rs.theme.client.field;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safecss.shared.SafeStylesBuilder;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.cell.core.client.form.FieldCell.FieldAppearanceOptions;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.theme.neptune.client.base.field.Css3TwinTriggerFieldAppearance;
import com.sencha.gxt.themebuilder.base.client.config.FieldDetails;

import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class RsTwinTriggerFieldAppearance extends Css3TwinTriggerFieldAppearance {

   private final Css3TwinTriggerFieldStyle style;
   private BaseIcon triggerIcon = BaseIcon.CHEVRON_DOWN;
   private BaseIcon twinTriggerIcon = BaseIcon.CHEVRON_DOWN;
   private boolean hideTwinTrigger;

   public RsTwinTriggerFieldAppearance() {
      this(GWT.<Css3TwinTriggerFieldResources>create(Css3TwinTriggerFieldResources.class));
   }

   public RsTwinTriggerFieldAppearance(Css3TwinTriggerFieldResources resources) {
      super(resources);
      this.style = resources.style();
   }

   @Override
   public boolean twinTriggerIsOrHasChild(XElement parent, Element target) {
      if (!parent.isOrHasChild(target))
         return false;

      XElement tt = parent.selectNode(".rs-field-ttf-tt");

      return null != tt && tt.isOrHasChild(target);
   }

   @Override
   public boolean triggerIsOrHasChild(XElement parent, Element target) {
      if (!parent.isOrHasChild(target))
         return false;

      XElement t = parent.selectNode(".rs-field-ttf-t");

      return null != t && t.isOrHasChild(target);
   }

   @Override
   public void onTriggerClick(XElement parent, boolean click) {
      parent.setClassName(style.triggerClick(), click);
   }

   @Override
   public void onTriggerOver(XElement parent, boolean over) {
      parent.setClassName(style.triggerOver() + " rs-field-ttf-t-o", over);
   }

   @Override
   public void onTwinTriggerClick(XElement parent, boolean click) {
      parent.setClassName(style.twinTriggerClick(), click);
   }

   @Override
   public void onTwinTriggerOver(XElement parent, boolean over) {
      parent.setClassName(style.twinTriggerOver() + " rs-field-ttf-tt-o", over);
   }

   @Override
   public void render(SafeHtmlBuilder sb, String value, FieldAppearanceOptions options) {
      int width = options.getWidth();
      boolean hideTrigger = options.isHideTrigger();

      if (width == -1) {
         width = 150;
      }

      SafeStylesBuilder inputStylesBuilder = new SafeStylesBuilder();
      inputStylesBuilder.appendTrustedString("width:100%;");

      sb.appendHtmlConstant("<div class=\"rs-field-ttf\" style='width:" + width + "px;'>");

      if (hideTrigger) {
         sb.appendHtmlConstant("<div class='" + style.wrap() + "'>");
         renderInput(sb, value, inputStylesBuilder.toSafeStyles(), options);
      } else {
         FieldDetails fieldDetails = getResources().theme().field();

         int triggerWidth = hideTwinTrigger ? 18 : 36;

         int rightPadding = fieldDetails.padding().right();
         sb.appendHtmlConstant(
               "<div class='" + style.wrap() + "' style='padding-right:" + (triggerWidth + rightPadding) + "px;'>");
         renderInput(sb, value, inputStylesBuilder.toSafeStyles(), options);

         int right = fieldDetails.borderWidth() + 1;
         int height = -22;
         SafeStyles triggerWarpStyle = SafeStylesUtils.fromTrustedString("margin-top:" + height + "px;right:" + right
               + "px;" + "position:absolute; width: " + triggerWidth + "px;");
         sb.appendHtmlConstant(
               "<div class='" + getStyle().triggerWrap() + "' style='" + triggerWarpStyle.asString() + "'>");

         sb.appendHtmlConstant("<div class='rs-field-ttf-t " + getStyle().trigger()
               + "' style='display: inline; position: relative; width: 16px;'>").append(triggerIcon.toSafeHtml())
               .appendHtmlConstant("</div>");
         if (!hideTwinTrigger)
            sb.appendHtmlConstant("<div class='rs-field-ttf-tt " + getStyle().twinTrigger()
                  + "' style='display: inline; position: relative; width: 16px;'>").append(twinTriggerIcon.toSafeHtml())
                  .appendHtmlConstant("</div>");
         sb.appendHtmlConstant("</div>");
      }

      sb.appendHtmlConstant("</div></div>");
   }

   public void setTriggerIcon(BaseIcon icon) {
      this.triggerIcon = icon;
   }

   public void setTwinTriggerIcon(BaseIcon icon) {
      this.twinTriggerIcon = icon;
   }

   public void setHideTwinTrigger(boolean hide) {
      this.hideTwinTrigger = hide;
   }
}
