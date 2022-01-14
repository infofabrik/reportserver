package net.datenwerke.security.service.treedb.actions;

import java.util.ArrayList;
import java.util.Collection;

import net.datenwerke.security.service.security.action.SecurityActionImpl;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Right;

public class ReadAction extends SecurityActionImpl {

   public Collection<Right> getRights() {
      Collection<Right> rights = new ArrayList<Right>();
      rights.add(new Read());
      return rights;
   }

}
