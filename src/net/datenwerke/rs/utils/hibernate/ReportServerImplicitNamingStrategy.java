package net.datenwerke.rs.utils.hibernate;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitBasicColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitJoinColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.model.naming.ImplicitPrimaryKeyJoinColumnNameSource;

public class ReportServerImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl implements ImplicitNamingStrategy {

	private static final long serialVersionUID = -1865262119783291727L;
	
	private static final int MAX_LENGTH = 27;
	
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
	
	@Override
	public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source) {
		return Identifier.toIdentifier(abbreviateName(addUnderscores(renamePropertyKeywords(source.getAttributePath().getProperty()))));
	}
	
	
	@Override
	public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource source) {
		final String name;
		if ( source.getNature() == ImplicitJoinColumnNameSource.Nature.ELEMENT_COLLECTION
				|| source.getAttributePath() == null ) {
			name = abbreviateName(addUnderscores(source.getReferencedTableName().getText() )
					+ "_"
					+ unrenamePropertyKeywords(source.getReferencedColumnName().getText()));
		} else {
			name = abbreviateName(addUnderscores(transformAttributePath( source.getAttributePath() ))
					+ "_"
					+ unrenamePropertyKeywords(source.getReferencedColumnName().getText()));
		}
		
		return toIdentifier( name, source.getBuildingContext() );
	}
	
	
	
	@Override
	public Identifier determinePrimaryKeyJoinColumnName(ImplicitPrimaryKeyJoinColumnNameSource source) {
		return Identifier.toIdentifier(abbreviateName(addUnderscores(unrenamePropertyKeywords(source.getReferencedPrimaryKeyColumnName().getText()))));
	}
	
	
	private String renamePropertyKeywords(String name) {
    	String key = name.toLowerCase();
    	if(propertyKeywordMap.containsKey(key))
    		return propertyKeywordMap.get(key);
		return name;
	}
	
	private String unrenamePropertyKeywords(String name) {
    	for(Map.Entry<String, String> e : propertyKeywordMap.entrySet()){
    		if(e.getValue().equalsIgnoreCase(name))
    			return e.getKey();
    	}
		return name;
	}
	
	private String addUnderscores(String name) {
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
	
	  private boolean isMultipleUpperToLower(StringBuffer buf, int i) {
	        return i > 1 && Character.isUpperCase(buf.charAt(i - 1))
	                && Character.isUpperCase(buf.charAt(i - 2))
	                && Character.isLowerCase(buf.charAt(i));
	    }

	    private boolean isLowerToUpper(StringBuffer buf, int i) {
	        return Character.isLowerCase(buf.charAt(i - 1))
	                && Character.isUpperCase(buf.charAt(i));
	    }
	    
	    public  String abbreviateName(String someName) {
	        if (someName.length() <= MAX_LENGTH)
	            return someName;

	        String[] tokens = splitName(someName);
	        shortenName(someName, tokens);

	        return assembleResults(tokens);
	    }

	    private  String[] splitName(String someName) {
	        StringTokenizer toki = new StringTokenizer(someName, "_"); //$NON-NLS-1$
	        String[] tokens = new String[toki.countTokens()];
	        int i = 0;
	        while (toki.hasMoreTokens()) {
	            tokens[i] = toki.nextToken();
	            i++;
	        }
	        return tokens;
	    }

	    private  void shortenName(String someName, String[] tokens) {
	        int currentLength = someName.length();
	        while (currentLength > MAX_LENGTH) {
	            int tokenIndex = getIndexOfLongest(tokens);
	            String oldToken = tokens[tokenIndex];
	            tokens[tokenIndex] = abbreviate(oldToken);
	            currentLength -= oldToken.length() - tokens[tokenIndex].length();
	        }
	    }

	    private  String assembleResults(String[] tokens) {
	        StringBuilder result = new StringBuilder(tokens[0]);
	        for (int j = 1; j < tokens.length; j++) {
	            result.append('_').append(tokens[j]); //$NON-NLS-1$
	        }
	        return result.toString();
	    }

	    private  String abbreviate(String token) {
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

	    private  int getIndexOfLongest(String[] tokens) {
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

}
