package net.datenwerke.rs.transport.service.transport;

import java.util.Optional;

import com.google.common.base.MoreObjects;

public class PreconditionResult {

   public enum Result {
      OK, ERROR
   }
   
   private Optional<String> errorMsg;
   private Result result;
   
   public PreconditionResult(Optional<String> errorMsg) {
      if (errorMsg.isPresent()) 
         result = Result.ERROR;
      else 
         result = Result.OK;
      
      this.errorMsg = errorMsg;
   }
   
   
   public Optional<String> getErrorMsg() {
      return errorMsg;
   }

   public Result getResult() {
      return result;
   }
   
   @Override
   public String toString() {
      return MoreObjects.toStringHelper(getClass())
            .add("Result", result.name())
            .add("Error", errorMsg)
            .toString();
   }
   
}
