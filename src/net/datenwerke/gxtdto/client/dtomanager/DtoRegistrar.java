package net.datenwerke.gxtdto.client.dtomanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class to register Dtos in a dto container
 *
 */
public class DtoRegistrar {

   private final ClientDtoManagerService dtoManager;
   private Map<Dto, Dto> registeredDtos = new HashMap<Dto, Dto>();

   public DtoRegistrar(ClientDtoManagerService dtoManager) {
      this.dtoManager = dtoManager;
   }

   /**
    * Registers a dto and returns a proxied object.
    * 
    * @param dto
    */
   public <X extends Dto> X registerDto(X dto) {
      if (registeredDtos.containsKey(dto))
         return (X) registeredDtos.get(dto);

      X proxy = dtoManager.registerDto(dto);
      registeredDtos.put(dto, proxy);
      return proxy;
   }

   /**
    * Registers a collections of dtos and returns proxied objects.
    * 
    * @param dtos
    */
   public <X extends Dto> List<X> registerDtos(Collection<X> dtos) {
      List<X> proxies = new ArrayList<X>();
      for (X dto : dtos)
         proxies.add(registerDto(dto));

      return proxies;
   }

}
