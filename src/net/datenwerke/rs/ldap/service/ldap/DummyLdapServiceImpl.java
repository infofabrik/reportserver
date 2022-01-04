package net.datenwerke.rs.ldap.service.ldap;

import javax.naming.directory.SearchResult;

import net.datenwerke.rs.ldap.service.ldap.exceptions.LdapException;

public class DummyLdapServiceImpl implements LdapService {

   @Override
   public void importUsers() throws LdapException {
   }

   @Override
   public String getStringAttribute(SearchResult sr, String attributeName) throws LdapException {
      return null;
   }

}
