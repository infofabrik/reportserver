package net.datenwerke.rs.terminal.service.terminal.obj;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.terminal.client.terminal.dto")
public class CreJavaScript extends CommandResultExtension {

   @ExposeToClient(allowArbitraryLobSize = true)
   private String data;

   public CreJavaScript() {
   }

   public CreJavaScript(String data) {
      this.data = data;
   }

   public String getData() {
      return data;
   }

   public void setData(String data) {
      this.data = data;
   }
}
