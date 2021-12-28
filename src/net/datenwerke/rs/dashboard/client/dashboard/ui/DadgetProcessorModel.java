package net.datenwerke.rs.dashboard.client.dashboard.ui;

import com.google.gwt.safehtml.shared.SafeHtml;

import net.datenwerke.rs.dashboard.client.dashboard.hooks.DadgetProcessorHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

class DadgetProcessorModel {
   private String title;
   private String description;
   private BaseIcon icon;
   private DadgetProcessorHook processor;

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public SafeHtml getIcon() {
      return icon.toSafeHtml(3);
   }

   public void setIcon(BaseIcon icon) {
      this.icon = icon;
   }

   public void setIcon(SafeHtml icon) {

   }

   public DadgetProcessorHook getProcessor() {
      return processor;
   }

   public void setProcessor(DadgetProcessorHook processor) {
      this.processor = processor;
   }
}