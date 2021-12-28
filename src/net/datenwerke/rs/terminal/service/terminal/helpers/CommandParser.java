package net.datenwerke.rs.terminal.service.terminal.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.text.StrTokenizer;

import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 * 
 * <h3>Third Party</h3>
 * <ul>
 * <li>http://jopt-simple.sourceforge.net/</li>
 * </ul>
 * 
 *
 */
public class CommandParser {

   public class CurrentArgument {
      private int cursorPosition;
      private String argument;
      private int argumentNr;
      private boolean quoted;

      public CurrentArgument(int cursorPosition, String argument, int argumentNr, boolean quoted) {
         this.cursorPosition = cursorPosition;
         this.argument = argument;
         this.argumentNr = argumentNr;
         this.quoted = quoted;
      }

      public int getCursorPosition() {
         return cursorPosition;
      }

      public String getArgument() {
         return argument;
      }

      public int getArgumentNr() {
         return argumentNr;
      }

      public boolean isQuoted() {
         return quoted;
      }
   }

   private String rawCommand;
   private String trimmed;

   public CommandParser(String rawCommand) {
      this.rawCommand = rawCommand;
      this.trimmed = null == rawCommand ? "" : rawCommand.trim();
   }

   public String getRawCommand() {
      return rawCommand;
   }

   public String getBaseCommand() {
      if (trimmed.contains(" "))
         return trimmed.split(" ")[0];
      return trimmed;
   }

   public OptionSet parse(String options) {
      OptionParser parser = new OptionParser(options);
      try {
         return parser.parse(getArguments());
      } catch (OptionException e) {
         throw new IllegalArgumentException(e);
      }
   }

   public String[] getArguments() {
      StrTokenizer tokenizer = new StrTokenizer(trimmed.replace("\\\"", "\"\""), ' ', '"');
      List<String> args = tokenizer.getTokenList();
      args.remove(0);
      List<String> finArgs = new ArrayList<String>();
      for (String arg : args)
         finArgs.add(arg.replace("\"\"", "\\\""));
      return finArgs.toArray(new String[] {});
   }

   public String[] getTokens() {
      StrTokenizer tokenizer = new StrTokenizer(trimmed.replace("\\\"", "\"\""), ' ', '"');
      List<String> cmds = tokenizer.getTokenList();
      List<String> finArgs = new ArrayList<String>();
      for (String arg : cmds)
         finArgs.add(arg.replace("\"\"", "\\\""));
      return finArgs.toArray(new String[] {});
   }

   public boolean hasOption(String option) {
      OptionSet set = parse(option);
      return set.has(option);
   }

   public boolean hasOption(String option, String setDesc) {
      OptionSet set = parse(setDesc);
      return set.has(option);
   }

   public List<String> getNonOptionArguments() {
      String[] args = getArguments();

      List<String> argumentList = new ArrayList<String>();
      for (String arg : args)
         if (!arg.startsWith("--") && !arg.startsWith("-"))
            argumentList.add(arg);

      return argumentList;
   }

   public CurrentArgument getCurrentArgument(int cursorPosition) {
      if (null == rawCommand)
         return new CurrentArgument(0, "", 1, false);

      StrTokenizer tokenizer = new StrTokenizer(rawCommand, ' ');
      tokenizer.setIgnoreEmptyTokens(false);

      int pos = 0;
      int posInArgument = 0;
      int seenQuotes = 0;
      int argNr = 1;
      boolean lastTokenWasChar = false;
      String currentArgument = "";
      Iterator<String> tokenIt = tokenizer.getTokenList().iterator();
      while (tokenIt.hasNext()) {
         String token = tokenIt.next();
         if (seenQuotes % 2 == 0) {
            /* reset */
            currentArgument = "";
            posInArgument = 0;
            seenQuotes = 0;
            if (lastTokenWasChar)
               argNr++;
         }

         /* add token to current argument */
         if (!"".equals(currentArgument))
            currentArgument += " ";
         currentArgument += token.replaceAll("^\"", "").replaceAll("([^\\\\])\"", "$1").replaceAll("\\\\\"", "\"");

         if (!"".equals(token.trim())) {
            char lastChar = '-';
            for (char c : token.toCharArray()) {
               pos++;
               posInArgument++;

               if (c == '"' && lastChar != '\\') {
                  seenQuotes++;
                  posInArgument--;
               }

               lastChar = c;

               if (pos >= cursorPosition) {
                  if (seenQuotes % 2 == 0)
                     return new CurrentArgument(posInArgument, currentArgument.trim(), argNr, seenQuotes > 0);
                  else {
                     while (tokenIt.hasNext()) {
                        token = tokenIt.next();
                        currentArgument += " "
                              + token.replaceAll("^\"", "").replaceAll("([^\\\\])\"", "$1").replaceAll("\\\\\"", "\"");

                        for (char c2 : token.toCharArray()) {
                           if (c2 == '"' && lastChar != '\\')
                              return new CurrentArgument(posInArgument, currentArgument.trim(), argNr, seenQuotes > 0);

                           lastChar = c2;
                        }
                     }
                     return new CurrentArgument(posInArgument, currentArgument.trim(), argNr, seenQuotes > 0);
                  }
               }
            }

            /* command completed .. add 1 to position and check again */
            pos++;
            posInArgument++;
            if (pos >= cursorPosition) {
               if (seenQuotes % 2 == 0)
                  return new CurrentArgument(0, "", argNr + 1, seenQuotes > 0);
               else
                  return new CurrentArgument(posInArgument, currentArgument.trim(), argNr, seenQuotes > 0);
            }

            lastTokenWasChar = true;
         } else {
            pos++;
            posInArgument++;
            if (pos >= cursorPosition)
               return new CurrentArgument(posInArgument, currentArgument.trim(), argNr, seenQuotes > 0);

            lastTokenWasChar = false;
         }

      }

      return null;
   }

   public boolean isBeforeFirstToken(int cursorPosition) {
      if (null == rawCommand)
         return true;

      int cnt = 0;
      for (char c : rawCommand.toCharArray()) {
         cnt++;
         if (cnt > cursorPosition)
            break;
         if (c != ' ')
            return false;
      }

      return true;
   }

   public boolean isInBaseCommand(int cursorPosition) {
      boolean inCommand = false;
      boolean beforeCommand = true;
      int cnt = 0;
      for (char c : rawCommand.toCharArray()) {
         cnt++;
         if (cnt > cursorPosition)
            break;
         else if (beforeCommand && ' ' == c) {
            continue;
         } else if (beforeCommand && !inCommand) {
            inCommand = true;
            beforeCommand = false;
            continue;
         } else if (inCommand && ' ' == c) {
            inCommand = false;
            break;
         }
      }
      return inCommand;
   }

   public String getArgumentString() {
      if (!trimmed.contains(" "))
         return "";

      return trimmed.substring(trimmed.indexOf(' ')).trim();
   }

   public String getArgumentNr(int nr) {
      String[] arguments = getArguments();
      if (arguments.length >= nr)
         return arguments[nr - 1];

      return null;
   }

   public String getTokenNr(int nr) {
      String[] tokens = getTokens();
      if (tokens.length >= nr)
         return tokens[nr - 1];

      return null;
   }

   public boolean isInCommand(int commandNr, int cursorPosition) {
      return commandNr == getTokenNrBy(cursorPosition);
   }

   public int getTokenNrBy(int cursorPosition) {
      CurrentArgument curArg = getCurrentArgument(cursorPosition);
      if (null == curArg)
         return 1;
      return curArg.getArgumentNr();
   }

   public CommandParser getSubCommandParser() {
      StrTokenizer tokenizer = new StrTokenizer(rawCommand, ' ');

      String newComd = "";
      boolean afterFirst = false;
      for (String token : tokenizer.getTokenArray()) {
         if (afterFirst) {
            if (!"".equals(token.trim()))
               newComd += token + " ";
            else
               newComd += token;
         } else {
            if (!"".equals(token.trim()))
               afterFirst = true;
         }
      }
      if ("".equals(newComd.trim()))
         return this;
      return new CommandParser(newComd);
   }

}
