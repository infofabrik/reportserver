package net.datenwerke.rs.theme.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.theme.neptune.client.base.widget.Css3DatePickerAppearance;
import com.sencha.gxt.widget.core.client.DatePicker.DatePickerMessages;
import com.sencha.gxt.widget.core.client.DatePicker.DateState;

public class RsDatePickerAppearance extends Css3DatePickerAppearance {

   private final Css3DatePickerResources resources;
   private final Css3DatePickerStyle style;

   public RsDatePickerAppearance() {
      this(GWT.<Css3DatePickerResources>create(Css3DatePickerResources.class));
   }

   public RsDatePickerAppearance(Css3DatePickerResources resources) {
      super(resources);
      this.resources = resources;
      this.style = resources.style();

      StyleInjectorHelper.ensureInjected(style, true);
   }

   @Override
   public void render(SafeHtmlBuilder sb) {
      sb.appendHtmlConstant("<div class=\"" + style.datePicker() + " rs-datepicker\">");

      sb.appendHtmlConstant("<table width=100% cellpadding=0 cellspacing=0 class=" + style.header() + "><tr>");
      sb.appendHtmlConstant("<td class=" + style.monthLeft() + "><i class=\"fa fa-chevron-left rs-date-month-btn "
            + style.monthLeftButton() + "\"></i></td>");
      sb.appendHtmlConstant("<td class=" + style.middle() + " align=center>");

      sb.appendHtmlConstant("<table cellpadding=0 cellspacing=0 class=" + style.monthButton() + "><tr>");
      sb.appendHtmlConstant("<td class=\"" + style.monthButtonText()
            + " rs-datepicker-month\"></td><td><i class=\"fa fa-angle-down rs-date-month-btn " + style.downIcon()
            + "\"></i></td></tr></table>");

      sb.appendHtmlConstant("</td>");
      sb.appendHtmlConstant("<td class=" + style.monthRight() + "><i class=\"fa fa-chevron-right rs-date-month-btn "
            + style.monthRightButton() + "\"></i></td></tr></table>");

      sb.appendHtmlConstant(
            "<div role=grid><table width=100% cellpadding=0 cellspacing=0 class=" + style.daysWrap() + "><thead><tr>");
      for (int i = 0; i < 7; i++) {
         sb.appendHtmlConstant("<th class=" + style.columnHeader() + "><span class=" + style.columnHeaderInner() + ">"
               + i + "</span></th>");
      }
      sb.appendHtmlConstant("</tr></thead>");

      sb.appendHtmlConstant("<tbody>");
      for (int i = 0; i < 6; i++) {
         sb.appendHtmlConstant("<tr>");
         for (int j = 0; j < 7; j++) {
            sb.appendHtmlConstant(
                  "<td class=" + style.date() + "><a href=# class=" + style.dateAnchor() + "></a></td>");
         }
         sb.appendHtmlConstant("</tr>");
      }
      sb.appendHtmlConstant("</tbody></table></div>");

      sb.appendHtmlConstant("<table width=100% cellpadding=0 cellspacing=0><tr><td class=" + style.bottom()
            + " align=center></td></tr></table>");

      sb.appendHtmlConstant("</div>");

   }

   @Override
   public void renderMonthPicker(SafeHtmlBuilder sb, DatePickerMessages messages, String[] monthNames) {
      sb.appendHtmlConstant("<div class=" + style.monthPicker() + "><table border=0 cellspacing=0>");

      for (int i = 0; i < 6; i++) {
         sb.appendHtmlConstant("<tr><td class='" + style.monthPickerItem() + " " + style.month() + "'><a class='"
               + style.monthPickerItemInner() + " rs-datepicker-month' href=#>");
         sb.appendHtmlConstant(monthNames[i]);
         sb.appendHtmlConstant("</a></td>");
         sb.appendHtmlConstant("<td class='" + style.monthPickerItem() + " " + style.month() + " " + style.monthSep()
               + "'><a class='" + style.monthPickerItemInner() + " rs-datepicker-month' href=#>");
         sb.appendHtmlConstant(monthNames[i + 6]);
         sb.appendHtmlConstant("</a></td>");
         if (i == 0) {
            sb.appendHtmlConstant("<td class=" + style.yearButton() + " align=center>");
            sb.appendHtmlConstant(
                  "<i class=\"fa fa-chevron-left rs-date-month-btn " + style.leftYearIcon() + "\"></i>");
            sb.appendHtmlConstant("</td><td class='" + style.yearButton() + "' align=center>");
            sb.appendHtmlConstant(
                  "<i class=\"fa fa-chevron-right rs-date-month-btn " + style.rightYearIcon() + "\"></i>");
            sb.appendHtmlConstant("</td></tr>");
         } else {
            sb.appendHtmlConstant("<td class='" + style.monthPickerItem() + " " + style.year() + "'><a class='"
                  + style.monthPickerItemInner() + " rs-datepicker-month ' href='#'></a></td><td class='"
                  + style.monthPickerItem() + " " + style.year() + "'><a class='" + style.monthPickerItemInner()
                  + " rs-datepicker-month ' href='#'></a></td></tr>");
         }
      }

      sb.appendHtmlConstant("<tr class=" + style.monthButtons() + "><td colspan='4'>");
      sb.appendHtmlConstant("<div class=" + style.ok() + "></div>");
      sb.appendHtmlConstant("<div class=" + style.cancel() + "></div>");
      sb.appendHtmlConstant("</td></tr></table></div>");
   }

   @Override
   public void onMonthSelected(Element cell, boolean select) {
      cell.<XElement>cast().setClassName(style.monthSelected() + " rs-datepicker-s", select);
   }

   @Override
   public void onUpdateDateStyle(Element cell, DateState type, boolean add) {
      String cls = "";

      switch (type) {
      case ACTIVE:
         cls = style.dateActive();
         break;
      case DISABLED:
         cls = style.dateDisabled();
         break;
      case NEXT:
         cls = style.dateNext();
         break;
      case PREVIOUS:
         cls = style.datePrevious();
         break;
      case OVER:
         cls = style.dateOver() + " rs-datepicker-o";
         break;
      case SELECTED:
         cls = style.dateSelected() + " rs-datepicker-s";
         break;
      case TODAY:
         cls = style.dateToday();
         break;
      }

      XElement elem = cell.cast();
      elem.setClassName(cls, add);
   }
}
