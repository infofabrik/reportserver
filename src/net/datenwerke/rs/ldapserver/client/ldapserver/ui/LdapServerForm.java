package net.datenwerke.rs.ldapserver.client.ldapserver.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorRegex;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.ldapserver.client.ldapserver.dto.LdapServerDto;
import net.datenwerke.rs.ldapserver.client.ldapserver.dto.pa.LdapServerDtoPA;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.locale.RemoteServerMessages;
import net.datenwerke.rs.utils.validator.shared.SharedRegex;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeFolders;

public class LdapServerForm extends SimpleFormView {

   private final Provider<UITree> treeProvider;

   @Inject
   public LdapServerForm(@UserManagerTreeFolders Provider<UITree> treeProvider) {
      /* store objects */
      this.treeProvider = treeProvider;
   }  
   
   
   @Override
   public String getComponentHeader() {
      return BaseMessages.INSTANCE.properties();
   }

   public void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(RemoteServerMessages.INSTANCE.editLdapServer() 
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      
      form.beginFloatRow();
      form.setFieldWidth(600);
      
      /* name */
      form.addField(String.class, LdapServerDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());
      
      form.setFieldWidth(500);
      
      /* key */
      form.addField(String.class, LdapServerDtoPA.INSTANCE.key(), BaseMessages.INSTANCE.key(),
            new SFFCStringValidatorRegex(SharedRegex.KEY_REGEX, BaseMessages.INSTANCE.invalidKey()),
            new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      }); // $NON-NLS-1$

      form.endRow();
      
      form.setFieldWidth(1);
      /* description */
      form.addField(String.class, LdapServerDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

      MarginData marginData = new MarginData(10, 0, 10, 10);
      
      form.beginFieldset(BaseMessages.INSTANCE.configuration());      
      
      form.beginFieldset(0, 0, null, marginData);
      form.beginFloatRow();
      form.setFieldWidth(100);
      form.addField(Boolean.class, LdapServerDtoPA.INSTANCE.ldapDisabled(), RemoteServerMessages.INSTANCE.disabled());
      form.endRow();
      
      form.beginFieldset(0, 0, RemoteServerMessages.INSTANCE.provider(), marginData);
      form.beginFloatRow();
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.providerHost(), BaseMessages.INSTANCE.host());
      form.setFieldWidth(250);
      form.addField(Integer.class, LdapServerDtoPA.INSTANCE.providerPort(), BaseMessages.INSTANCE.port());
      form.endRow();
      form.endGroup();

      form.beginFieldset(0, 0, RemoteServerMessages.INSTANCE.security(), marginData);
      form.beginFloatRow();

      /* encryption */
      form.setFieldWidth(250);
      form.addField(List.class, LdapServerDtoPA.INSTANCE.securityEncryption(), 
            RemoteServerMessages.INSTANCE.encryption(), new SFFCStaticDropdownList<String>() {

               private Map<String, String> map;

               @Override
               public Map<String, String> getValues() {
                  if (null == map) {
                     map = new HashMap<>();
                     map.put("NONE", "none");
                     map.put("STARTTLS", "starttls");
                     map.put("SSL", "ssl");
                  }

                  return map;
               }
            });
      
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.securityPrincipal(), "Principal");
      form.setFieldWidth(250);
      
      /* credentials */
      String passwordKey = form.addField(String.class, LdapServerDtoPA.INSTANCE.securityCredentials(), 
            BaseMessages.INSTANCE.password(), new SFFCPasswordField() {
            @Override
            public Boolean isPasswordSet() {
               return ((LdapServerDto) getSelectedNode()).isHasSecurityCredentials();
            }
      }); // $NON-NLS-1$
      form.endRow();
      
      Menu clearPwMenu = new DwMenu();
      MenuItem clearSecurityCredentialsItem = new DwMenuItem(BaseMessages.INSTANCE.clearPassword());
      clearPwMenu.add(clearSecurityCredentialsItem);
      clearSecurityCredentialsItem.addSelectionHandler(event -> ((LdapServerDto) getSelectedNode()).setSecurityCredentials(null));
      form.addFieldMenu(passwordKey, clearPwMenu);
      form.endGroup();
      
      form.beginFieldset(0, 0, null, marginData);
      form.beginFloatRow();
      form.setFieldWidth(1000);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.ldapBase(), RemoteServerMessages.INSTANCE.base());
      form.endRow();
      
      form.beginFieldset(0, 0, null, marginData);
      form.beginFloatRow();
      form.setFieldWidth(1000);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.ldapFilter(), RemoteServerMessages.INSTANCE.filter(),
            new SFFCTextAreaImpl());
      form.endRow();
      
      form.beginFieldset(0, 0, null, marginData);
      form.beginFloatRow();
      form.setFieldWidth(1000);
      form.addField(OrganisationalUnitDto.class, LdapServerDtoPA.INSTANCE.externalDir(),
            RemoteServerMessages.INSTANCE.externalDirectory(), new SFFCGenericTreeNode() {
               @Override
               public UITree getTreeForPopup() {
                  return treeProvider.get();
               }
      });
      form.endRow();
      
      form.beginFieldset(0, 0, null, marginData);
      form.beginFloatRow();
      form.setFieldWidth(100);
      form.addField(Boolean.class, LdapServerDtoPA.INSTANCE.writeProtection(), RemoteServerMessages.INSTANCE.writeProtection());
      form.setFieldWidth(125); 
      form.addField(Boolean.class, LdapServerDtoPA.INSTANCE.logResultingTree(), RemoteServerMessages.INSTANCE.logResultingTree());
      form.setFieldWidth(100);
      form.addField(Boolean.class, LdapServerDtoPA.INSTANCE.flattenTree(), RemoteServerMessages.INSTANCE.flattenTree());
      form.endRow();
      
      
      form.beginFieldset(0, 750, RemoteServerMessages.INSTANCE.attributes(), marginData);
      
      form.beginFieldset(0, 0, null, marginData);
      form.beginFloatRow();
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.attrObjClass(), RemoteServerMessages.INSTANCE.objectClass());
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.attrGuid(), "GUID");
      form.endRow();
      
      form.beginFieldset(0, 0, RemoteServerMessages.INSTANCE.organizationalUnit(), marginData);
      form.beginFloatRow();
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.attrOrgUnitObjClass(), RemoteServerMessages.INSTANCE.objectClass());
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.attrOrgUnitObjName(), RemoteServerMessages.INSTANCE.name());
      form.endRow();
      form.endGroup();
      
      form.beginFieldset(0, 0, RemoteServerMessages.INSTANCE.group(), marginData);
      form.beginFloatRow();
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.attrGroupObjClass(), RemoteServerMessages.INSTANCE.objectClass());
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.attrGroupName(), RemoteServerMessages.INSTANCE.name());
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.attrGroupMember(), RemoteServerMessages.INSTANCE.member());
      form.endRow();
      form.endGroup();

      form.beginFieldset(0, 0, RemoteServerMessages.INSTANCE.user(), marginData);
      form.beginFloatRow();
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.attrUserObjClass(), RemoteServerMessages.INSTANCE.objectClass());
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.attrUserFirstname(), UsermanagerMessages.INSTANCE.firstname());
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.attrUserLastname(), UsermanagerMessages.INSTANCE.lastname());
      form.endRow();
      
      form.beginFloatRow();
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.attrUserUsername(), UsermanagerMessages.INSTANCE.username());
      form.setFieldWidth(250);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.attrUserMail(), UsermanagerMessages.INSTANCE.email());
      form.endRow();
      form.endGroup();
      
      form.beginFieldset(0, 0, null, marginData);
      form.beginFloatRow();
      form.setFieldWidth(1000);
      form.addField(String.class, LdapServerDtoPA.INSTANCE.attrAdd(), RemoteServerMessages.INSTANCE.additional());
      form.endRow();
      
      //end attributes
      form.endGroup();
      
      //end configuration
      form.endGroup();
      
   }

}
