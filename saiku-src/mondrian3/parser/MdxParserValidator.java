/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2010-2011 Pentaho
// All Rights Reserved.
*/
package mondrian3.parser;

import mondrian3.olap.*;
import mondrian3.server.Statement;

import java.util.List;

/**
 * Parses and validates an MDX statement.
 *
 * <p>NOTE: API is subject to change. Current implementation is backwards
 * compatible with the old parser based on JavaCUP.
 *
 * @author jhyde
 */
public interface MdxParserValidator {

    /**
      * Parses a string to create a {@link mondrian3.olap.Query}.
      * Called only by {@link mondrian3.olap.ConnectionBase#parseQuery}.
      */
    QueryPart parseInternal(
        Statement statement,
        String queryString,
        boolean debug,
        FunTable funTable,
        boolean strictValidation);

    Exp parseExpression(
        Statement statement,
        String queryString,
        boolean debug,
        FunTable funTable);

    interface QueryPartFactory {

        /**
         * Creates a {@link mondrian3.olap.Query} object.
         * Override this function to make your kind of query.
         */
        Query makeQuery(
            Statement statement,
            Formula[] formulae,
            QueryAxis[] axes,
            String cube,
            Exp slicer,
            QueryPart[] cellProps,
            boolean strictValidation);

        /**
         * Creates a {@link mondrian3.olap.DrillThrough} object.
         */
        DrillThrough makeDrillThrough(
            Query query,
            int maxRowCount,
            int firstRowOrdinal,
            List<Exp> returnList);

        /**
         * Creates an {@link mondrian3.olap.Explain} object.
         */
        Explain makeExplain(
            QueryPart query);
    }
}

// End MdxParserValidator.java
