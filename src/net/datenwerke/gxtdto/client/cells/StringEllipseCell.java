package net.datenwerke.gxtdto.client.cells;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.util.Format;

public class StringEllipseCell extends AbstractCell<String> {

   private final int len;

   public StringEllipseCell(int len) {
      this.len = len;
   }

   @Override
   public void render(Context context, String value, SafeHtmlBuilder sb) {
      if (null != value)
         sb.appendEscaped(Format.ellipse(value, len));
   }

}
