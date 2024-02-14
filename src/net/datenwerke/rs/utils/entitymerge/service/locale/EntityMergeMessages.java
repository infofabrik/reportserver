package net.datenwerke.rs.utils.entitymerge.service.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface EntityMergeMessages extends Messages{
   public final static EntityMergeMessages INSTANCE = LocalizationServiceImpl
         .getMessages(EntityMergeMessages.class);
   
   String commandEntityMerge_desc();
   String commandEntityMerge_targetEntity();
   String commandEntityMerge_valueEntity();
}
