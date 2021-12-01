package net.datenwerke.rs.condition.service.condition;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow;
import net.datenwerke.rs.condition.client.condition.dto.SimpleCondition;
import net.datenwerke.rs.condition.client.condition.Condition;
import net.datenwerke.rs.condition.service.condition.entity.Condition__;
import net.datenwerke.rs.condition.service.condition.entity.ReportCondition;
import net.datenwerke.rs.condition.service.condition.hooks.ConditionProviderHook;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.eventlogger.annotations.FireMergeEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FirePersistEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;
import net.datenwerke.security.service.usermanager.entities.User;

public class ConditionServiceImpl implements ConditionService {

	private final Provider<EntityManager> entityManagerFactory;
	private final Provider<SimpleJuel> juelProvider;
	private final Provider<AuthenticatorService> authServiceProvider;
	private final ReportExecutorService reportExecutor;
	private final HookHandlerService hookHandler;
	
	@Inject
	public ConditionServiceImpl(
		Provider<EntityManager> entityManagerFactory,
		Provider<SimpleJuel> juelProvider,
		Provider<AuthenticatorService> authServiceProvider, 
		ReportExecutorService reportExecutor,
		HookHandlerService hookHandler
		) {
		
		/* store object */
		this.entityManagerFactory = entityManagerFactory;
		this.juelProvider = juelProvider;
		this.authServiceProvider = authServiceProvider;
		this.reportExecutor = reportExecutor;
		this.hookHandler = hookHandler;
	}

	@Override
	@FirePersistEntityEvents
	public void persist(ReportCondition condition) {
		entityManagerFactory.get().persist(condition);
	}

	@Override
	@FireMergeEntityEvents
	public ReportCondition merge(ReportCondition condition) {
		condition = entityManagerFactory.get().merge(condition);
		return condition;
	}

	@Override
	@FireRemoveEntityEvents
	public void remove(ReportCondition condition) {
		EntityManager em = entityManagerFactory.get();
		condition = em.find(ReportCondition.class, condition.getId());
		if(null != condition)
			em.remove(condition);
	}
	
	@Override
	@QueryByAttribute(where=Condition__.report)
	public List<ReportCondition> getReportConditionsFor(TableReport report) {
		return null;
	}

	@QueryById
	public ReportCondition getReportConditionById(Long id) {
		return null; // magic
	}

	@SimpleQuery
	public List<ReportCondition> getReportConditions() {
		return null; // magic
	}

	@Override
	public List<String> getReplacementsFor(ReportCondition condition) {
		TableReport report = (TableReport) condition.getReport();
		
		if(null == report)
			throw new IllegalArgumentException();
		
		try {
			List<String> result = new ArrayList<String>();
			
			for(Column col : report.getColumns())
				result.add(col.getName());
			
			return result;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public boolean executeCondition(Condition condition, String expression, User user) throws ReportExecutorException {
		if(condition instanceof ReportCondition)
			return executeCondition((ReportCondition)condition, expression, user);
		else if(condition instanceof SimpleCondition){
			for(ConditionProviderHook provider : hookHandler.getHookers(ConditionProviderHook.class))
				if(provider.consumes(condition.getKey()))
					return provider.execute(condition.getKey(), expression, user, null);
			
			throw new IllegalArgumentException("Could not find provider for condition: " + condition.getKey());
		}
		throw new IllegalArgumentException("Expected Simple- or ReportCondition");
	}
	
	protected boolean executeCondition(ReportCondition condition, String expression, User user) throws ReportExecutorException {
		if(null == expression)
			return false;
		
		TableReport report = condition.getReport();
		
		if(null == report)
			throw new IllegalArgumentException();
		
		expression = "${" + expression + "}";
		
		SimpleJuel juel = juelProvider.get();
		RSTableModel data = (RSTableModel) reportExecutor.execute(report, user, ReportExecutorService.OUTPUT_FORMAT_TABLE);
		if(data.getRowCount() < 1)
			throw new IllegalArgumentException("The report should return at least one row");
		RSTableRow row = data.getData().get(0);
		for(int i = 0; i < data.getColumnCount(); i++)
			juel.addReplacement(data.getColumnName(i), row.getAt(i));
		
		Object result = juel.parseAsObject(expression);
		if(null == result)
			return false;
		
		if(! (result instanceof Boolean))
			return true;
		
		return (Boolean) result;
	}
	
	@Override
	public boolean executeCondition(Condition condition, String expression) throws ReportExecutorException {
		User currentUser = authServiceProvider.get().getCurrentUser();
		return executeCondition(condition, expression, currentUser);
	}
	
	@Override
	public List<SimpleCondition> getSimpleConditionsFor(Report report){
		List<SimpleCondition> conditions = new ArrayList<>();
		
		for(ConditionProviderHook hooker : hookHandler.getHookers(ConditionProviderHook.class)){
			SimpleCondition cond = hooker.provideConditionFor(report);
			if(null != cond)
				conditions.add(cond);
		}
		
		return conditions;
	}

}
