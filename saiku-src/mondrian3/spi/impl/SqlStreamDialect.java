/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2009-2009 Pentaho
// All Rights Reserved.
*/
package mondrian3.spi.impl;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementation of {@link mondrian3.spi.Dialect} for the SQLstream streaming
 * SQL system.
 *
 * @author jhyde
 * @since Mar 23, 2009
 */
public class SqlStreamDialect extends LucidDbDialect {

    public static final JdbcDialectFactory FACTORY =
        new JdbcDialectFactory(
            SqlStreamDialect.class,
            DatabaseProduct.SQLSTREAM);

    /**
     * Creates a SqlStreamDialect.
     *
     * @param connection Connection
     */
    public SqlStreamDialect(Connection connection) throws SQLException {
        super(connection);
    }
}

// End SqlStreamDialect.java
