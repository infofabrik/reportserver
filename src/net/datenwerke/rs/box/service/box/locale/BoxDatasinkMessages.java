package net.datenwerke.rs.box.service.box.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface BoxDatasinkMessages extends Messages {

   public final static BoxDatasinkMessages INSTANCE = LocalizationServiceImpl.getMessages(BoxDatasinkMessages.class);

   String boxDatasinkTypeName();

   String boxDatasinks();

}
