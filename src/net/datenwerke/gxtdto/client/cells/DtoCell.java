package net.datenwerke.gxtdto.client.cells;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import net.datenwerke.gxtdto.client.dtomanager.Dto;

public class DtoCell<D extends Dto> extends AbstractCell<D> {

   @Override
   public void render(Context context, D value, SafeHtmlBuilder sb) {
      sb.appendEscaped(value.toDisplayTitle());
   }

}
