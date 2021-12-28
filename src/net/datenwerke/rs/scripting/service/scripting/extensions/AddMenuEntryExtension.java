package net.datenwerke.rs.scripting.service.scripting.extensions;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultExtension;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(dtoPackage = "net.datenwerke.rs.scripting.client.scripting.dto")
public class AddMenuEntryExtension extends CommandResultExtension {

   @ExposeToClient
   private String menuName;

   @ExposeToClient
   private String label;

   @ExposeToClient
   private String icon;

   @ExposeToClient
   private String scriptLocation;

   @ExposeToClient
   private String arguments;

   @ExposeToClient(allowArbitraryLobSize = true, disableHtmlEncode = true)
   private String javaScript;

   @EnclosedEntity
   @ExposeToClient
   private List<AddMenuEntryExtension> subMenuEntries = new ArrayList<AddMenuEntryExtension>();

   @EnclosedEntity
   @ExposeToClient
   private List<DisplayCondition> displayConditions = new ArrayList<DisplayCondition>();

   public void setSubMenuEntries(List<AddMenuEntryExtension> subMenuEntries) {
      this.subMenuEntries = subMenuEntries;
   }

   public List<AddMenuEntryExtension> getSubMenuEntries() {
      return subMenuEntries;
   }

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

   public String getScriptLocation() {
      return scriptLocation;
   }

   public void setScriptLocation(String scriptLocation) {
      this.scriptLocation = scriptLocation;
   }

   public void setMenuName(String menuName) {
      this.menuName = menuName;
   }

   public String getMenuName() {
      return menuName;
   }

   public String getArguments() {
      return arguments;
   }

   public void setArguments(String arguments) {
      this.arguments = arguments;
   }

   public void setDisplayConditions(List<DisplayCondition> displayConditions) {
      this.displayConditions = displayConditions;
   }

   public void addDisplayCondition(DisplayCondition displayCondition) {
      if (null == displayConditions)
         displayConditions = new ArrayList<DisplayCondition>();
      this.displayConditions.add(displayCondition);
   }

   public List<DisplayCondition> getDisplayConditions() {
      return displayConditions;
   }

   public String getJavaScript() {
      return javaScript;
   }

   public void setJavaScript(String javaScript) {
      this.javaScript = javaScript;
   }
}
