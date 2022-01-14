package net.datenwerke.gf.service.lateinit;

import javax.persistence.EntityManager;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

/**
 * The {@link LateInitHook} allows to register a callback that is called when
 * the JPA is up and the {@link EntityManager} is available.
 * 
 *
 */
public interface LateInitHook extends Hook {

   /**
    * Called as soon as the {@link EntityManager} is available.
    */
   void initialize();
}
