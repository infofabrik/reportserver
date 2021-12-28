package net.datenwerke.rs.teamspace.service.teamspace.terminal.commands;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.inject.Inject;

import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceMember;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceRole;
import net.datenwerke.rs.teamspace.service.teamspace.hooks.TeamspaceModSubCommandHook;
import net.datenwerke.rs.teamspace.service.teamspace.locale.TeamSpaceMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.ObjectResolverDeamon;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;

public class SetRoleSubCommand implements TeamspaceModSubCommandHook {

public static final String BASE_COMMAND = "setrole";
	
	private final TeamSpaceService teamspaceService;

	@Inject
	public SetRoleSubCommand(
		TeamSpaceService teamspaceService
		){
		
		/* store objects */
		this.teamspaceService = teamspaceService;
	}
	
	@Override
	public String getBaseCommand() {
		return BASE_COMMAND;
	}
	
	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@Override
	@CliHelpMessage(
		messageClass = TeamSpaceMessages.class,
		name = BASE_COMMAND,
		description = "commandTeamspacemod_sub_setrole_description",
		nonOptArgs = {
			@NonOptArgument(name="role", description="commandTeamspacemod_sub_setrole_arg1", mandatory=true),
			@NonOptArgument(name="teamspace", description="commandTeamspacemod_sub_setrole_arg2", mandatory=true),
			@NonOptArgument(name="users", description="commandTeamspacemod_sub_setrole_arg3", mandatory=true, varArgs=true)
		}
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
		List<String> arguments = parser.getNonOptionArguments();
		if(2 > arguments.size())
			throw new IllegalArgumentException();
		
		ObjectResolverDeamon objectResolver = session.getObjectResolver();

		String roleStr = arguments.remove(0);
		TeamSpaceRole role;
		if("admin".equals(roleStr))
			role = TeamSpaceRole.ADMIN;
		else if("manager".equals(roleStr))
			role = TeamSpaceRole.MANAGER;
		else if("user".equals(roleStr))
			role = TeamSpaceRole.USER;
		else if("guest".equals(roleStr))
			role = TeamSpaceRole.GUEST;
		else
			throw new IllegalArgumentException("Unsupported role: " + roleStr);
		
		
		/* locate teamspace */
		String teamspaceLocator = arguments.remove(0);
		Collection<Object> teamspaceCandidates = objectResolver.getObjects(teamspaceLocator, Read.class);
		if(teamspaceCandidates.size() != 1)
			throw new IllegalArgumentException("Could not find teamspace single teamspace: " + teamspaceLocator);
		if(! (teamspaceCandidates.iterator().next() instanceof TeamSpace))
			throw new IllegalArgumentException("Could not find teamspace single teamspace: " + teamspaceLocator);
		TeamSpace teamspace = (TeamSpace) teamspaceCandidates.iterator().next();
			
		/* check rights */
		if(! teamspaceService.isManager(teamspace))
			throw new ViolatedSecurityException();
		
		/* get users */
		Set<User> userList = new HashSet<User>();
		for(String locationStr : arguments){
			Collection<Object> objectList = objectResolver.getObjects(locationStr, Read.class);
			if(objectList.isEmpty())
				throw new IllegalArgumentException("No users selected");
			
			for(Object obj : objectList){
				if(! (obj instanceof AbstractUserManagerNode))
					throw new IllegalArgumentException("Found unknown objects in object selection: " + obj.getClass());
				if(obj instanceof User)
					userList.add((User) obj);
			}
		}
		
		/* add members */
		for(User user : userList){
			TeamSpaceMember member = teamspaceService.getMemberFor(teamspace,user);
			if(null != member)
				member.setRole(role);
		}
		
		teamspaceService.merge(teamspace);
		
		return new CommandResult();
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
	}

}
