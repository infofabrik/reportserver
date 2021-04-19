package net.sf.jxls.tag;

import java.util.List;

/**
 * @author Leonid Vysochyn
 */
public class TagInfo {
    String name;
    String description;
    String tagClass;
    List attributes;


    public TagInfo() {
    }

    public void addAttribute(AttributeInfo attr){
        attributes.add( attr );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTagClass() {
        return tagClass;
    }

    public void setTagClass(String tagClass) {
        this.tagClass = tagClass;
    }

    public List getAttributes() {
        return attributes;
    }

    public void setAttributes(List attributes) {
        this.attributes = attributes;
    }
}
