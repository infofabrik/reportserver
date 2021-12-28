package net.datenwerke.security.service.crypto.pbe;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import net.datenwerke.security.service.crypto.pbe.encrypt.ClientEncryptionService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;

/**
 * The PbeModule provides several functions for getting an
 * {@link EncryptionService} instance.
 * 
 * <h1>Description</h1> The PbeModule provides several functions for getting an
 * {@link EncryptionService} instance.
 * 
 * 
 * <h1>Content</h1>
 * <h2>Services:</h2>
 * <ul>
 * <li>{@link PbeService}</li>
 * <li>{@link EncryptionService}</li>
 * <li>{@link ClientEncryptionService}</li>
 * </ul>
 * 
 * <h2>Entities:</h2>
 * <ul>
 * </ul>
 * 
 * 
 * <h2>ClientModule:</h2>
 * <ul>
 * </ul>
 * 
 * <h2>Hookability:</h2>
 * <ul>
 * </ul>
 * 
 * 
 * <h1>Dependencies:</h1>
 * 
 * <h2>Modules:</h2>
 * <ul>
 * <li>{@link net.datenwerke.rs.core.service.guice.AbstractReportServerModule}</li>
 * </ul>
 * 
 * <h2>Entities:</h2>
 * <ul>
 * <li>{@link net.datenwerke.security.service.usermanager.entities.User}</li>
 * </ul>
 * 
 * <h2>Services:</h2>
 * <ul>
 * <li>{@link net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService}</li>
 * <li>{@link net.datenwerke.security.service.authenticator.AuthenticatorService}</li>
 * <li>{@link net.datenwerke.security.service.crypto.pbe.encrypt.ClientEncryptionService}</li>
 * </ul>
 * 
 * <h2>Others:</h2>
 * <ul>
 * <li>{@link com.google.inject.Inject}</li>
 * <li>{@link com.google.inject.Provides}</li>
 * <li>{@link com.google.inject.Singleton}</li>
 * <li>{@link net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionServiceImpl}</li>
 * <li>{@link com.google.inject.Provider}</li>
 * <li>{@link net.datenwerke.security.service.crypto.pbe.exception.PbeException}</li>
 * <li>{@link org.bouncycastle.util.encoders.Hex}</li>
 * </ul>
 */
public class PbeModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(PbeService.class).to(PbeServiceImpl.class).in(Singleton.class);
      bind(PbeConfig.class);
   }

}
