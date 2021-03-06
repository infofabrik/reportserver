/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2011-2011 Pentaho
// All Rights Reserved.
*/
package mondrian3.rolap.agg;

import mondrian3.rolap.StarColumnPredicate;

/**
 * Utilities for {@link mondrian3.rolap.StarPredicate}s and
 * {@link mondrian3.rolap.StarColumnPredicate}s.
 *
 * @author jhyde
 */
public class StarPredicates {
    /**
     * Optimizes a column predicate.
     *
     * @param predicate Column predicate
     * @return Optimized predicate
     */
    public static StarColumnPredicate optimize(StarColumnPredicate predicate) {
        if (predicate instanceof ListColumnPredicate && false) {
            ListColumnPredicate listColumnPredicate =
                (ListColumnPredicate) predicate;

            switch (listColumnPredicate.getPredicates().size()) {
            case 0:
                return new LiteralStarPredicate(
                    predicate.getConstrainedColumn(), false);
            case 1:
                return listColumnPredicate.getPredicates().get(0);
            default:
                return listColumnPredicate;
            }
        }
        return predicate;
    }
}

// End StarPredicates.java
