package net.datenwerke.gf.client.entityselector.hooks;

import net.datenwerke.gf.client.entityselector.EntitySelectorConfiguration;
import net.datenwerke.gf.client.entityselector.EntitySelectorView;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface EntitySelectionPopupSelectionMethod extends Hook {

   public boolean appliesTo(Class<?> entitiesToSelect);

   public String getName();

   public EntitySelectorView getView(EntitySelectorConfiguration config);

}
