package net.datenwerke.rs.core.service.datasinkmanager.terminal.operators;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.operators.WriteIntoOperatorHelper;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;
import net.datenwerke.rs.terminal.service.terminal.ExecuteCommandConfig;
import net.datenwerke.rs.terminal.service.terminal.ExecuteCommandConfigImpl;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.operator.TerminalCommandOperator;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

public class WriteIntoDatasinkOperator implements TerminalCommandOperator {

   private final SecurityService securityService;
   private final Provider<DatasinkService> datasinkServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;

   @Inject
   public WriteIntoDatasinkOperator(
         SecurityService securityService,
         Provider<DatasinkService> datasinkServiceProvider,
         Provider<TerminalService> terminalServiceProvider
         ) {
      this.securityService = securityService;
      this.datasinkServiceProvider = datasinkServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
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
      return new WriteIntoOperatorHelper().getMaxOpPos(parser.getRawCommand(), even -> {});
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
         
         DatasinkDefinition datasink = terminalServiceProvider.get().getSingleObjectOfTypeByQuery(
               DatasinkDefinition.class, fileLocation, session, Read.class, Execute.class);
         if (datasink instanceof TableDatasink)
            throw new IllegalArgumentException("Table datasinks not allowed: \"" + fileLocation + "\"");

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
   
}
