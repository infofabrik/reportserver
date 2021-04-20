package net.sf.jxls.tag;

/**
 * Base class for {@link Tag} interface implementation
 * @author Leonid Vysochyn
 */
public abstract class BaseTag implements Tag{
    protected String name;
    protected TagContext tagContext;

    public String getName() {
        return name;
    }

    public String toString() {
        return "<" + getName() + ">";
    }

    public void init(TagContext context) {
        this.tagContext = context;
    }

    public TagContext getTagContext() {
        return tagContext;
    }

}
