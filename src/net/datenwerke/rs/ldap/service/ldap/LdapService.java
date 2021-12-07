package net.datenwerke.rs.ldap.service.ldap;

import net.datenwerke.rs.ldap.service.ldap.exceptions.LdapException;

public interface LdapService {

   /**
    * Imports LDAP users as configured in sso/ldap.cf
    * 
    * @throws LdapException if the LDAP users cannot be imported
    */
   void importUsers() throws LdapException;
}