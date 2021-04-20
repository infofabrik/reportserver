package net.sf.jxls.tag;

/**
 * @author Leonid Vysochyn
 */
public class AttributeInfo {
    String name;
    String required;
    String rtexpvalue;
    String type;


    public AttributeInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getRtexpvalue() {
        return rtexpvalue;
    }

    public void setRtexpvalue(String rtexpvalue) {
        this.rtexpvalue = rtexpvalue;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
