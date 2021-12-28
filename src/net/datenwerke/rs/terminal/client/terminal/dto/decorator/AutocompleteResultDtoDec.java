package net.datenwerke.rs.terminal.client.terminal.dto.decorator;

import net.datenwerke.rs.terminal.client.terminal.dto.AutocompleteResultDto;

/**
 * Dto Decorator for {@link AutocompleteResultDto}
 *
 */
public class AutocompleteResultDtoDec extends AutocompleteResultDto {

   private static final long serialVersionUID = 1L;

   public AutocompleteResultDtoDec() {
      super();
   }

   public int size() {
      return getEntries().getList().size() + getCmdEntries().getList().size() + getObjectEntries().getList().size();
   }

}
