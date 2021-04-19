package net.datenwerke.rs.utils.misc;

public class StringEscapeUtils {

    public static String escapeXml(String text) {
	    if(null == text)
	    	return null;
	    return text.replace( "&", "&amp;" )
	               .replace( "<", "&lt;" )
	               .replace( ">", "&gt;" )
	               ;
	};
	
	public static String unescapeXml(String text) {
	    if(null == text)
	    	return null;
	    return text.replace( "&lt;", "<")
	               .replace( "&gt;", ">")
	               .replace( "&amp;", "&" )
	               ;      
	};
}
