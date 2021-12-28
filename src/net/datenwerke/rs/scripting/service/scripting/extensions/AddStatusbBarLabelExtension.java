package net.datenwerke.rs.scripting.service.scripting.extensions;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultExtension;

@GenerateDto(dtoPackage = "net.datenwerke.rs.scripting.client.scripting.dto")
public class AddStatusbBarLabelExtension extends CommandResultExtension {

   @ExposeToClient
   private boolean left = false;

   @ExposeToClient
   private String label;

   @ExposeToClient
   private String icon;

   @ExposeToClient
   private boolean clear = false;

   public String getLabel() {
      return label;
   }

   public void setLabel(String label) {
      this.label = label;
   }

   public String getIcon() {
      return icon;
   }

   public void setIcon(String icon) {
      this.icon = icon;
   }

   public void setLeft(boolean left) {
      this.left = left;
   }

   public boolean isLeft() {
      return left;
   }

   public boolean isClear() {
      return clear;
   }

   public void setClear(boolean clear) {
      this.clear = clear;
   }

}
