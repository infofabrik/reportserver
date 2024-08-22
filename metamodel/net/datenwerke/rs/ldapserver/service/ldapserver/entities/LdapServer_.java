package net.datenwerke.rs.ldapserver.service.ldapserver.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LdapServer.class)
public abstract class LdapServer_ extends net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerDefinition_ {

	public static volatile SingularAttribute<LdapServer, String> attrUserLastname;
	public static volatile SingularAttribute<LdapServer, String> securityCredentials;
	public static volatile SingularAttribute<LdapServer, Boolean> writeProtection;
	public static volatile SingularAttribute<LdapServer, OrganisationalUnit> externalDir;
	public static volatile SingularAttribute<LdapServer, String> attrObjClass;
	public static volatile SingularAttribute<LdapServer, String> attrGroupObjClass;
	public static volatile SingularAttribute<LdapServer, Boolean> ldapDisabled;
	public static volatile SingularAttribute<LdapServer, String> ldapFilter;
	public static volatile SingularAttribute<LdapServer, Integer> providerPort;
	public static volatile SingularAttribute<LdapServer, String> attrUserMail;
	public static volatile SingularAttribute<LdapServer, String> securityEncryption;
	public static volatile SingularAttribute<LdapServer, Boolean> logResultingTree;
	public static volatile SingularAttribute<LdapServer, String> securityPrincipal;
	public static volatile SingularAttribute<LdapServer, String> attrOrgUnitObjName;
	public static volatile SingularAttribute<LdapServer, String> ldapBase;
	public static volatile SingularAttribute<LdapServer, String> attrGuid;
	public static volatile SingularAttribute<LdapServer, String> attrUserObjClass;
	public static volatile SingularAttribute<LdapServer, String> attrUserUsername;
	public static volatile SingularAttribute<LdapServer, Boolean> flattenTree;
	public static volatile SingularAttribute<LdapServer, String> attrUserFirstname;
	public static volatile SingularAttribute<LdapServer, String> attrOrgUnitObjClass;
	public static volatile SingularAttribute<LdapServer, String> attrAdd;
	public static volatile SingularAttribute<LdapServer, String> providerHost;
	public static volatile SingularAttribute<LdapServer, String> attrGroupName;
	public static volatile SingularAttribute<LdapServer, String> attrGroupMember;

}

