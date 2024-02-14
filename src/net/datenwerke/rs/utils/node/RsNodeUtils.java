package net.datenwerke.rs.utils.node;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.treedb.service.treedb.AbstractNode;

public class RsNodeUtils {
   public static String getNodePath(AbstractNode<?> node) {
      List<String> paths = new ArrayList<>();

      AbstractNode<?> parent = node;
      while (parent != null) {
         String path = parent.getNodeName();
         if (parent.isFolder())
            path += "/";
         paths.add(path);
         parent = parent.getParent();
      }

      StringBuilder builder = new StringBuilder("/");

      for (int i = paths.size() - 1; i >= 0; i--) {
         builder.append(paths.get(i));
      }

      return builder.toString();
   }
}