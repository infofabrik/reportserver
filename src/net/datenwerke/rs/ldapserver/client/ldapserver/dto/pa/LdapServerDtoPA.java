package net.datenwerke.rs.ldapserver.client.ldapserver.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.ldapserver.client.ldapserver.dto.LdapServerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.pa.RemoteServerDefinitionDtoPA;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.ldapserver.service.ldapserver.entities.LdapServer.class)
public interface LdapServerDtoPA extends RemoteServerDefinitionDtoPA {


	public static final LdapServerDtoPA INSTANCE = GWT.create(LdapServerDtoPA.class);


	/* Properties */
	public ValueProvider<LdapServerDto,String> attrAdd();
	public ValueProvider<LdapServerDto,String> attrGroupMember();
	public ValueProvider<LdapServerDto,String> attrGroupName();
	public ValueProvider<LdapServerDto,String> attrGroupObjClass();
	public ValueProvider<LdapServerDto,String> attrGuid();
	public ValueProvider<LdapServerDto,String> attrObjClass();
	public ValueProvider<LdapServerDto,String> attrOrgUnitObjClass();
	public ValueProvider<LdapServerDto,String> attrOrgUnitObjName();
	public ValueProvider<LdapServerDto,String> attrUserFirstname();
	public ValueProvider<LdapServerDto,String> attrUserLastname();
	public ValueProvider<LdapServerDto,String> attrUserMail();
	public ValueProvider<LdapServerDto,String> attrUserObjClass();
	public ValueProvider<LdapServerDto,String> attrUserUsername();
	public ValueProvider<LdapServerDto,OrganisationalUnitDto> externalDir();
	public ValueProvider<LdapServerDto,Boolean> flattenTree();
	public ValueProvider<LdapServerDto,String> ldapBase();
	public ValueProvider<LdapServerDto,Boolean> ldapDisabled();
	public ValueProvider<LdapServerDto,String> ldapFilter();
	public ValueProvider<LdapServerDto,Boolean> logResultingTree();
	public ValueProvider<LdapServerDto,String> providerHost();
	public ValueProvider<LdapServerDto,Integer> providerPort();
	public ValueProvider<LdapServerDto,String> securityCredentials();
	public ValueProvider<LdapServerDto,String> securityEncryption();
	public ValueProvider<LdapServerDto,String> securityPrincipal();
	public ValueProvider<LdapServerDto,Boolean> writeProtection();
	public ValueProvider<LdapServerDto,Boolean> hasSecurityCredentials();


}
