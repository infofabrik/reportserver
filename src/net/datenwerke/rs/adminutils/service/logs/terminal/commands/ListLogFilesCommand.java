package net.datenwerke.rs.adminutils.service.logs.terminal.commands;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowConsumer;
import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowFunction;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.mail.MessagingException;

import org.apache.commons.io.FileUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.service.locale.AdminUtilsMessages;
import net.datenwerke.rs.adminutils.service.logs.LogFilesService;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

public class ListLogFilesCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "listlogfiles";

   private final Provider<LogFilesService> logFilesServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;
   private final Provider<SecurityService> securityServiceProvider;
   private final Provider<DatasinkService> datasinkServiceProvider;
   private final Provider<ZipUtilsService> zipUtilsServiceProvider;

   private final String dateFormat = "yyyy-MM-dd-HH-mm-ss";

   @Inject
   public ListLogFilesCommand(
         Provider<LogFilesService> logFilesServiceProvider,
         Provider<TerminalService> terminalServiceProvider,
         Provider<SecurityService> securityServiceProvider,
         Provider<DatasinkService> datasinkServiceProvider,
         Provider<ZipUtilsService> zipUtilsServiceProvider
         ) {
      this.logFilesServiceProvider = logFilesServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
      this.datasinkServiceProvider = datasinkServiceProvider;
      this.zipUtilsServiceProvider = zipUtilsServiceProvider;
   }

   @Override
   @CliHelpMessage(
         messageClass = AdminUtilsMessages.class, 
         name = BASE_COMMAND, 
         description = "commandListlogfiles_description", 
         args = {
               @Argument(
                     flag = "s", 
                     hasValue = true, 
                     valueName = "sort", 
                     description = "commandListlogfiles_sub_flagS", 
                     mandatory = false
               ),
               @Argument(
                     flag = "f", 
                     hasValue = true, 
                     valueName = "filter", 
                     description = "commandListlogfiles_sub_flagF", 
                     mandatory = false
               ),
               @Argument(
                     flag = "e", 
                     hasValue = false, 
                     valueName = "email", 
                     description = "commandListlogfiles_sub_flagE", 
                     mandatory = false
               ),
               @Argument(
                     flag = "d", 
                     hasValue = true, 
                     valueName = "datasink", 
                     description = "commandListlogfiles_sub_flagD", 
                     mandatory = false
               ) 
         }
   )
   public CommandResult execute(final CommandParser parser, TerminalSession session) throws TerminalException {
      Path logPath = Paths.get(logFilesServiceProvider.get().getLogDirectory());
      if (!Files.exists(logPath))
         throw new IllegalArgumentException("no valid log file directory configured");

      final String argStr = "s:f:ed:";

      final boolean sendToEmail = parser.hasOption("e", argStr);
      final boolean sendToDatasink = parser.hasOption("d", argStr);

      final String sortExpression = parser.hasOption("s", argStr) ? String.valueOf(parser.parse(argStr).valueOf("s"))
            : "1"; // default: name ascending

      final List<Integer> sorting = Arrays
            .stream(sortExpression.split(";"))
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

      if (0 == sorting.size())
         throw new IllegalArgumentException("Minimum 1 sorting field");

      if (sorting.size() > 3)
         throw new IllegalArgumentException("Maximum 3 sorting fields");

      if (sorting
            .stream()
            .map(s -> (Boolean) Arrays.asList(1, 2, 3).contains(Math.abs(s)))
            .anyMatch(p -> p == false))
         throw new IllegalArgumentException("Sorting fields must be (-) 1, 2 or 3");

      CommandResult result = new CommandResult();

      try {
         final Comparator<Path> sortingComparator = sorting
               .stream()
               .map(rethrowFunction(sort -> getPathComparator(sort)))
               .reduce(Comparator::thenComparing).get(); // sorting

         // default: everything (*)
         final String filter = parser.hasOption("f", argStr) ? String.valueOf(parser.parse(argStr).valueOf("f")) : ".*"; 

         RSTableModel table = new RSTableModel();
         TableDefinition td = new TableDefinition(Arrays.asList("Name", "Last modified", "Size"),
               Arrays.asList(String.class, String.class, String.class, String.class));
         table.setTableDefinition(td);

         final Predicate<Path> matchesFilename = path -> path.getFileName().toString().matches(filter);

         try (Stream<Path> stream = Files.list(logPath)) {
            List<Path> files = stream
                  .filter(matchesFilename)
                  .filter(f -> !Files.isDirectory(f))
                  .sorted(sortingComparator)
                  .collect(toList());

            if (sendToEmail)
               logFilesServiceProvider.get().emailLogFiles(files, filter);
            
            if (sendToDatasink) {
               sendToDatasink(String.valueOf(parser.parse(argStr).valueOf("d")), session, files);
               
            }

            final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

            files.forEach(rethrowConsumer(path -> table.addDataRow(new RSStringTableRow(path.getFileName().toString(),
                  sdf.format(Files.getLastModifiedTime(path).toMillis()),
                  FileUtils.byteCountToDisplaySize(Files.size(path))))));

            result.addResultTable(table);
         }

      } catch (IOException | MessagingException e) {
         throw new TerminalException("Cound not list log files: ", e);
      } catch (Exception e) {
         throw new TerminalException(e);
      }

      return result;
   }
   
   private void sendToDatasink(String datasinkExpression, TerminalSession session, List<Path> files) throws Exception {
      DatasinkDefinition datasink = terminalServiceProvider.get().getSingleObjectOfTypeByQuery(
            DatasinkDefinition.class, datasinkExpression, session, Read.class, Execute.class);
      if (datasink instanceof TableDatasink)
         throw new IllegalArgumentException("Table datasinks not allowed: \"" + datasinkExpression + "\"");
      
      /* check rights */
      securityServiceProvider.get().assertRights(datasink, Read.class, Execute.class);
      
      try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
         zipUtilsServiceProvider.get().createZip(files, out);
         
         datasinkServiceProvider.get().exportIntoDatasink(out.toByteArray(), datasink, ".zip");
      }
   }

   private Comparator<Path> getPathComparator(int sortColumn) throws IOException {
      final Function<Path, String> byFilename = path -> path.getFileName().toString();
      final Function<Path, FileTime> byLastModified = rethrowFunction(Files::getLastModifiedTime);
      final Function<Path, Long> bySize = rethrowFunction(Files::size);

      boolean reverse = sortColumn < 0;

      Comparator<Path> pathComparator = null;

      switch (Math.abs(sortColumn)) {
      case 1:
         pathComparator = comparing(byFilename);
         break;
      case 2:
         pathComparator = comparing(byLastModified);
         break;
      case 3:
         pathComparator = comparing(bySize);
         break;
      default:
         pathComparator = comparing(byFilename);
         break;
      }

      if (reverse)
         pathComparator = pathComparator.reversed();

      return pathComparator;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

}
