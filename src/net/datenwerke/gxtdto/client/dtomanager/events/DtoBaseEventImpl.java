package net.datenwerke.gxtdto.client.dtomanager.events;

import net.datenwerke.gxtdto.client.dtomanager.Dto;

public abstract class DtoBaseEventImpl implements DtoBaseEvent {

   protected final Dto dto;

   public DtoBaseEventImpl(Dto dto) {
      this.dto = dto;
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.gxtdto.client.dtomanager.events.DtoBaseEvent#getDto()
    */
   public Dto getDto() {
      return dto;
   }

}
