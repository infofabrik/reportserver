package net.datenwerke.gxtdto.client.utils.modelkeyprovider;

import java.util.HashMap;

import com.sencha.gxt.data.shared.ModelKeyProvider;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;

public class DtoIdModelKeyProvider implements ModelKeyProvider<Dto> {

   private final HashMap<Dto, Integer> map = new HashMap<Dto, Integer>();
   private int cnt = 0;

   @Override
   public String getKey(Dto item) {
      if (item instanceof IdedDto && null != ((IdedDto) item).getDtoId())
         return String.valueOf(((IdedDto) item).getDtoId());

      if (!map.containsKey(item))
         map.put(item, cnt++);
      return String.valueOf(map.get(item));
   }

}
