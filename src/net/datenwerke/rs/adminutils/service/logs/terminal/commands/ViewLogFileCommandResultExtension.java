package net.datenwerke.rs.adminutils.service.logs.terminal.commands;

import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultExtension;

@GenerateDto(dtoPackage = "net.datenwerke.rs.adminutils.client.logs.dto")
public class ViewLogFileCommandResultExtension extends CommandResultExtension {

   @ExposeToClient(disableHtmlEncode = true)
   private List<String> data;

   @ExposeToClient(disableHtmlEncode = true)
   private String filename;

   public void setData(List<String> data) {
      this.data = data;
   }

   public List<String> getData() {
      return data;
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }

   public String getFilename() {
      return filename;
   }

}
