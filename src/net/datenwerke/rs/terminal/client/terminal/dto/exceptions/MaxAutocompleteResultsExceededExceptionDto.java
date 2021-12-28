package net.datenwerke.rs.terminal.client.terminal.dto.exceptions;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;

public class MaxAutocompleteResultsExceededExceptionDto extends ExpectedException {

   /**
    * 
    */
   private static final long serialVersionUID = 4991248438215102315L;

   private int numberOfResults;

   public MaxAutocompleteResultsExceededExceptionDto(int numberOfResults) {
      super();
      this.numberOfResults = numberOfResults;
   }

   public int getNumberOfResults() {
      return numberOfResults;
   }
}
