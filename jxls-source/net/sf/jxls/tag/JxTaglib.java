package net.sf.jxls.tag;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines mapping between java class files and jx tag names
 * @author Leonid Vysochyn
 */
public class JxTaglib implements TagLib {
    static String[] tagName = new String[]{ "forEach", "if", "outline", "out"};
    static String[] tagClassName = new String[]{ "net.sf.jxls.tag.ForEachTag", "net.sf.jxls.tag.IfTag", "net.sf.jxls.tag.OutlineTag", "net.sf.jxls.tag.OutTag" };

    static Map tags = new HashMap();

    static{
        for (int i = 0; i < tagName.length; i++) {
            String key = tagName[i];
            String value = tagClassName[i];
            tags.put( key, value );
        }
    }

    public Map getTags(){
        return tags;
    }
}