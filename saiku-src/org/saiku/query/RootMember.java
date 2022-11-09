// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query;

import org.olap4j.metadata.Member;
import org.olap4j.impl.Named;

public class RootMember implements Named
{
    private final Member member;
    private final QueryHierarchy hierarchy;
    
    public RootMember(final QueryHierarchy queryHierarchy, final Member member) {
        this.member = member;
        this.hierarchy = queryHierarchy;
    }
    
    public String getName() {
        return this.member.getName();
    }
    
    public String getUniqueName() {
        return this.member.getUniqueName();
    }
    
    public String getCaption() {
        return this.member.getCaption();
    }
    
    public String getDescription() {
        return this.member.getDescription();
    }
}
