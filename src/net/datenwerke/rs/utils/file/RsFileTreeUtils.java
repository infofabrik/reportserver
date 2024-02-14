package net.datenwerke.rs.utils.file;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import net.datenwerke.eximport.im.ImportResult.ImportResultExtraData;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.rs.utils.node.RsNodeUtils;

public class RsFileTreeUtils<T> {
   private final Map<String, T> objects;

   public RsFileTreeUtils(Map<String, T> objects) {
      this.objects = objects;
   }

   public Map<String, T> buildObjectTree() {
      Map<String, T> nodes = new HashMap<>();

      for (Entry<String, T> entry : this.objects.entrySet()) {
         T value = entry.getValue();
         if (value instanceof AbstractNode<?>) {
            AbstractNode<?> node = (AbstractNode<?>) value;
            nodes.put(RsNodeUtils.getNodePath(node), value);
         } else if (value instanceof ImportResultExtraData) {
            ImportResultExtraData data = (ImportResultExtraData) value;
            nodes.put(RsNodeUtils.getNodePath((AbstractNode<?>) data.object), value);
         }
      }
      
      return nodes.entrySet()
            .stream()
            .sorted((a,b) -> a.getKey().compareTo(b.getKey()))
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (o1, o2) -> o1, LinkedHashMap::new));
   }
}
