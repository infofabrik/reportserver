package net.datenwerke.usermanager.ext.service.terminal.commands;

import static java.util.Comparator.comparing;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyService;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyUserMetadata;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.Sex;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.usermanager.ext.service.locale.UserManagerMessages;

public class IdCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "id";

   private final UserManagerService userManagerService;

   private final BsiPasswordPolicyService bsiPasswordPolicyService;
   
   private final HistoryService historyService;

   @Inject
   public IdCommand(
         UserManagerService userService, 
         BsiPasswordPolicyService bsiPasswordPolicyService,
         HistoryService historyService
         ) {
      this.userManagerService = userService;
      this.bsiPasswordPolicyService = bsiPasswordPolicyService;
      this.historyService = historyService;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(
         messageClass = UserManagerMessages.class, 
         name = BASE_COMMAND, 
         description = "commandId_description", 
         nonOptArgs = {
               @NonOptArgument(
                     name = "username", 
                     description = "commandId_arg_username", 
                     mandatory = true
                     ) 
               }
         )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      CommandResult result = new CommandResult();

      List<String> arguments = parser.getNonOptionArguments();

      if (arguments.size() != 1)
         throw new IllegalArgumentException("Please enter the username");

      String username = arguments.remove(0);
      User user = userManagerService.getUserByName(username);
      if (null == user)
         throw new IllegalArgumentException("Username not found");

      RSTableModel userInfoTable = new RSTableModel();
      TableDefinition userInfoDef = new TableDefinition(Arrays.asList("User-Info", ""),
            Arrays.asList(String.class, String.class));
      userInfoTable.setTableDefinition(userInfoDef);

      userInfoTable.addDataRow(
            new RSStringTableRow("Title", Sex.Male.equals(user.getSex()) ? UserManagerMessages.INSTANCE.genderMale()
                  : Sex.Female.equals(user.getSex()) ? UserManagerMessages.INSTANCE.genderFemale() : ""));
      userInfoTable.addDataRow(new RSStringTableRow("ID", user.getId() + ""));
      userInfoTable.addDataRow(new RSStringTableRow("Username", user.getUsername()));
      userInfoTable.addDataRow(new RSStringTableRow("First name", null != user.getFirstname() ? user.getFirstname() : ""));
      userInfoTable.addDataRow(new RSStringTableRow("Last name", null != user.getLastname() ? user.getLastname() : ""));
      userInfoTable.addDataRow(new RSStringTableRow("E-mail", null != user.getEmail() ? user.getEmail() : ""));

      BsiPasswordPolicyUserMetadata metadata = bsiPasswordPolicyService.getUserMetadata(user);

      if (null != metadata.getAccountInhibited())
         userInfoTable.addDataRow(new RSStringTableRow("Account Inhibition", String.valueOf(metadata.getAccountInhibited())));
      else
         userInfoTable.addDataRow(new RSStringTableRow("Account Inhibition", "false"));

      if (null != metadata.getAccountExpirationDate())
         userInfoTable.addDataRow(new RSStringTableRow("Accoung Expires",
               new SimpleDateFormat("dd.MM.yyyy").format(metadata.getAccountExpirationDate())));
      else
         userInfoTable.addDataRow(new RSStringTableRow("Accoung Expires", ""));

      RSTableModel groupsTable = new RSTableModel();
      TableDefinition groupsDef = new TableDefinition(Arrays.asList("Groups"),
            Arrays.asList(String.class, Long.class, String.class));
      groupsTable.setTableDefinition(groupsDef);

      Collection<Group> allGroups = userManagerService.getReferencedGroups(user);
      Collection<Group> indirectGroups = userManagerService.getIndirectGroups(user);
      if (allGroups.isEmpty()) {
         groupsTable.addDataRow(new RSStringTableRow("This user is not present in any group", " "));
      } else {
         groupsTable.addDataRow(new RSStringTableRow("Group", "Group Id", "Membership"));
         allGroups
            .stream()
            .sorted(comparing(Group::getName))
            .forEach(group -> groupsTable.addDataRow(
                  new RSStringTableRow(group.getName(), group.getId() + "", 
                        indirectGroups.contains(group)? "indirect": "direct")));
      }

      RSTableModel ouTable = new RSTableModel();
      TableDefinition ouDef = new TableDefinition(Arrays.asList("Organisational Unit"),
            Arrays.asList(String.class));
      ouTable.setTableDefinition(ouDef);

      OrganisationalUnit ou = ((OrganisationalUnit) user.getParent());

      historyService.buildLinksFor(ou)
         .forEach(link -> ouTable
            .addDataRow(new RSStringTableRow(link.getObjectCaption() + "/" + user.getUsername())));

      result.addResultTable(userInfoTable);
      result.addResultTable(groupsTable);
      result.addResultTable(ouTable);

      return result;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

}