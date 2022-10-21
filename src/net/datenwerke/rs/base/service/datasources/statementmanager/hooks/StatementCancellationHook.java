package net.datenwerke.rs.base.service.datasources.statementmanager.hooks;

import java.sql.Connection;
import java.sql.Statement;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;

@HookConfig
public interface StatementCancellationHook extends Hook {

   public boolean cancelStatement(Statement statement, Connection connection, String statementId);

   public boolean consumes(Statement statement, Connection connection);

   public boolean overridesDefaultMechanism(Statement statement, Connection connection);

}
