package net.datenwerke.security.service.usermanager.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;
import net.datenwerke.security.service.usermanager.locale.UserManagerMessages;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;

/**
 * 
 *
 */
@Entity
@Table(name="GROUP")
@Audited
@Indexed
@GenerateDto(
	dtoPackage="net.datenwerke.security.client.usermanager.dto",
	typeDescriptionMsg=net.datenwerke.security.client.locale.UserManagerMessages.class, typeDescriptionKey="group",
	icon="group"
)
@InstanceDescription(
	msgLocation=UserManagerMessages.class,
	objNameKey="groupTypeName",
	icon = "group"
)
public class Group extends AbstractUserManagerNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9165913719225457972L;

	@JoinTable(name="GROUP_2_OU")
	@ExposeToClient
	@ManyToMany
	private Set<OrganisationalUnit> ous = new HashSet<OrganisationalUnit>();
	
	@JoinTable(name="GROUP_2_USER")
	@ExposeToClient
	@ManyToMany
	private Set<User> users = new HashSet<User>();
	
	@JoinTable(name="GROUP_2_GROUP")
	@ExposeToClient
	@ManyToMany
	private Set<Group> referencedGroups = new HashSet<Group>();
	
	@ExposeToClient(view=DtoView.MINIMAL)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	@Description
	@Field
    private String description;
	
	@ExposeToClient(displayTitle=true)
	@Field
	@Column(length = 255)
	@Title
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public void addUser(User user){
		users.add(user);
		user.addToGroup(this, true);
	}
    
	void addUser(User user, boolean doNotInformGroup){
		users.add(user);
	}
	
	public void removeUser(User user){
		users.remove(user);
	}
	
	public void addOu(OrganisationalUnit ou){
		this.ous.add(ou);
	}
	
	public void removeOu(OrganisationalUnit ou){
		this.ous.remove(ou);
	}
	
	public void setOus(Set<OrganisationalUnit> ous) {
		this.ous = ous;
	}

	public Set<OrganisationalUnit> getOus() {
		return ous;
	}
	
	public void clearMembers() {
		referencedGroups.clear();
		ous.clear();
		users.clear();
	}

	public Set<Group> getReferencedGroups() {
		return referencedGroups;
	}

	public void setReferencedGroups(Set<Group> referencedGroups) {
		this.referencedGroups = referencedGroups;
	}
	
	public void addReferencedGroup(Group referencedGroup){
		this.referencedGroups.add(referencedGroup);
	}
	
	public void removeReferencedGroup(Group group){
		this.referencedGroups.remove(group);
	}
	
	public List<Group> getAllReferencedGroups(){
		List<Group> refGroups = new ArrayList<Group>();
		_getAllReferencedGroups(refGroups, this);
		return refGroups;
	}
	
	private void _getAllReferencedGroups(List<Group> refGroups, Group group) {
		if(refGroups.contains(group))
			return;
		refGroups.add(group);
		for(Group g : group.getReferencedGroups())
			_getAllReferencedGroups(refGroups, g);
	}

	@Override
	public boolean hasChildren() {
		return false;
	}
}
