package net.datenwerke.usermanager.ext.service.terminal.commands;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.inject.Inject;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.ObjectResolverDeamon;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.hooks.GroupModSubCommandHook;
import net.datenwerke.security.service.usermanager.locale.UserManagerMessages;

public class AddMembersSubCommand implements GroupModSubCommandHook {

public static final String BASE_COMMAND = "addmembers";
	
	private final UserManagerService userManagerService;
	private final SecurityService securityService;

	@Inject
	public AddMembersSubCommand(
			UserManagerService userManagerService,
			SecurityService securityService
		){
		
		this.userManagerService = userManagerService;
		this.securityService = securityService;
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
		messageClass = UserManagerMessages.class,
		name = BASE_COMMAND,
		description = "commandGroupmod_sub_memberadd_description",
		args = {
			@Argument(flag="c", description="commandGroupmod_sub_memberadd_cflag")
		},
		nonOptArgs = {
			@NonOptArgument(name="group", description="commandGroupmod_sub_memberadd_arg_groupid", mandatory=true),
			@NonOptArgument(name="members", description="commandGroupmod_sub_memberadd_arg_members", varArgs=true)
		}
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
		List<String> arguments = parser.getNonOptionArguments();
		if(1 > arguments.size())
			throw new IllegalArgumentException();
		
		ObjectResolverDeamon objectResolver = session.getObjectResolver();

		/* locate group */
		String groupLocator = arguments.remove(0);
		Collection<Object> groupCandidates = objectResolver.getObjects(groupLocator, Read.class);
		if(groupCandidates.size() != 1)
			throw new IllegalArgumentException("Could not find group single group: " + groupLocator);
		if(! (groupCandidates.iterator().next() instanceof Group))
			throw new IllegalArgumentException("Could not find group single group: " + groupLocator);
		Group group = (Group) groupCandidates.iterator().next();
			
		/* check rights */
		securityService.assertRights(group, Write.class);
		
		/* get users */
		Set<AbstractUserManagerNode> memberList = new HashSet<>();
		for(String locationStr : arguments){
			Collection<Object> objectList = objectResolver.getObjects(locationStr, Read.class);
			if(objectList.isEmpty())
				throw new IllegalArgumentException("No users, groups or OUs selected: " + locationStr);
			
			for(Object obj : objectList){
				if(! (obj instanceof AbstractUserManagerNode))
					throw new IllegalArgumentException("Found unknown objects in object selection: " + obj.getClass());
				AbstractUserManagerNode node = (AbstractUserManagerNode) obj;
				node.getName();
				memberList.add((AbstractUserManagerNode) obj);
			}
		}
		
		boolean removeMembers = parser.hasOption("c");
		
		if(removeMembers && memberList.isEmpty()){
			group.clearMembers();
		}
		
		/* add/remove members */
		for(AbstractUserManagerNode member : memberList){
			if (!removeMembers) {
				/* add members */
				if (member instanceof User) {
					User user = (User) member;
					if (! group.getUsers().contains(user))
						group.addUser(user);
				} else if (member instanceof Group) {
					Group referencedGroup = (Group) member;
					if (! group.getReferencedGroups().contains(referencedGroup))
						group.addReferencedGroup(referencedGroup);
				} else if (member instanceof OrganisationalUnit) {
					OrganisationalUnit ou = (OrganisationalUnit) member;
					if (! group.getOus().contains(ou))
						group.addOu(ou);
				}
			} else {
				/* remove members */
				if (member instanceof User) {
					User user = (User) member;
					if (group.getUsers().contains(user))
						group.removeUser(user);
				} else if (member instanceof Group) {
					Group referencedGroup = (Group) member;
					if (group.getReferencedGroups().contains(referencedGroup))
						group.removeReferencedGroup(referencedGroup);
				} else if (member instanceof OrganisationalUnit) {
					OrganisationalUnit ou = (OrganisationalUnit) member;
					if (group.getOus().contains(ou))
						group.removeOu(ou);
				}
			}
		}
		
		userManagerService.merge(group);
		
		return new CommandResult();
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
	}

}
