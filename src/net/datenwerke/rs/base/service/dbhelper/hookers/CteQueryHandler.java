package net.datenwerke.rs.base.service.dbhelper.hookers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.datenwerke.rs.base.service.dbhelper.hooks.StatementModificationHook;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class CteQueryHandler implements StatementModificationHook {

   private static final Pattern p = Pattern.compile("(?sm)/\\*<rs:cte>\\*/(.*)/\\*</rs:cte>\\*/");

   @Override
   public String modifyStatement(String stmt, QueryBuilder queryBuilder) {
      if (!stmt.contains("/*<rs:cte>*/"))
         return null;

      Matcher matcher = p.matcher(stmt);
      if (matcher.find())
         stmt = matcher.group(0) + matcher.replaceAll("");

      return stmt;
   }

}