package net.datenwerke.rs.fileserver.service.fileserver.terminal.operators

import java.util.function.Consumer

import net.datenwerke.rs.fileserver.service.fileserver.terminal.operators.WriteIntoFileOperator.Mode
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser

class WriteIntoOperatorHelper {
   
   int getMaxOpPos(String rawCommand, Consumer<Boolean> callback) {
      int pos = 0
      int maxPos = 0
      char lastChar = '-'
      int seenQuotes = 0
      boolean even = false
      rawCommand.each { c -> 
         pos++
         if ('\"' == c && lastChar != '\\')
            seenQuotes++
         else if ('>' == c && lastChar == '>' && seenQuotes % 2 == 0) {
            even = true
            maxPos = pos - 1
         } else if ('>' == c && seenQuotes % 2 == 0) {
            even = false
            maxPos = pos
         }

         lastChar = c
      }
      callback.accept even
      return maxPos
   }
   
}

