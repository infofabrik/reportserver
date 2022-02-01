package net.datenwerke.security.service.security.terminal.commands;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.security.service.security.locale.SecurityMessages;
import net.datenwerke.rs.teamspace.service.teamspace.security.rights.TeamSpaceAdministrator;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rsenterprise.license.service.annotations.EnterpriseChecked;
import net.datenwerke.security.service.security.GenericSecurityTargetMarker;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.rights.Delete;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.GrantAccess;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Right;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.usermanager.entities.User;

public class HaspermissionCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "haspermission";

   private final Provider<SecurityService> securityServiceProvider;

   @Inject
   public HaspermissionCommand(
         Provider<SecurityService> securityServiceProvider
         ) {
      this.securityServiceProvider = securityServiceProvider;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @EnterpriseChecked
   @CliHelpMessage(
         messageClass = SecurityMessages.class, 
         name = BASE_COMMAND, 
         description = "commandHaspermission_description", 
         nonOptArgs = {
               @NonOptArgument(
                     name = "user", 
                     description = "commandHaspermission_user", 
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "target", 
                     description = "commandHaspermission_target", 
                     mandatory = true
                     ),
               @NonOptArgument(
                     name = "right", 
                     description = "commandHaspermission_right", 
                     mandatory = true
                     ),
               },
         args = {
               @Argument(
                     flag = "g", 
                     hasValue = true, 
                     valueName = "generic", 
                     description = "commandHaspermission_flagG", 
                     mandatory = false
                     )
               }
         )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<?> nonOptionArguments = parser.getNonOptionArguments();
      if (nonOptionArguments.size() < 3)
         throw new IllegalArgumentException("at least 3 arguments required");
         
      String userQuery = (String) nonOptionArguments.get(0);
      String targetQuery = (String) nonOptionArguments.get(1);
      String rightString = (String) nonOptionArguments.get(2);
      
      final String argStr = "g"; 
      
      final boolean generic = parser.hasOption("g", argStr);
      
      if (!generic)
         throw new IllegalArgumentException("Only checking generic rights is currently supported");

      try {
         User user = getSingleObject(userQuery, session, User.class);
         
         Class<?> target = null;
         if (generic) {
            target = Class.forName(targetQuery);
            Arrays.stream(target.getInterfaces())
               .filter(i -> i.equals(GenericSecurityTargetMarker.class))
               .findAny()
               .orElseThrow(() -> new IllegalArgumentException(targetQuery + " is not a generic target"));
         } 
         
         Class<? extends Right> right = getRightClass(rightString);
         
         boolean hasRight = securityServiceProvider.get().checkRights(user, target, SecurityServiceSecuree.class, right);
         
         return new CommandResult(hasRight+ "");
      } catch (ClassNotFoundException e) {
         throw new IllegalArgumentException("Target not found: " + targetQuery);
      } catch (Exception e) {
         throw new TerminalException(e);
      }
   }
   
   private Class<? extends Right> getRightClass(String asString) {
      return Arrays
            .asList(Read.class, Write.class, Delete.class, Execute.class, GrantAccess.class,
                  TeamSpaceAdministrator.class)
         .stream()
         .filter(r -> r.getSimpleName().equals(asString))
         .findAny()
         .orElseThrow(() -> new IllegalArgumentException("not a valid right: " + asString));
   }
   
   private <T> T getSingleObject(String query, TerminalSession session, Class<? extends T> clazz) throws ObjectResolverException {
      Collection<Object> resolvedDatasource = session.getObjectResolver().getObjects(query, Read.class);
      if (1 != resolvedDatasource.size())
         throw new IllegalArgumentException("query must be resolved to exactly one object: \"" + query + "\"");
      Object asObject = resolvedDatasource.iterator().next();
      if (!(clazz.isInstance(asObject)))
         throw new IllegalArgumentException("not of type: \"" + clazz.getSimpleName() + "\"");
      return clazz.cast(asObject);
   }
   

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }
   
}
