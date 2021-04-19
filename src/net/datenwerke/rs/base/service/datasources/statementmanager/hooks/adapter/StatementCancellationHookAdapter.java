package net.datenwerke.rs.base.service.datasources.statementmanager.hooks.adapter;

import java.sql.Connection;
import java.sql.Statement;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.service.datasources.statementmanager.hooks.StatementCancellationHook;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class StatementCancellationHookAdapter implements StatementCancellationHook {

	@Override
	public boolean cancelStatement(Statement statement, Connection connection, String statementId){
		return cancelStatement(statement);
	}

	/* to be removed on RS 3.1 */
	@Deprecated
	public boolean cancelStatement(Statement statement)  {
		return false;
	}

	@Override
	public boolean consumes(Statement statement, Connection connection)  {
		return consumes(statement);
	}

	/* to be removed on RS 3.1 */
	@Deprecated
	protected boolean consumes(Statement statement) {
		return false;
	}

	@Override
	public boolean overridesDefaultMechanism(Statement statement, Connection connection)  {
		return overridesDefaultMechanism(statement);
	}

	/* to be removed on RS 3.1 */
	@Deprecated
	protected boolean overridesDefaultMechanism(Statement statement) {
		return false;
	}



}
