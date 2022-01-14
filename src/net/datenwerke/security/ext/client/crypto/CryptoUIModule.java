package net.datenwerke.security.ext.client.crypto;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class CryptoUIModule extends AbstractGinModule {

   protected static final int KEY_LENGTH = 256;
   protected static final int NR_OF_ITERATIONS = 2000;

   @Override
   protected void configure() {
      bind(CryptoUIService.class).to(CryptoUIServiceImpl.class).in(Singleton.class);
   }

}
