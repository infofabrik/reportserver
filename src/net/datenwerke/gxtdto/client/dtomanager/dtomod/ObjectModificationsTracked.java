package net.datenwerke.gxtdto.client.dtomanager.dtomod;

import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;

/**
 * Ensures that if the object
 *
 * @param <T>
 */
public interface ObjectModificationsTracked<T> extends HasObjectChangedEventHandler<T> {

   public boolean isModified();

}
