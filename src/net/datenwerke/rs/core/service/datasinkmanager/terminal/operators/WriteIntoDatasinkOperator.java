package net.datenwerke.rs.core.service.datasinkmanager.terminal.operators;

import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;
import net.datenwerke.rs.terminal.service.terminal.ExecuteCommandConfig;
import net.datenwerke.rs.terminal.service.terminal.ExecuteCommandConfigImpl;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.terminal.service.terminal.operator.TerminalCommandOperator;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

public class WriteIntoDatasinkOperator implements TerminalCommandOperator {

   private final SecurityService securityService;
   private final Provider<DatasinkService> datasinkServiceProvider;

   @Inject
   public WriteIntoDatasinkOperator(
         SecurityService securityService,
         Provider<DatasinkService> datasinkServiceProvider
         ) {
      this.securityService = securityService;
      this.datasinkServiceProvider = datasinkServiceProvider;
   }

   @Override
   public Integer consumes(String command, CommandParser parser, ExecuteCommandConfig config) {
      int maxPos = getMaxOpPos(command, parser);
      String rawCommand = parser.getRawCommand();
      if (maxPos > 0 && rawCommand.contains(">>>"))
         return maxPos;
      return null;
   }

   private int getMaxOpPos(String command, CommandParser parser) {
      int pos = 0;
      int maxPos = 0;
      char lastChar = '-';
      int seenQuotes = 0;
      for (char c : parser.getRawCommand().toCharArray()) {
         pos++;
         if ('\"' == c && lastChar != '\\')
            seenQuotes++;
         else if ('>' == c && lastChar == '>' && seenQuotes % 2 == 0) {
            maxPos = pos - 1;
         } else if ('>' == c && seenQuotes % 2 == 0) {
            maxPos = pos;
         }

         lastChar = c;
      }
      return maxPos;
   }

   @Override
   public CommandResult execute(String command, CommandParser parser, ExecuteCommandConfig config,
         TerminalSession session) throws TerminalException {
      int maxPos = getMaxOpPos(command, parser);

      String firstCommand = command.substring(0, maxPos - 2);
      try {
         String fileLocation = command.substring(maxPos + 2).trim();
         if (fileLocation.trim().equals(""))
            throw new TerminalException("Invalid datasink operator position");
         
         DatasinkDefinition datasink = getDatasink(fileLocation, session);

         /* check rights */
         securityService.assertRights(datasink, Read.class, Execute.class);

         CommandResult result = session.execute(firstCommand, new ExecuteCommandConfigImpl() {
            @Override
            public CommandResult execute(TerminalCommandHook commandHook, CommandParser parser, TerminalSession session)
                  throws TerminalException {
               return commandHook.execute(parser, session);
            }

            @Override
            public boolean allowOperators() {
               return true;
            }

            @Override
            public boolean allowInteractive() {
               return false;
            }

            @Override
            public boolean allowHijackers() {
               return false;
            }
         });
         
         Object data = null;

         if (result.containsByteData()) {
            data = result.getByteData();
         } else {
            String resultString = null == result ? "" : result.toString();
            data = resultString;
         }

         datasinkServiceProvider.get().exportIntoDatasink(data, datasink);

         CommandResult newResult = new CommandResult("Data successfully sent to datasink");
         return newResult;
      } catch (IndexOutOfBoundsException e) {
         throw new TerminalException("Invalid file operator position");
      } catch (Exception e) {
         throw new TerminalException(e);
      }
   }
   
   private DatasinkDefinition getDatasink(String query, TerminalSession session) throws ObjectResolverException {
      Collection<Object> resolvedDatasink = session.getObjectResolver().getObjects(query, Read.class, Execute.class);
      if (1 != resolvedDatasink.size())
         throw new IllegalArgumentException("datasink must be resolved to exactly one object: \"" + query + "\"");
      Object asObject = resolvedDatasink.iterator().next();
      if (!(asObject instanceof DatasinkDefinition))
         throw new IllegalArgumentException("not a DatasinkDefinition: \"" + query + "\"");
      if (asObject instanceof TableDatasink)
         throw new IllegalArgumentException("table datasinks not allowed: \"" + query + "\"");
      return (DatasinkDefinition) asObject;
   }

}
