package net.datenwerke.gxtdto.client.dtomanager;

import java.util.Collection;

import com.sencha.gxt.core.client.ValueProvider;

import net.datenwerke.gxtdto.client.dtomanager.events.DtoListener;

/**
 * 
 *
 */
public interface ClientDtoManagerService {

   /**
    * Registers a dto and returns a proxied object.
    * 
    * @param dto
    */
   public <X extends Dto> X registerDto(X dto);

   /**
    * Registers a collections of dtos and returns proxied objects.
    * 
    * @param dtos
    */
   public <X extends Dto> Collection<X> registerDtos(Collection<X> dtos);

   /**
    * Detaches Dtos from the manager.
    * 
    * <p>
    * As a consequence all listeners on that particular dto are detached.
    * </p>
    * 
    * <p>
    * If properties for a specific dto are requested
    * 
    * </p>
    * 
    * @param <X>
    * @param dto
    */
   public <X extends Dto> void detachDto(X dto);

   /**
    * Detaches Dtos from the manager.
    * 
    * @see #detachDto(Dto)
    * @param <X>
    * @param dtos
    */
   public <X extends Dto> void detachDtos(Collection<X> dtos);

   public void registerDtoContainer(DtoContainer container);

   public <X> X getProperty(Dto proxy, ValueProvider valueProvider);

   public <X> void setProperty(Dto proxy, ValueProvider property, X value);

   public void registerDtoContainer(Collection<DtoContainer> result);

   public void onDtoChange(Dto dto, DtoListener listener);

   public void removeChangeListener(Dto dto, DtoListener listener);

   public void onDtoChange(DtoListener listener);

   public void removeDtoChangeListener(DtoListener listener);

   /**
    * Can for example be used to "clone" dtos.
    * 
    * @param <X>
    * @param dto
    */
   public <X extends Dto> X newProxy(X dto);

   /**
    * Creates a propper clone.
    * 
    * @param <X>
    * @param dto
    */
   public <X extends Dto> X deepClone(X dto);

   /**
    * Turns a proxy back into a dto
    * 
    * @param <X>
    * @param dto
    */
   public <X extends Dto> X unproxy(X dto);

   public <X extends Dto> Collection<X> unproxy(Collection<X> dtos);

}
