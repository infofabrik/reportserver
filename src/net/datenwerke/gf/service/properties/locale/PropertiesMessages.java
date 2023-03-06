package net.datenwerke.gf.service.properties.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface PropertiesMessages extends Messages {

   public final static PropertiesMessages INSTANCE = LocalizationServiceImpl.getMessages(PropertiesMessages.class);

   String commandProperties_description();
   
   String commandProperties_sub_list_description();
   
   String commandProperties_sub_clear_description();
   
   String commandProperties_sub_remove_description();
   
   String commandPropertiesRemove_key();
   
   String commandProperties_sub_put_description();
   
   String commandPropertiesPut_key();
   
   String commandPropertiesPut_value();
   
   String commandProperties_sub_contains_description();
   
   String commandPropertiesContains_key();

}
