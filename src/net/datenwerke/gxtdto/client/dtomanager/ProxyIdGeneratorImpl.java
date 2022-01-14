package net.datenwerke.gxtdto.client.dtomanager;

import com.google.inject.Singleton;

/**
 * 
 *
 */
@Singleton
public class ProxyIdGeneratorImpl implements ProxyIdGenerator {

   private Long currentId = 1L;

   public synchronized Long nextId() {
      return currentId++;
   }
}
