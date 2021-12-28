package net.datenwerke.rs.terminal.service.terminal.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;

import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser.CurrentArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.AutocompleteResult;

public class AutocompleteHelper {

   private CommandParser parser;
   private int cursorPosition;

   private List<String> baseCommandCompletion = new ArrayList<>();
   private Map<Integer, List<String>> autocompleteNamesByToken = new HashMap<>();

   private final AutocompleteResult result = new AutocompleteResult();

   @Inject
   public AutocompleteHelper() {

   }

   public void init(CommandParser parser, int cursorPosition) {
      this.parser = parser;
      this.cursorPosition = cursorPosition;
   }

   public CommandParser getParser() {
      return parser;
   }

   public int getCursorPosition() {
      return cursorPosition;
   }

   public void addResultEntry(String entry) {
      result.addEntry(entry);
   }

   public void addObjectResultEntry(String entry) {
      result.addObjectEntry(entry);
   }

   public void addCmdResultEntry(String entry) {
      result.addCmdEntry(entry);
   }

   public AutocompleteResult getResult() {
      if (parser.isBeforeFirstToken(cursorPosition)) {
         baseCommandCompletion.stream().forEach(cmd -> result.addCmdEntry(cmd));
      } else if (parser.isInBaseCommand(cursorPosition)) {
         String base = parser.getBaseCommand();

         baseCommandCompletion.stream().filter(cmd -> cmd.startsWith(base)).forEach(cmd -> result.addCmdEntry(cmd));
      }

      int cmdNr = parser.getTokenNrBy(cursorPosition);
      if (autocompleteNamesByToken.containsKey(cmdNr)) {
         CurrentArgument arg = parser.getCurrentArgument(cursorPosition);
         String cmd = arg.getArgument();
         if (null == cmd || "".equals(cmd))
            result.addEntries(autocompleteNamesByToken.get(cmdNr));
         else {
            autocompleteNamesByToken.get(cmdNr).stream().filter(autoCmd -> autoCmd.startsWith(cmd))
                  .forEach(autoCmd -> result.addEntry(autoCmd));
         }
      }

      String currentArgument = parser.getTokenNr(cmdNr);

      /* finalize */
      result.processCompleteStump(currentArgument);
      result.sort();

      return result;
   }

   public void autocompleteBaseCommand(String baseCommand) {
      baseCommandCompletion.add(baseCommand);
   }

   public void addAutocompleteNamesForToken(int tokendNr, String autocompleteName) {
      if (!autocompleteNamesByToken.containsKey(tokendNr))
         autocompleteNamesByToken.put(tokendNr, new ArrayList<String>());

      autocompleteNamesByToken.get(tokendNr).add(autocompleteName);
   }

   public void addAutocompleteNamesForToken(int tokendNr, List<String> autocompleteNames) {
      if (!autocompleteNamesByToken.containsKey(tokendNr))
         autocompleteNamesByToken.put(tokendNr, new ArrayList<String>());

      autocompleteNamesByToken.get(tokendNr).addAll(autocompleteNames);
   }
}
