package net.datenwerke.rs.teamspace.service.teamspace.terminal.commands;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.inject.Inject;

import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceMember;
import net.datenwerke.rs.teamspace.service.teamspace.hooks.TeamspaceModSubCommandHook;
import net.datenwerke.rs.teamspace.service.teamspace.locale.TeamSpaceMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.ObjectResolverDeamon;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;

public class AddMembersSubCommand implements TeamspaceModSubCommandHook {

public static final String BASE_COMMAND = "addmembers";
	
	private final TeamSpaceService teamspaceService;

	@Inject
	public AddMembersSubCommand(
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
		description = "commandTeamspacemod_sub_memberadd_description",
		args = {
			@Argument(flag="c", valueName="clear", description="commandTeamspacemod_sub_memberadd_cflag")
		},
		nonOptArgs = {
			@NonOptArgument(name="teamspace", description="commandTeamspacemod_sub_memberadd_arg_teamspaceid", mandatory=true),
			@NonOptArgument(name="members", description="commandTeamspacemod_sub_memberadd_arg_members", varArgs=true)
		}
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
		List<String> arguments = parser.getNonOptionArguments();
		if(1 > arguments.size())
			throw new IllegalArgumentException();
		
		ObjectResolverDeamon objectResolver = session.getObjectResolver();

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
		Set<AbstractUserManagerNode> memberList = new HashSet<>();
		for(String locationStr : arguments){
			Collection<Object> objectList = objectResolver.getObjects(locationStr, Read.class);
			if(objectList.isEmpty())
				throw new IllegalArgumentException("No users or groups selected: " + locationStr);
			
			for(Object obj : objectList){
				if(! (obj instanceof AbstractUserManagerNode))
					throw new IllegalArgumentException("Found unknown objects in object selection: " + obj.getClass());
				
				if (obj instanceof OrganisationalUnit)
					throw new IllegalArgumentException("Cannot add an OU to a TeamSpace");
				
				memberList.add((AbstractUserManagerNode) obj);
			}
		}
		
		boolean removeMembers = parser.hasOption("c");
		
		if(removeMembers && memberList.isEmpty()){
			Iterator<TeamSpaceMember> it = teamspace.getMembers().iterator();
			while(it.hasNext()){
				teamspaceService.remove(it.next());
				it.remove();
			}
		}
		
		/* add/remove members */
		for(AbstractUserManagerNode member : memberList){
			if (!removeMembers) {
				/* add members */
				if(! teamspace.isDirectMember(member))
					teamspace.addMember(new TeamSpaceMember(member));
			} else {
				/* remove members */
				if (teamspace.isDirectMember(member))
					teamspace.removeMember(member);
			}
					
		}
		
		teamspaceService.merge(teamspace);
		
		return new CommandResult();
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
	}

}
