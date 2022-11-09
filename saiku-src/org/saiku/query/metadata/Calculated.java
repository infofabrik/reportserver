// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query.metadata;

import java.util.Map;

public interface Calculated
{
    String getFormula();
    
    Map<String, String> getFormatProperties();
    
    String getUniqueName();
    
    int getSolveOrder();
}
