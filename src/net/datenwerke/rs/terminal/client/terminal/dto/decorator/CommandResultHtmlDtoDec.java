package net.datenwerke.rs.terminal.client.terminal.dto.decorator;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHtmlDto;
import net.datenwerke.rs.terminal.client.terminal.helper.DisplayHelper;

/**
 * Dto Decorator for {@link CommandResultHtmlDto}
 *
 */
public class CommandResultHtmlDtoDec extends CommandResultHtmlDto {

   private static final long serialVersionUID = 1L;

   public CommandResultHtmlDtoDec() {
      super();
   }

   public void format(DisplayHelper displayHelper, SafeHtmlBuilder builder) {
      /* double dispatch */
      displayHelper.format(this, builder);
   }

}
