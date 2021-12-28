package net.datenwerke.rs.scripting.service.scripting.extensions;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultExtension;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(dtoPackage = "net.datenwerke.rs.scripting.client.scripting.dto")
public class AddToolbarEntryExtension extends CommandResultExtension {

   DisplayConditionType wl_1;

   @ExposeToClient
   private String toolbarName;

   @ExposeToClient
   private boolean left = true;

   @ExposeToClient
   private String label;

   @ExposeToClient
   private String icon;

   @ExposeToClient
   private String scriptLocation;

   @ExposeToClient(allowArbitraryLobSize = true, disableHtmlEncode = true)
   private String javaScript;

   @ExposeToClient
   private String arguments;

   @ExposeToClient
   private boolean large = false;

   @ExposeToClient
   private int columns = 1;

   @EnclosedEntity
   @ExposeToClient
   private List<AddToolbarEntryExtension> groupEntries = new ArrayList<AddToolbarEntryExtension>();

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

   public void setLarge(boolean large) {
      this.large = large;
   }

   public boolean isLarge() {
      return large;
   }

   public void setLeft(boolean left) {
      this.left = left;
   }

   public boolean isLeft() {
      return left;
   }

   public void setGroupEntries(List<AddToolbarEntryExtension> groupEntries) {
      this.groupEntries = groupEntries;
   }

   public List<AddToolbarEntryExtension> getGroupEntries() {
      return groupEntries;
   }

   public void setColumns(int columns) {
      this.columns = columns;
   }

   public int getColumns() {
      return columns;
   }

   public void setToolbarName(String toolbarName) {
      this.toolbarName = toolbarName;
   }

   public String getToolbarName() {
      return toolbarName;
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
