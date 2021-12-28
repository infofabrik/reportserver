package net.datenwerke.rs.terminal.client.terminal.helper;

import java.util.ArrayList;
import java.util.List;

public class HistoryHelper {

   private List<String> history = new ArrayList<String>();

   private int index = 0;

   public void addCommand(String command) {
      history.add(command);
      index = history.size() - 1;
   }

   public boolean inHistory() {
      return index + 1 != history.size();
   }

   public String last() {
      try {
         return history.get(index--);
      } catch (IndexOutOfBoundsException e) {
         index = 0;
         if (!history.isEmpty())
            return history.get(0);
         return "";
      }
   }

   public String next() {
      try {
         return history.get(index++);
      } catch (IndexOutOfBoundsException e) {
         index = history.size() - 1;
         if (!history.isEmpty())
            return history.get(index);
         return "";
      }
   }

   public void addCurrent(String value) {
      if (null == value)
         return;
      if (history.isEmpty() || !history.get(history.size() - 1).equals(value))
         addCommand(value);
   }

   public String getLast() {
      if (!history.isEmpty())
         return history.get(history.size() - 1);
      return "";
   }

}
