package net.datenwerke.gxtdto.client.ui.helper.panel.treepanelform;

import net.datenwerke.gxtdto.client.model.DwModel;

class NavigationModelData<M> implements DwModel {

   /**
    * 
    */
   private static final long serialVersionUID = 8783945078009624370L;

   private int id;
   private String name;
   private M component;
   private String iconPath;

   public NavigationModelData() {
   }

   public NavigationModelData(int id, String name, String iconPath, M component) {
      setId(id);
      setName(name);
      setModel(component);
      setIconPath(iconPath);
   }

   public void setModel(M Model) {
      this.component = Model;
   }

   public M getModel() {
      return component;
   }

   public void setIconPath(String iconPath) {
      this.iconPath = iconPath;
   }

   public String getIconPath() {
      return iconPath;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

}
