package net.datenwerke.rs.ldap.service.ldap;

import java.util.List;
import java.util.Map;

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

   @Override
   public List<String> testFilter() throws LdapException {
      return null;
   }

   @Override
   public List<Map<String, String>> testUsers() throws LdapException {
      return null;
   }

   @Override
   public List<Map<String, String>> testGroups() throws LdapException {
      return null;
   }

   @Override
   public List<Map<String, String>> testOrganisationalUnits() throws LdapException {
      return null;
   }

}
