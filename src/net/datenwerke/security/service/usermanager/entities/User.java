package net.datenwerke.security.service.usermanager.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import com.google.common.base.MoreObjects;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.dtoservices.dtogenerator.annotations.PropertyValidator;
import net.datenwerke.dtoservices.dtogenerator.annotations.StringValidator;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.utils.crypto.PasswordHasher;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.entitycloner.annotation.EntityClonerIgnore;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.validator.shared.SharedRegex;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.UserPropertiesService;
import net.datenwerke.security.service.usermanager.entities.post.User2DtoPostProcessor;
import net.datenwerke.security.service.usermanager.locale.UserManagerMessages;

/**
 * 
 *
 */
@Entity
@Table(name = "USER")
@Audited
@Indexed
@InstanceDescription(
      msgLocation = UserManagerMessages.class, 
      objNameKey = "userTypeName",
      title = "${lastname}, ${firstname}", 
      fields = { "firstname", "lastname" }, 
      icon = "user"
)
@GenerateDto(
      dtoPackage = "net.datenwerke.security.client.usermanager.dto", 
      createDecorator = true, 
      poso2DtoPostProcessors = User2DtoPostProcessor.class, 
      displayTitle = "getLastname() + \", \" + getFirstname()", 
      typeDescriptionMsg = net.datenwerke.security.client.locale.UserManagerMessages.class, 
      typeDescriptionKey = "user", 
      icon = "user", 
      additionalFields = {
            @AdditionalField(
                  name = "hasPassword", 
                  type = Boolean.class
            ) 
      }
)
public class User extends AbstractUserManagerNode {

   /**
    * 
    */
   private static final long serialVersionUID = 3056683014040311065L;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Transient
   private boolean active = true;

   @ExposeToClient(view = DtoView.MINIMAL)
   private Sex sex;

   @ExposeToClient
   private Boolean superUser = false;

   @JoinTable(name = "USER_2_PROPERTY")
   @ExposeToClient
   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @EnclosedEntity
   private Set<UserProperty> properties = new HashSet<UserProperty>();

   @ExposeToClient
   @Column(length = 40)
   private String title;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Column(length = 128)
   @Field
   private String firstname;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Column(length = 128)
   @Field
   private String lastname;

   @ExposeToClient
   @Column(length = 320)
   @Field
   private String email;

   @ExposeToClient(
         view = DtoView.LIST, 
         validateDtoProperty = @PropertyValidator(
               string = @StringValidator(
                     regex = SharedRegex.USERNAME_REGEX
               )
         )
   )
   @Column(
         length = 128, 
         unique = true, 
         nullable = false
         )
   @Field
   private String username;

   @ExposeToClient(exposeValueToClient = false, mergeDtoValueBack = false)
   @Column(length = 40)
   private String password;

   @ExposeToClient
   @ManyToMany(mappedBy = "users")
   @EntityClonerIgnore
   private Set<Group> groups = new HashSet<Group>();

   public String getFirstname() {
      return firstname;
   }

   public void setFirstname(String firstname) {
      this.firstname = firstname;
   }

   public String getLastname() {
      return lastname;
   }

   public void setLastname(String lastname) {
      this.lastname = lastname;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   /**
    * Usually you would not want to call this directly. Use
    * {@link UserManagerService#setPassword(User, String)} instead
    * 
    * @param password
    * @param passwordHasher
    */
   public void setPassword(String password, PasswordHasher passwordHasher) {
      String hashedPassword = passwordHasher.hashPassword(password);

      this.password = hashedPassword;
   }

   /**
    * Returns the groups where this user is a member directly.
    */
   public Set<Group> getGroups() {
      return groups;
   }

   public void setGroups(Set<Group> groups) {
      this.groups = groups;
   }

   public void addToGroup(Group group) {
      groups.add(group);
      group.addUser(this, true);
   }

   void addToGroup(Group group, boolean doNotInformGroup) {
      groups.add(group);
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public Sex getSex() {
      return sex;
   }

   public void setSex(Sex sex) {
      this.sex = sex;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public void setSuperUser(Boolean superUser) {
      if (null == superUser)
         superUser = false;
      this.superUser = superUser;
   }

   public Boolean isSuperUser() {
      return superUser;
   }

   public boolean isActive() {
      return active;
   }

   public void setActive(boolean active) {
      this.active = active;
   }

   /**
    * Usually you would not want to call this directly. Use
    * {@link UserPropertiesService} instead
    * 
    * @param properties
    */
   public void setProperties(Set<UserProperty> properties) {
      this.properties = properties;
   }

   /**
    * Usually you would not want to call this directly. Use
    * {@link UserPropertiesService} instead
    * 
    */
   public Set<UserProperty> getProperties() {
      return properties;
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(getClass())
            .add("ID", getId())
            .add("First name", firstname)
            .add("Last name", lastname)
            .toString();
   }

   /**
    * 
    * @see UserManagerService#getReferencedGroups(User)
    * @param uService
    */
   @Deprecated
   public Collection<Group> getReferencedGroups(UserManagerService uService) {
      return uService.getReferencedGroups(this);
   }

   @Override
   public String getName() {
      return String.valueOf(lastname) + " " + String.valueOf(firstname);
   }

   public UserProperty getProperty(String key) {
      Set<UserProperty> props = getProperties();
      if (null != props) {
         for (UserProperty prop : props) {
            if (key.equals(prop.getKey()))
               return prop;
         }
      }
      return null;
   }

   @Override
   public boolean hasChildren() {
      return false;
   }

}
