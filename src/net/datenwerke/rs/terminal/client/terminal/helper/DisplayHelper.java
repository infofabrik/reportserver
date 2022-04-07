package net.datenwerke.rs.terminal.client.terminal.helper;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Hyperlink;

import net.datenwerke.rs.base.client.reportengines.table.dto.RSStringTableRowDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultAnchorDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultEntryDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHtmlDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHyperlinkDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultLineDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultListDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultTableDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultEntryDtoDec;
import net.datenwerke.rs.terminal.client.terminal.ui.TerminalWindow;

public class DisplayHelper {

   private final Resources resources = GWT.create(Resources.class);

   interface Resources extends ClientBundle {
      @Source("terminal.gss")
      Style css();
   }

   interface Style extends CssResource {
      @ClassName("rs-terminal-table")
      String rsTerminalTable();

      @ClassName("rs-terminal-table-header")
      String rsTerminalTableHeader();

      @ClassName("rs-terminal-table-row-odd")
      String rsTerminalTableRowOdd();

      @ClassName("rs-terminal-table-row-even")
      String rsTerminalTableRowEven();

      @ClassName("rs-terminal-tabled-list")
      String rsTerminalTabledList();
   }

   private static final int MAX_ELEMENTS_LIST = 5;

   private TerminalWindow terminalWindow;

   public void init(TerminalWindow terminalWindow) {
      this.terminalWindow = terminalWindow;

      resources.css().ensureInjected();
   }

   public SafeHtml format(List<String> resultList) {
      SafeHtmlBuilder builder = new SafeHtmlBuilder();

      if (resultList.size() > MAX_ELEMENTS_LIST)
         formatSplitList(resultList, builder);
      else {
         for (String entry : resultList) {
            if ("".equals(entry))
               builder.appendHtmlConstant("<div style=\"height: 0.3em;\">&nbsp;</div>");
            else
               builder.appendEscaped(entry).appendHtmlConstant("<br/>");
         }
      }

      return builder.toSafeHtml();
   }

   private void formatSplitList(List<String> resultList, SafeHtmlBuilder builder) {
      int columns = 2; // (int)(terminalWindow.getWidth() / 200);
      int rows = (int) (resultList.size() / columns) + 1;

      int i = 0;
      builder.appendHtmlConstant("<table class=\"" + resources.css().rsTerminalTabledList() + " rs-terminal-tab\">");
      while (i < columns * rows) {
         if (i % columns == 0) {
            if (i != 0)
               builder.appendHtmlConstant("</tr>");
            builder.appendHtmlConstant("<tr>");
         }

         int index = (i % (columns)) * (rows) + (int) (i / columns);
         if (index >= resultList.size())
            builder.appendHtmlConstant("<td/>");
         else {
            String entry = resultList.get(index);
            builder.appendHtmlConstant("<td>");
            builder.appendEscaped(null == entry ? "" : entry);
            builder.appendHtmlConstant("</td>");
         }

         i++;
      }
      builder.appendHtmlConstant("</tr>");
      builder.appendHtmlConstant("</table>");
   }

   public SafeHtml format(CommandResultDto result) {
      SafeHtmlBuilder builder = new SafeHtmlBuilder();

      boolean isFirst = true;
      for (CommandResultEntryDto entry : result.getEntryList()) {
         if (!isFirst)
            builder.appendHtmlConstant("<br/>");
         else
            isFirst = false;

         try {
            format(entry, builder);
         } catch (Exception e) {
            builder.appendEscaped("Could not format result: " + e.getMessage());
         }
      }

      return builder.toSafeHtml();
   }

   public void format(CommandResultEntryDto entry, SafeHtmlBuilder builder) {
      /* double dispatch */
      ((CommandResultEntryDtoDec) entry).format(this, builder);
   }

   public void format(CommandResultLineDto line, SafeHtmlBuilder builder) {
      if (null != line.getLine())
         builder.appendEscaped(line.getLine());
   }

   public void format(CommandResultListDto list, SafeHtmlBuilder builder) {
      boolean isFirst = true;

      if (list.getList().size() > MAX_ELEMENTS_LIST && !list.isDenyBreakUp())
         formatSplitList(list.getList(), builder);
      else {
         for (String entry : list.getList()) {
            if (!isFirst)
               builder.appendHtmlConstant("<br/>");
            else
               isFirst = false;

            if (null != entry)
               builder.appendEscaped(entry);
         }
      }
   }

   public void format(CommandResultTableDto table, final SafeHtmlBuilder builder) {
      builder.appendHtmlConstant("<table class=\"" + resources.css().rsTerminalTable() + " rs-terminal-tab\">");

      if (null != table.getTable().getTableDefinition()) {
         builder.appendHtmlConstant(
               "<tr class=\"" + resources.css().rsTerminalTableHeader() + " rs-terminal-tab-head\">");

         table.getTable().getTableDefinition().getColumnNames().stream().forEach(data -> {
            builder.appendHtmlConstant("<th>");
            builder.appendEscaped(data);
            builder.appendHtmlConstant("</th>");
         });

         builder.appendHtmlConstant("</tr>");
      }

      RSTableModelDto tableModel = table.getTable();
      final boolean hasDisplaySizes = null != tableModel.getTableDefinition() 
            && null != tableModel.getTableDefinition().getDisplaySizes();
      List<Integer> displaySizes = null;
      if (hasDisplaySizes)
         displaySizes = tableModel.getTableDefinition().getDisplaySizes();
      
      int i = 1;
      for (RSTableRowDto row : tableModel.getData()) {
         i++;
         builder.appendHtmlConstant(
               "<tr class=\"" + (i % 2 == 0 ? resources.css().rsTerminalTableRowEven() + " rs-terminal-tab-even"
                     : resources.css().rsTerminalTableRowOdd() + " rs-terminal-tab-odd") + "\">");

         if (row instanceof RSStringTableRowDto) {
            int counter = 0;
            for (String data : ((RSStringTableRowDto) row).getStringRow()) {
               if (hasDisplaySizes && 0 != displaySizes.get(counter))
                  builder.appendHtmlConstant("<td style=\"width:" + displaySizes.get(counter) + "px;\">");
               else 
                  builder.appendHtmlConstant("<td>");
               
               counter++;
               
               builder.appendEscaped(data);
               builder.appendHtmlConstant("</td>");
            }
         }

         builder.appendHtmlConstant("</tr>");
      }

      builder.appendHtmlConstant("</table>");
   }

   public void format(CommandResultHyperlinkDto hyperlink, SafeHtmlBuilder builder) {
      Hyperlink link = new Hyperlink(hyperlink.getCaption(), hyperlink.getHistoryToken().replace("&amp;", "&"));
      builder.appendHtmlConstant(link.getElement().getInnerHTML());
   }

   public void format(CommandResultAnchorDto anchor, SafeHtmlBuilder builder) {
      builder.appendHtmlConstant(
            "<a href=\"" + anchor.getUrl() + "\" target=\"" + anchor.getTarget() + "\">" + anchor.getText() + "</a>");
   }

   public void format(CommandResultHtmlDto html, SafeHtmlBuilder builder) {
      builder.appendHtmlConstant(html.getHtml());
   }

}
