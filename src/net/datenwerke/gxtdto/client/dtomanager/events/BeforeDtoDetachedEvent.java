package net.datenwerke.gxtdto.client.dtomanager.events;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.gxtdto.client.dtomanager.Dto;

/**
 * 
 *
 */
public class BeforeDtoDetachedEvent extends DtoBaseEventImpl {

   final private Set<Dto> forDetach = new HashSet<Dto>();

   public BeforeDtoDetachedEvent(Dto dto) {
      super(dto);
      forDetach.add(dto);
   }

   public void addDtoForDetach(Dto dto) {
      forDetach.add(dto);
   }

   public Collection<Dto> getDtosForDetach() {
      return forDetach;
   }
}
