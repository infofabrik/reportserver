package net.datenwerke.rs.utils.validator.shared;

public class SharedRegex {
   
   public static final String KEY_REGEX = "^[a-zA-Z0-9_\\-]+$";
   
   /**
    * ^ asserts the start of the string.
    *    [\p{L}\p{N}_\-\.]+ matches one or more characters that are either:
    *    \p{L}: Any Unicode letter.
    *    \p{N}: Any Unicode number.
    *    _, -, or .: These characters are explicitly allowed.
    * $ asserts the end of the string.A
    */
   public static final String USERNAME_REGEX = "^\\S+$";
   
}
