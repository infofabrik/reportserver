package net.datenwerke.rs.teamspace.service.teamspace.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.google.inject.Inject;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.dtoservices.dtogenerator.annotations.PropertyValidator;
import net.datenwerke.dtoservices.dtogenerator.annotations.StringValidator;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.TeamSpace2DtoPostProcessor;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.entitycloner.annotation.EntityClonerIgnore;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
@Entity
@Table(name = "TEAMSPACE")
@Audited
@Indexed
@GenerateDto(dtoPackage = "net.datenwerke.rs.teamspace.client.teamspace.dto", poso2DtoPostProcessors = TeamSpace2DtoPostProcessor.class, createDecorator = true, displayTitle = "getName()")
public class TeamSpace {

   @Inject
   private static TeamSpaceService teamspaceService;

   @Inject
   private static UserManagerService userManagerService;

   @JoinTable(name = "TEAMSPACE_2_APP")
   @ExposeToClient(mergeDtoValueBack = false)
   @EnclosedEntity
   @OneToMany(cascade = { CascadeType.ALL })
   private Set<TeamSpaceApp> apps = new HashSet<TeamSpaceApp>();

   @ExposeToClient(mergeDtoValueBack = false, view = DtoView.ALL)
   @EnclosedEntity
   @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true, mappedBy = TeamSpaceMember__.teamSpace)
   private Set<TeamSpaceMember> members = new HashSet<TeamSpaceMember>();

   @ExposeToClient(mergeDtoValueBack = false, view = DtoView.LIST)
   @EntityClonerIgnore
   @ManyToOne
   private User owner;

   @ExposeToClient(validateDtoProperty = @PropertyValidator(string = @StringValidator(maxLength = 255)), view = DtoView.LIST)
   @Column(length = 255)
   @Field
   private String name;

   @ExposeToClient(view = DtoView.LIST)
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @Field
   private String description;

   @Version
   private Long version;

   @ExposeToClient(id = true)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @PrePersist
   @PreUpdate
   final private void prePersist() {
      for (TeamSpaceMember member : members)
         member.setTeamSpace(this);
   }

   public Long getVersion() {
      return version;
   }

   public void setVersion(Long version) {
      this.version = version;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   public void addMember(TeamSpaceMember member) {
      this.members.add(member);
      member.setTeamSpace(this);
   }

   public boolean removeMember(TeamSpaceMember member) {
      return this.members.remove(member);
   }

   public void setMembers(Set<TeamSpaceMember> members) {
      if (null == this.members)
         this.members = new HashSet<>();
      this.members.clear();
      if (null != members)
         this.members.addAll(members);
      for (TeamSpaceMember member : this.members)
         member.setTeamSpace(this);
   }

   /**
    * Use {@link TeamSpaceService#getMemberFor(TeamSpace, AbstractUserManagerNode
    * folk)} instead
    */
   @Deprecated
   public TeamSpaceMember getMember(AbstractUserManagerNode folk) {
      return teamspaceService.getMemberFor(this, folk);
   }

   public Set<TeamSpaceMember> getMembers() {
      return members;
   }

   public Collection<User> getMembersAndOwner() {
      Collection<User> users = new HashSet<User>();
      for (TeamSpaceMember member : getMembers())
         if (member.getFolk() instanceof User)
            users.add((User) member.getFolk());
         else if (member.getFolk() instanceof Group)
            users.addAll(userManagerService.getAllTransitiveUsers((Group) member.getFolk()));

      users.add(getOwner());
      return users;
   }

   public void setOwner(User owner) {
      this.owner = owner;
   }

   public User getOwner() {
      return owner;
   }

   public boolean isOwner(User user) {
      if (null == user)
         return false;

      return user.equals(getOwner());
   }

   public boolean isMember(User user) {
      if (null == user)
         return false;

      for (TeamSpaceMember member : members) {
         if (member.getFolk() instanceof User) {
            if (user.equals(member.getFolk()))
               return true;
         } else if (member.getFolk() instanceof Group) {
            Set<User> groupUsers = userManagerService.getAllTransitiveUsers((Group) member.getFolk());
            if (groupUsers.contains(user))
               return true;
         }
      }

      return isOwner(user);
   }

   public boolean isDirectMember(AbstractUserManagerNode node) {
      if (null == node)
         return false;

      for (TeamSpaceMember member : members) {
         if (member.getFolk().equals(node))
            return true;
      }

      if (node instanceof User)
         return isOwner((User) node);

      return false;
   }

   public boolean removeMember(AbstractUserManagerNode node) {
      for (TeamSpaceMember member : members) {
         if (member.getFolk().equals(node)) {
            members.remove(member);
            return true;
         }
      }

      return false;
   }

   public void setApps(Set<TeamSpaceApp> apps) {
      if (null == this.apps)
         this.apps = new HashSet<>();
      this.apps.clear();
      if (null != apps)
         this.apps.addAll(apps);
   }

   public Set<TeamSpaceApp> getApps() {
      return apps;
   }

   public void addApp(TeamSpaceApp app) {
      apps.add(app);
   }

   public TeamSpaceApp getAppByType(String type) {
      if (null == type)
         return null;

      for (TeamSpaceApp app : getApps())
         if (type.equals(app.getType()))
            return app;

      return null;
   }

}
