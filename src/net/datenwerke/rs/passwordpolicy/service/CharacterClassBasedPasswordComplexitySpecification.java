package net.datenwerke.rs.passwordpolicy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.security.locale.SecurityMessages;

/**
 * Specifies the desired complexity of a password by means of character classes
 * and the minimum number of characters from each class present in the password.
 * 
 *
 */
public class CharacterClassBasedPasswordComplexitySpecification implements PasswordComplexitySpecification {

   private final static SecurityMessages messages = LocalizationServiceImpl.getMessages(SecurityMessages.class);

   private List<CharacterSelectionSpecification> characterSelectionSpecifications = new ArrayList<CharacterSelectionSpecification>();
   private int passwordMinLength;
   private CharacterClassPasswordGenerator characterClassPasswordGenerator;

   public CharacterClassBasedPasswordComplexitySpecification(int passwordMinLength) {
      this.passwordMinLength = passwordMinLength;
   }

   @Override
   public int getPasswordMinLength() {
      return passwordMinLength;
   }

   public void addSelectionSpecification(CharacterSelectionSpecification characterSelectionSpecification) {
      characterSelectionSpecifications.add(characterSelectionSpecification);
      characterClassPasswordGenerator = null;
   }

   public List<CharacterSelectionSpecification> getCharacterSelectionSpecifications() {
      return characterSelectionSpecifications;
   }

   /**
    * Checks whether the given password fulfills this specifications requirements
    * 
    * @param password
    */
   public boolean isSatisfiedBy(String password) {
      if (password.length() < passwordMinLength)
         return false;

      HashMap<Character, Integer> cindex = buildCharacterIndex(password);

      for (CharacterSelectionSpecification cse : characterSelectionSpecifications) {
         if (cse.getMinOccurrences() > 0) {
            if (cse.getMinOccurrences() > sumOccurencesOfClassmembers(cse.getCharacterClass(), cindex)) {
               return false;
            }
         }
      }

      return true;
   }

   @Override
   public List<String> getErrorCause(String password) {
      List<String> errors = new ArrayList<String>();

      if (password.length() < passwordMinLength)
         errors.add(messages.passwordLengthSpec(passwordMinLength, password.length()));

      HashMap<Character, Integer> cindex = buildCharacterIndex(password);

      for (CharacterSelectionSpecification cse : characterSelectionSpecifications)
         if (cse.getMinOccurrences() > 0)
            if (cse.getMinOccurrences() > sumOccurencesOfClassmembers(cse.getCharacterClass(), cindex))
               errors.add(messages.passwordCharacterClassRequirement(cse.getMinOccurrences(),
                     new String(cse.getCharacterClass().getCharacters()),
                     sumOccurencesOfClassmembers(cse.getCharacterClass(), cindex)));

      return errors;
   }

   private int sumOccurencesOfClassmembers(CharacterClass characterClass, HashMap<Character, Integer> characterIndex) {
      int sum = 0;
      for (char c : characterClass.getCharacters()) {
         if (characterIndex.containsKey(c)) {
            sum += characterIndex.get(c);
         }
      }

      return sum;
   }

   /**
    * Build a map which contains for each character in the password its number of
    * occurences
    * 
    * @param password
    */
   private HashMap<Character, Integer> buildCharacterIndex(String password) {
      HashMap<Character, Integer> characterCounts = new HashMap<Character, Integer>();
      for (char c : password.toCharArray()) {
         int count = 1;

         Integer oldCount = characterCounts.get(c);
         if (null != oldCount)
            count = oldCount + 1;

         characterCounts.put(c, count);
      }
      return characterCounts;
   }

   @Override
   public PasswordGenerator getPasswordGenerator() {
      if (null == characterClassPasswordGenerator)
         characterClassPasswordGenerator = new CharacterClassPasswordGenerator(this);

      return characterClassPasswordGenerator;
   }

}
