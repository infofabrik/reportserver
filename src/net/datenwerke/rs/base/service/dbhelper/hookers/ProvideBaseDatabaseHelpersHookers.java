package net.datenwerke.rs.base.service.dbhelper.hookers;

import java.util.ArrayList;
import java.util.Collection;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.db.AmazonRedshift;
import net.datenwerke.rs.base.service.dbhelper.db.Firebird;
import net.datenwerke.rs.base.service.dbhelper.db.GoogleBigQuery;
import net.datenwerke.rs.base.service.dbhelper.db.H2;
import net.datenwerke.rs.base.service.dbhelper.db.HSQL;
import net.datenwerke.rs.base.service.dbhelper.db.MariaDB;
import net.datenwerke.rs.base.service.dbhelper.db.MonetDB;
import net.datenwerke.rs.base.service.dbhelper.db.MySQL;
import net.datenwerke.rs.base.service.dbhelper.db.PostgreSQL;
import net.datenwerke.rs.base.service.dbhelper.db.SQLite;
import net.datenwerke.rs.base.service.dbhelper.db.Sybase;
import net.datenwerke.rs.base.service.dbhelper.db.Vertica;
import net.datenwerke.rs.base.service.dbhelper.db.db2.DB2;
import net.datenwerke.rs.base.service.dbhelper.db.informix.Informix;
import net.datenwerke.rs.base.service.dbhelper.db.mssql.MsSQL;
import net.datenwerke.rs.base.service.dbhelper.db.oracle.Oracle;
import net.datenwerke.rs.base.service.dbhelper.db.teradata.Teradata;
import net.datenwerke.rs.base.service.dbhelper.hooks.DatabaseHelperProviderHook;

public class ProvideBaseDatabaseHelpersHookers implements DatabaseHelperProviderHook {

   private final Collection<DatabaseHelper> helpers;

   @Inject
   public ProvideBaseDatabaseHelpersHookers(
         DB2 db2, H2 h2, 
         Firebird firebird, 
         MariaDB mariaDb, 
         MsSQL mssql,
         MySQL mysql,
         MonetDB monetdb, 
         Oracle oracle, 
         HSQL hsql, 
         PostgreSQL postgres, 
         Informix informix,
         Vertica vertica, 
         Sybase sybase, 
         GoogleBigQuery googleBigQuery, 
         AmazonRedshift amazonRedshift,
         Teradata teradata,
         SQLite sqlite
         ) {

      helpers = new ArrayList<DatabaseHelper>();
      helpers.add(db2);
      helpers.add(h2);
      helpers.add(mssql);
      helpers.add(mysql);
      helpers.add(mariaDb);
      helpers.add(monetdb);
      helpers.add(oracle);
      helpers.add(firebird);
      helpers.add(hsql);
      helpers.add(postgres);
      helpers.add(informix);
      helpers.add(vertica);
      helpers.add(sybase);
      helpers.add(googleBigQuery);
      helpers.add(amazonRedshift);
      helpers.add(teradata);
      helpers.add(sqlite);
   }

   @Override
   public Collection<DatabaseHelper> provideDatabaseHelpers() {
      return helpers;
   }

}
