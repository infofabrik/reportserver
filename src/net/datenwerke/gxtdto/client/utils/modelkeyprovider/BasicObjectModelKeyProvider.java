package net.datenwerke.gxtdto.client.utils.modelkeyprovider;

import java.util.HashMap;

import com.sencha.gxt.data.shared.ModelKeyProvider;

public class BasicObjectModelKeyProvider<T> implements ModelKeyProvider<T> {

   private final HashMap<T, Integer> map = new HashMap<T, Integer>();
   private int cnt = 0;

   @Override
   public String getKey(T item) {
      if (!map.containsKey(item))
         map.put(item, cnt++);
      return String.valueOf(map.get(item));
   }

}
