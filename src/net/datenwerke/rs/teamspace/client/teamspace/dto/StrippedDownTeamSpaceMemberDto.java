package net.datenwerke.rs.teamspace.client.teamspace.dto;

import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.ie.AbstractStrippedDownNode;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownGroup;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;


public class StrippedDownTeamSpaceMemberDto extends AbstractStrippedDownNode implements Comparable<StrippedDownTeamSpaceMemberDto> {

	public static enum Type{
		GROUP,
		OU,
		USER
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4457293462541571533L;

	private TeamSpaceRoleDto role;
	
	private String name;
	private String firstname;
	private String lastname;
	private boolean owner;
	private Type type;

	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public Long getFolkId() {
		return getId();
	}

	public void setFolkId(Long folkId) {
		setId(folkId);
	}

	public TeamSpaceRoleDto getRole() {
		return role;
	}

	public void setRole(TeamSpaceRoleDto role) {
		this.role = role;
	}

	public String getFirstname() {
		return firstname;
	}
	
	public String getName() {
		if(null == name)
			return getLastname() + ", " + getFirstname();
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	@Override
	public int hashCode() {
		return getFolkId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof StrippedDownTeamSpaceMemberDto))
			return false;

		return getFolkId().equals(((StrippedDownTeamSpaceMemberDto)obj).getFolkId());
	}

	public static StrippedDownTeamSpaceMemberDto createFrom(
			StrippedDownUser user) {
		StrippedDownTeamSpaceMemberDto member = new StrippedDownTeamSpaceMemberDto();

		member.setFolkId(user.getId());
		member.setFirstname(user.getFirstname());
		member.setLastname(user.getLastname());
		member.setRole(TeamSpaceRoleDto.GUEST);

		return member;
	}

	public static StrippedDownTeamSpaceMemberDto createFrom(StrippedDownGroup group) {
		StrippedDownTeamSpaceMemberDto member = new StrippedDownTeamSpaceMemberDto();

		member.setFolkId(group.getId());
		member.setType(Type.GROUP);
		member.setName(group.getName());
		member.setRole(TeamSpaceRoleDto.GUEST);

		return member;
	}
	
	public static StrippedDownTeamSpaceMemberDto createForOwner(UserDto user) {
		StrippedDownTeamSpaceMemberDto member = new StrippedDownTeamSpaceMemberDto();

		member.setFolkId(user.getId());
		member.setType(Type.USER);
		member.setFirstname(user.getFirstname());
		member.setLastname(user.getLastname());
		member.setRole(TeamSpaceRoleDto.ADMIN);
		member.setOwner(true);

		return member;
	}


	public static StrippedDownTeamSpaceMemberDto createFrom(TeamSpaceMemberDto member) {
		StrippedDownTeamSpaceMemberDto sMember = new StrippedDownTeamSpaceMemberDto();

		sMember.setFolkId(member.getFolk().getId());
		sMember.setRole(member.getRole());

		if(member.getFolk() instanceof UserDto){
			sMember.setFirstname(((UserDto)member.getFolk()).getFirstname());
			sMember.setLastname(((UserDto)member.getFolk()).getLastname());
			sMember.setType(Type.USER);
		} else if(member.getFolk() instanceof GroupDto){
			sMember.setName(((GroupDto)member.getFolk()).getName());
			sMember.setType(Type.GROUP);
		} else if(member.getFolk() instanceof OrganisationalUnitDto){
			sMember.setName(((OrganisationalUnitDto)member.getFolk()).getName());
			sMember.setType(Type.OU);
		}

		return sMember;
	}

	public StrippedDownUser getStrippedUser() {
		StrippedDownUser user = new StrippedDownUser();

		user.setId(getFolkId());
		user.setFirstname(getFirstname());
		user.setLastname(getLastname());

		return user;
	}
	
	public StrippedDownGroup getStrippedGroup() {
		StrippedDownGroup group = new StrippedDownGroup();

		group.setId(getFolkId());
		group.setName(getName());

		return group;
	}

	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	@Override
	public int compareTo(StrippedDownTeamSpaceMemberDto o) {
		int compareToType = o.getType().compareTo(getType()) ;
		if(compareToType != 0)
			return compareToType;
		
		return null != getName() ? getName().compareTo(o.getName()) : null != o.getName() ? -1 : 0;
	}

}
