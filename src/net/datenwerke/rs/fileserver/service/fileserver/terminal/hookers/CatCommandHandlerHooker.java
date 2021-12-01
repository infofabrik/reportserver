package net.datenwerke.rs.fileserver.service.fileserver.terminal.hookers;

import java.util.Scanner;

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.terminal.service.terminal.basecommands.hooks.CatCommandHandlerHook;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class CatCommandHandlerHooker implements CatCommandHandlerHook {

   @Override
   public boolean consumes(Object object, CommandParser parser) {
      return object instanceof FileServerFile;
   }

   @Override
   public CommandResult cat(Object object, CommandParser parser) {
      CommandResult result = new CommandResult();

      String data = new String(((FileServerFile) object).getData());

      try (Scanner scanner = new Scanner(data)) {
         while (scanner.hasNextLine())
            result.addResultLine(scanner.nextLine());
      }

      return result;
   }

}
