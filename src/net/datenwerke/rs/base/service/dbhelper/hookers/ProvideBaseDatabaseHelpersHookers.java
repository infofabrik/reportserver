package net.datenwerke.rs.base.service.dbhelper.hookers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.db.CockroachDB;
import net.datenwerke.rs.base.service.dbhelper.db.CrateDB;
import net.datenwerke.rs.base.service.dbhelper.db.Exasol;
import net.datenwerke.rs.base.service.dbhelper.db.Firebird;
import net.datenwerke.rs.base.service.dbhelper.db.GoogleBigQuery;
import net.datenwerke.rs.base.service.dbhelper.db.H2;
import net.datenwerke.rs.base.service.dbhelper.db.HSQL;
import net.datenwerke.rs.base.service.dbhelper.db.Incorta;
import net.datenwerke.rs.base.service.dbhelper.db.MariaDB;
import net.datenwerke.rs.base.service.dbhelper.db.MonetDB;
import net.datenwerke.rs.base.service.dbhelper.db.MySQL;
import net.datenwerke.rs.base.service.dbhelper.db.PostgreSQL;
import net.datenwerke.rs.base.service.dbhelper.db.Redshift;
import net.datenwerke.rs.base.service.dbhelper.db.SQLite;
import net.datenwerke.rs.base.service.dbhelper.db.SapHana;
import net.datenwerke.rs.base.service.dbhelper.db.SybaseJConnect;
import net.datenwerke.rs.base.service.dbhelper.db.Vertica;
import net.datenwerke.rs.base.service.dbhelper.db.YugabyteDB;
import net.datenwerke.rs.base.service.dbhelper.db.athena.Athena;
import net.datenwerke.rs.base.service.dbhelper.db.db2.Db2i;
import net.datenwerke.rs.base.service.dbhelper.db.db2.Db2z;
import net.datenwerke.rs.base.service.dbhelper.db.derby.Derby;
import net.datenwerke.rs.base.service.dbhelper.db.informix.Informix;
import net.datenwerke.rs.base.service.dbhelper.db.mssql.SqlServer;
import net.datenwerke.rs.base.service.dbhelper.db.mssql.SqlServerJTDS;
import net.datenwerke.rs.base.service.dbhelper.db.oracle.Oracle;
import net.datenwerke.rs.base.service.dbhelper.db.teradata.Teradata;
import net.datenwerke.rs.base.service.dbhelper.hooks.DatabaseHelperProviderHook;

public class ProvideBaseDatabaseHelpersHookers implements DatabaseHelperProviderHook {

   private final List<DatabaseHelper> helpers = new ArrayList<DatabaseHelper>();

   @Inject
   public ProvideBaseDatabaseHelpersHookers(
         Db2z db2z, 
         H2 h2, 
         Firebird firebird, 
         MariaDB mariaDb, 
         SqlServer mssql,
         SqlServerJTDS mssqlJtds,
         MySQL mysql,
         MonetDB monetdb, 
         Oracle oracle, 
         HSQL hsql, 
         PostgreSQL postgres, 
         Informix informix,
         Vertica vertica, 
         SybaseJConnect sybase, 
         GoogleBigQuery googleBigQuery, 
         Redshift amazonRedshift,
         Teradata teradata,
         SQLite sqlite,
         YugabyteDB yugabyteDb,
         CockroachDB cockroachDb,
         SapHana saphana,
         Exasol exasol,
         Incorta incorta,
         CrateDB crateDb,
         Athena athena,
         Derby derby,
         Db2i db2i
         ) {

      helpers.add(db2z);
      helpers.add(h2);
      helpers.add(mssql);
      helpers.add(mssqlJtds);
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
      helpers.add(yugabyteDb);
      helpers.add(cockroachDb);
      helpers.add(saphana);
      helpers.add(exasol);
      helpers.add(incorta);
      helpers.add(crateDb);
      helpers.add(athena);
      helpers.add(derby);
      helpers.add(db2i);
   }

   @Override
   public Collection<DatabaseHelper> provideDatabaseHelpers() {
      return helpers;
   }

}
