package net.datenwerke.rs.keyutils.service.keyutils.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public interface SpecificGenerateDefaultKeyHook extends Hook{
   
   boolean consumes(TreeDBManager treeDBManager);
   
   String generateDefaultKey(TreeDBManager treeDBManager);
}
