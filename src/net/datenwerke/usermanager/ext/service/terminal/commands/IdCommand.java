package net.datenwerke.usermanager.ext.service.terminal.commands;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;

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
import net.datenwerke.security.service.usermanager.entities.Sex;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.usermanager.ext.service.locale.UserManagerMessages;

public class IdCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "id";

   private final UserManagerService userService;

   private final BsiPasswordPolicyService bsiPasswordPolicyService;

   @Inject
   public IdCommand(UserManagerService userService, BsiPasswordPolicyService bsiPasswordPolicyService) {
      this.userService = userService;
      this.bsiPasswordPolicyService = bsiPasswordPolicyService;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(messageClass = UserManagerMessages.class, name = BASE_COMMAND, description = "commandId_description", nonOptArgs = {
         @NonOptArgument(name = "username", description = "commandId_arg_username", mandatory = true) })
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      CommandResult result = new CommandResult();

      List<String> arguments = parser.getNonOptionArguments();

      if (arguments.size() != 1)
         throw new IllegalArgumentException("Please enter the username");

      String username = arguments.remove(0);
      User user = userService.getUserByName(username);
      if (null == user)
         throw new IllegalArgumentException("Username not found");

      RSTableModel table = new RSTableModel();
      TableDefinition td = new TableDefinition(Arrays.asList("User-Info", ""),
            Arrays.asList(String.class, String.class));
      table.setTableDefinition(td);

      table.addDataRow(
            new RSStringTableRow("Title", Sex.Male.equals(user.getSex()) ? UserManagerMessages.INSTANCE.genderMale()
                  : Sex.Female.equals(user.getSex()) ? UserManagerMessages.INSTANCE.genderFemale() : ""));
      table.addDataRow(new RSStringTableRow("ID", user.getId() + ""));
      table.addDataRow(new RSStringTableRow("Username", user.getUsername()));
      table.addDataRow(new RSStringTableRow("First name", null != user.getFirstname() ? user.getFirstname() : ""));
      table.addDataRow(new RSStringTableRow("Last name", null != user.getLastname() ? user.getLastname() : ""));
      table.addDataRow(new RSStringTableRow("E-mail", null != user.getEmail() ? user.getEmail() : ""));

      BsiPasswordPolicyUserMetadata metadata = bsiPasswordPolicyService.getUserMetadata(user);

      if (null != metadata.getAccountInhibited())
         table.addDataRow(new RSStringTableRow("Account Inhibition", String.valueOf(metadata.getAccountInhibited())));
      else
         table.addDataRow(new RSStringTableRow("Account Inhibition", "false"));

      if (null != metadata.getAccountExpirationDate())
         table.addDataRow(new RSStringTableRow("Accoung Expires",
               new SimpleDateFormat("dd.MM.yyyy").format(metadata.getAccountExpirationDate())));
      else
         table.addDataRow(new RSStringTableRow("Accoung Expires", ""));

      result.addResultTable(table);

      return result;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

}