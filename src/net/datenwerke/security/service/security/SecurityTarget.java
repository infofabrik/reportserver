package net.datenwerke.security.service.security;

import java.io.Serializable;

import net.datenwerke.security.service.security.entities.Acl;

/**
 * An implementing object can be secured through ACLs.
 * 
 *
 */
public interface SecurityTarget extends Serializable {

   /**
    * The object's ACL
    * 
    */
   public Acl getAcl();

}
