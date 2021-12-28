package net.datenwerke.rs.theme.client.field;

import com.google.gwt.core.client.GWT;

import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class RsStoreFilterFieldAppearance extends RsTriggerFieldAppearance {

   public RsStoreFilterFieldAppearance() {
      this(GWT.<Css3TriggerFieldResources>create(Css3TriggerFieldResources.class));
   }

   public RsStoreFilterFieldAppearance(Css3TriggerFieldResources resources) {
      super(resources);
      setTriggerIcon(BaseIcon.TIMES);
   }
}
