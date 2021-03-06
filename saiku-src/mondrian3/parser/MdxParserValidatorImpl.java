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

/**
 * Default implementation of {@link mondrian3.parser.MdxParserValidator}.
 *
 * @author jhyde
 */
public class MdxParserValidatorImpl implements MdxParserValidator {
    /**
     * Creates a MdxParserValidatorImpl.
     */
    public MdxParserValidatorImpl() {
    }

    public QueryPart parseInternal(
        Statement statement,
        String queryString,
        boolean debug,
        FunTable funTable,
        boolean strictValidation)
    {
        return new Parser().parseInternal(
            new Parser.FactoryImpl(),
            statement, queryString, debug, funTable, strictValidation);
    }

    public Exp parseExpression(
        Statement statement,
        String queryString,
        boolean debug,
        FunTable funTable)
    {
        return new Parser().parseExpression(
            new Parser.FactoryImpl(),
            statement, queryString, debug, funTable);
    }
}

// End MdxParserValidatorImpl.java
