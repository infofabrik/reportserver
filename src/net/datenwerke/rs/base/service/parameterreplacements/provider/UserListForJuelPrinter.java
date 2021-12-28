package net.datenwerke.rs.base.service.parameterreplacements.provider;

import java.util.Iterator;
import java.util.List;

import com.google.common.base.Splitter;

import net.datenwerke.security.service.usermanager.entities.User;

public class UserListForJuelPrinter {

   private final List<User> userList;
   private final StringBuilder template;
   private String separator = "\n";

   private boolean isHtml;

   private static final String TAG_SEPARATOR = "___";
   private static final String FIRSTNAMES = "_rs_firstnames";
   private static final String LASTNAMES = "_rs_lastnames";
   private static final String EMAILS = "_rs_emails";
   private static final String USERNAMES = "_rs_usernames";
   private static final String TITLES = "_rs_titles";
   private static final String IDS = "_rs_ids";

   private UserListForJuelPrinter(List<User> userList, boolean isHtml) {
      this.userList = userList;
      this.isHtml = isHtml;
      separator = isHtml ? "<br/>" : "\n";
      template = new StringBuilder();
   }

   public UserListForJuelPrinter addFirstnames() {
      template.append(TAG_SEPARATOR + FIRSTNAMES + TAG_SEPARATOR);
      return this;
   }

   public UserListForJuelPrinter addLastnames() {
      template.append(TAG_SEPARATOR + LASTNAMES + TAG_SEPARATOR);
      return this;
   }

   public UserListForJuelPrinter addEmails() {
      template.append(TAG_SEPARATOR + EMAILS + TAG_SEPARATOR);
      return this;
   }

   public UserListForJuelPrinter addUsernames() {
      template.append(TAG_SEPARATOR + USERNAMES + TAG_SEPARATOR);
      return this;
   }

   public UserListForJuelPrinter addTitles() {
      template.append(TAG_SEPARATOR + TITLES + TAG_SEPARATOR);
      return this;
   }

   public UserListForJuelPrinter addIds() {
      template.append(TAG_SEPARATOR + IDS + TAG_SEPARATOR);
      return this;
   }

   public UserListForJuelPrinter addString(String s) {
      template.append(s);
      return this;
   }

   public UserListForJuelPrinter addBlankspace() {
      template
            .append(isHtml && template.length() != 0 && template.charAt(template.length() - 1) == ' ' ? "&nbsp;" : " ");
      return this;
   }

   public UserListForJuelPrinter addNewline() {
      template.append(isHtml ? "<br />" : "\n");
      return this;
   }

   public UserListForJuelPrinter withSeparator(String separator) {
      this.separator = separator;
      return this;
   }

   public static UserListForJuelPrinter createInstance(List<User> users, boolean isHtml) {
      UserListForJuelPrinter printer = new UserListForJuelPrinter(users, isHtml);
      return printer;
   }

   public String print() {
      StringBuilder output = new StringBuilder();

      userList.stream().map(this::userToStringBuilder).reduce(output,
            (s1, s2) -> s1.append(s1.toString().isEmpty() ? "" : separator).append(s2));

      return output.toString();
   }

   private StringBuilder userToStringBuilder(User user) {
      StringBuilder output = new StringBuilder();
      Iterable<String> separated = Splitter.on(TAG_SEPARATOR).omitEmptyStrings().split(template.toString());
      Iterator<String> it = separated.iterator();
      while (it.hasNext()) {
         String next = it.next();
         switch (next) {
         case FIRSTNAMES:
            output.append(user.getFirstname());
            break;
         case LASTNAMES:
            output.append(user.getLastname());
            break;
         case EMAILS:
            output.append(user.getEmail());
            break;
         case USERNAMES:
            output.append(user.getUsername());
            break;
         case TITLES:
            output.append(user.getTitle());
            break;
         case IDS:
            output.append(user.getId());
            break;
         default:
            output.append(next);
         }
      }

      return output;
   }

}
