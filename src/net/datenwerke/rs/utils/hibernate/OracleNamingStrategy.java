package net.datenwerke.rs.utils.hibernate;

/**
 * Class under Apache License 2.0 
 * http://code.google.com/p/hibernate-naming-strategy-for-oracle/
 */

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;

import org.hibernate.cfg.DefaultComponentSafeNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
public class OracleNamingStrategy extends DefaultComponentSafeNamingStrategy {

   private final static Logger logger = LoggerFactory.getLogger(OracleNamingStrategy.class.getName());

   /**
    * value
    */
   private static final long serialVersionUID = -9016141987169984605L;

   private static final int MAX_LENGTH = 27;
   private static final String TABLE_PREFIX = "RS_"; //$NON-NLS-1$

   private static final Map<String, String> propertyKeywordMap;
   static {
      propertyKeywordMap = new HashMap<String, String>();
      propertyKeywordMap.put("access", "ACCESS_FIELD"); //$NON-NLS-1$ //$NON-NLS-2$
      propertyKeywordMap.put("date", "DATE_FIELD"); //$NON-NLS-1$ //$NON-NLS-2$
      propertyKeywordMap.put("id", "ENTITY_ID"); //$NON-NLS-1$ //$NON-NLS-2$
      propertyKeywordMap.put("level", "LEVEL_FIELD"); //$NON-NLS-1$ //$NON-NLS-2$
      propertyKeywordMap.put("name", "NAME_FIELD"); //$NON-NLS-1$ //$NON-NLS-2$
      propertyKeywordMap.put("mode", "MODE_FIELD"); //$NON-NLS-1$ //$NON-NLS-2$
      propertyKeywordMap.put("version", "ENTITY_VERSION"); //$NON-NLS-1$ //$NON-NLS-2$
      propertyKeywordMap.put("key", "KEY_FIELD");
      propertyKeywordMap.put("order", "ORDER_FIELD");
      propertyKeywordMap.put("start", "START_FIELD");
      propertyKeywordMap.put("end", "END_FIELD");
      propertyKeywordMap.put("separator", "SEPARATOR_FIELD");
   }

   private static final Map<String, String> joinTableNameMap;
   static {
      joinTableNameMap = new HashMap<String, String>();
      joinTableNameMap.put("sapfilter|includeranges", "RS_SAP_FILTER_INCLUDE_RANGES"); //$NON-NLS-1$ //$NON-NLS-2$
      joinTableNameMap.put("sapfilter|excluderanges", "RS_SAP_FILTER_EXCLUDE_RANGES"); //$NON-NLS-1$ //$NON-NLS-2$
   }

   private static final Map<String, String> assignedJoinTableNameMap = new HashMap<String, String>();

   protected static String addUnderscores(String name) {
      if (name == null)
         return null;

      StringBuffer buf = new StringBuffer(name.replace('.', '_'));
      for (int i = 1; i < buf.length() - 1; i++) {
         if ((isLowerToUpper(buf, i)) || (isMultipleUpperToLower(buf, i))

         ) {
            buf.insert(i++, '_');
         }
      }
      return buf.toString().toLowerCase();
   }

   private static boolean isMultipleUpperToLower(StringBuffer buf, int i) {
      return i > 1 && Character.isUpperCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i - 2))
            && Character.isLowerCase(buf.charAt(i));
   }

   private static boolean isLowerToUpper(StringBuffer buf, int i) {
      return Character.isLowerCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i));
   }

   @Override
   public String collectionTableName(String ownerEntity, String ownerEntityTable, String associatedEntity,
         String associatedEntityTable, String propertyName) {
      String tableName = null;

      /* test if we have a name in our lookup table */
      String lookupName = new StringBuilder().append(ownerEntityTable).append('|') // $NON-NLS-1$
            .append(propertyName).toString().toLowerCase();
      if (joinTableNameMap.containsKey(lookupName))
         tableName = joinTableNameMap.get(lookupName);

      Class<?> ownerEntityType = null;
      try {
         ownerEntityType = Class.forName(ownerEntity);
      } catch (ClassNotFoundException e) {
         logger.warn(e.getMessage(), e);
      }
      if (null == tableName && ownerEntityType.isAnnotationPresent(AssociationOverride.class)) {
         AssociationOverride ao = ownerEntityType.getAnnotation(AssociationOverride.class);
         if (propertyName.equals(ao.name()) && null != ao.joinTable()) {
            tableName = ao.joinTable().name();
         }
      }
      if (null == tableName && ownerEntityType.isAnnotationPresent(AssociationOverrides.class)) {
         AssociationOverrides aos = ownerEntityType.getAnnotation(AssociationOverrides.class);
         for (AssociationOverride ao : aos.value()) {
            if (propertyName.equals(ao.name()) && null != ao.joinTable()) {
               tableName = ao.joinTable().name();
               break;
            }
         }
      }

      String key = new StringBuilder().append(ownerEntity).append('|') // $NON-NLS-1$
            .append(ownerEntityTable).append('|') // $NON-NLS-1$
            .append(associatedEntity).append('|') // $NON-NLS-1$
            .append(associatedEntityTable).append('|') // $NON-NLS-1$
            .append(propertyName).toString();

      /* if no name has been set */
      if (null == tableName)
         tableName = abbreviateName(super.collectionTableName(addUnderscores(ownerEntity),
               addUnderscores(ownerEntityTable), addUnderscores(associatedEntity),
               addUnderscores(associatedEntityTable), addUnderscores(propertyName)));

      /* test that we have not yet assigned the name */
      if (assignedJoinTableNameMap.containsKey(tableName) && !assignedJoinTableNameMap.get(tableName).equals(key))
         throw new RuntimeException("Name clash when making up a name (" + tableName + ") for join table: " + key //$NON-NLS-1$ //$NON-NLS-2$
               + ".\nName clashes with " + assignedJoinTableNameMap.containsKey(tableName)); //$NON-NLS-1$

      assignedJoinTableNameMap.put(tableName, key);

      return tableName.startsWith(TABLE_PREFIX) ? tableName : TABLE_PREFIX + tableName;
   }

   @Override
   public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName,
         String referencedColumnName) {
      return abbreviateName(super.foreignKeyColumnName(addUnderscores(propertyName), addUnderscores(propertyEntityName),
            addUnderscores(propertyTableName), addUnderscores(referencedColumnName)));
   }

   @Override
   public String logicalCollectionColumnName(String columnName, String propertyName, String referencedColumn) {
      return abbreviateName(super.logicalCollectionColumnName(addUnderscores(columnName), addUnderscores(propertyName),
            addUnderscores(referencedColumn)));
   }

   @Override
   public String logicalCollectionTableName(String tableName, String ownerEntityTable, String associatedEntityTable,
         String propertyName) {
      return null != tableName && tableName.startsWith(TABLE_PREFIX) ? ""
            : TABLE_PREFIX + abbreviateName(
                  super.logicalCollectionTableName(addUnderscores(tableName), addUnderscores(ownerEntityTable),
                        addUnderscores(associatedEntityTable), addUnderscores(propertyName)));
   }

   @Override
   public String logicalColumnName(String columnName, String propertyName) {
      return abbreviateName(super.logicalColumnName(addUnderscores(columnName), addUnderscores(propertyName)));
   }

   @Override
   public String propertyToColumnName(String propertyName) {
      return renamePropertyKeywords(abbreviateName(super.propertyToColumnName(addUnderscores(propertyName))));
   }

   public String renamePropertyKeywords(String name) {
      String key = name.toLowerCase();
      if (propertyKeywordMap.containsKey(key))
         return propertyKeywordMap.get(key);
      return name;
   }

   public static String abbreviateName(String someName) {
      if (someName.length() <= MAX_LENGTH)
         return someName;

      String[] tokens = splitName(someName);
      shortenName(someName, tokens);

      return assembleResults(tokens);
   }

   private static String[] splitName(String someName) {
      StringTokenizer toki = new StringTokenizer(someName, "_"); //$NON-NLS-1$
      String[] tokens = new String[toki.countTokens()];
      int i = 0;
      while (toki.hasMoreTokens()) {
         tokens[i] = toki.nextToken();
         i++;
      }
      return tokens;
   }

   private static void shortenName(String someName, String[] tokens) {
      int currentLength = someName.length();
      while (currentLength > MAX_LENGTH) {
         int tokenIndex = getIndexOfLongest(tokens);
         String oldToken = tokens[tokenIndex];
         tokens[tokenIndex] = abbreviate(oldToken);
         currentLength -= oldToken.length() - tokens[tokenIndex].length();
      }
   }

   private static String assembleResults(String[] tokens) {
      StringBuilder result = new StringBuilder(tokens[0]);
      for (int j = 1; j < tokens.length; j++) {
         result.append('_').append(tokens[j]); // $NON-NLS-1$
      }
      return result.toString();
   }

   private static String abbreviate(String token) {
      final String VOWELS = "AEIOUaeiou"; //$NON-NLS-1$
      boolean vowelFound = false;
      for (int i = token.length() - 1; i >= 0; i--) {
         if (!vowelFound)
            vowelFound = VOWELS.contains(String.valueOf(token.charAt(i)));
         else if (!VOWELS.contains(String.valueOf(token.charAt(i))))
            return token.substring(0, i + 1);
      }
      return ""; //$NON-NLS-1$
   }

   private static int getIndexOfLongest(String[] tokens) {
      int maxLength = 0;
      int index = -1;
      for (int i = 0; i < tokens.length; i++) {
         String string = tokens[i];
         if (maxLength < string.length()) {
            maxLength = string.length();
            index = i;
         }
      }
      return index;
   }

   @Override
   public String classToTableName(String aClassName) {
      String name = aClassName.startsWith(TABLE_PREFIX) ? ""
            : TABLE_PREFIX + abbreviateName(super.classToTableName(addUnderscores(aClassName)));
      name = name.toUpperCase();

      return name;
   }

   @Override
   public String tableName(String tableName) {
      if (!tableName.startsWith(TABLE_PREFIX))
         return TABLE_PREFIX + tableName;
      return tableName;
   }
}
