// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query.util;

import org.saiku.query.IQuerySet;
import java.util.Collection;
import org.saiku.query.ISortableQuerySet;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class QueryUtil
{
    public static List<String> parseParameters(final String expression) {
        final List<String> parameterNames = new ArrayList<String>();
        if (StringUtils.isNotBlank(expression)) {
            final char[] exArray = expression.toCharArray();
            for (int i = 0; i < exArray.length - 2; ++i) {
                if (exArray[i] == '$' && exArray[i + 1] == '{') {
                    final StringBuffer sb = new StringBuffer();
                    for (i += 2; i < exArray.length && exArray[i] != '}'; ++i) {
                        sb.append(exArray[i]);
                    }
                    final String newParam = sb.toString();
                    if (StringUtils.isNotBlank(newParam)) {
                        parameterNames.add(newParam);
                    }
                }
            }
        }
        return parameterNames;
    }
    
    public static List<String> retrieveSortableSetParameters(final ISortableQuerySet sqs) {
        final List<String> parameterNames = new ArrayList<String>();
        final String sortEval = sqs.getSortEvaluationLiteral();
        final List<String> sortParameters = parseParameters(sortEval);
        parameterNames.addAll(sortParameters);
        parameterNames.addAll(retrieveSetParameters(sqs));
        return parameterNames;
    }
    
    public static List<String> retrieveSetParameters(final IQuerySet qs) {
        final List<String> parameterNames = new ArrayList<String>();
        final String mdx = qs.getMdxSetExpression();
        final List<String> mdxParameters = parseParameters(mdx);
        parameterNames.addAll(mdxParameters);
        return parameterNames;
    }
}
