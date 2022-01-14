package net.datenwerke.gxtdto.server.dtomanager;

import java.lang.reflect.Field;

import com.google.inject.ImplementedBy;

/**
 * 
 *
 */
@ImplementedBy(PosoDtoIdServiceImpl.class)
public interface PosoDtoIdServices {

   public Field getIdField(Class<?> type);

   public Field getIdField(Object entity);

   public Object getId(Object entity);
}
