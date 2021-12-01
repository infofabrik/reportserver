package net.datenwerke.rs.condition.service.condition.hookers;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.condition.client.condition.Condition;
import net.datenwerke.rs.condition.client.condition.dto.ConditionFailureStrategy;
import net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto;
import net.datenwerke.rs.condition.client.condition.dto.ScheduleConditionDto;
import net.datenwerke.rs.condition.client.condition.dto.ScheduleConditionList;
import net.datenwerke.rs.condition.client.condition.dto.SimpleCondition;
import net.datenwerke.rs.condition.service.condition.ConditionModule;
import net.datenwerke.rs.condition.service.condition.ConditionService;
import net.datenwerke.rs.condition.service.condition.entity.ReportCondition;
import net.datenwerke.rs.condition.service.condition.hooks.ConditionProviderHook;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.RetryTimeUnitDto;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;
/**
 * 
 *
 */
public class ScheduleConditionConfigProvider implements
		ScheduleConfigProviderHook {

	private final DtoService dtoService;
	private final SecurityService securityService;
	private final ConditionService conditionService;
	private final HookHandlerService hookHandler;
	
	@Inject
	public ScheduleConditionConfigProvider(
		DtoService dtoService,
		SecurityService securityService,
		ConditionService conditionService,
		HookHandlerService hookHandler
		) {
		
		/*  store objects */
		this.dtoService = dtoService;
		this.securityService = securityService;
		this.conditionService = conditionService;
		this.hookHandler = hookHandler;
	}

	@Override
	public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
			throws InvalidConfigurationException {
		ScheduleConditionList list = scheduleDTO.getAdditionalInfo(ScheduleConditionList.class);
		if(null != list && list.getConditionList().size() > 0){
			int i = 0;
			for(ScheduleConditionDto conditionDto :  list.getConditionList()){
				Condition condition = conditionDto.getCondition();
				
				if(condition instanceof ReportConditionDto){
					ReportCondition reportCondition = (ReportCondition) dtoService.loadPoso(condition);
					Report report = reportCondition.getReport();
					if(null == report || ! (report instanceof TableReport))
						throw new InvalidConfigurationException("could not load report");
					
					securityService.assertRights(report, Read.class, Execute.class);
					
					job.setProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_KEY, ConditionModule.PROPERTY_POSTFIX_CONDITION_REPORT_COND_KEY);
					job.setProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_ID, reportCondition.getId().toString());
					job.setProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_EXPRESSION, conditionDto.getExpression());
				} else if(condition instanceof SimpleCondition){
					job.setProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_KEY, condition.getKey());
					if(condition.hasExpression())
						job.setProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_EXPRESSION, conditionDto.getExpression());
				} else 
					throw new InvalidConfigurationException("Expected Simple- or ReportCondition.");
				
				i++;
			}
			
			ConditionFailureStrategy strat = list.getFailureStrategy();
			if(null != strat)
				job.setProperty(ConditionModule.PROPERTY_FAILURE_STRATEGY, String.valueOf(strat.ordinal()));
			RetryTimeUnitDto unit = list.getRetryStrategyUnit();
			if(null != unit)
				job.setProperty(ConditionModule.PROPERTY_RETRY_STRATEGY_UNIT, String.valueOf(unit.ordinal()));
			
			int amount = list.getRetryStrategyAmount();
			job.setProperty(ConditionModule.PROPERTY_RETRY_STRATEGY_AMOUNT, String.valueOf(amount));
		}
	}

	private void adaptScheduleDefinitionForCondition(ReportExecuteJob job, List<ScheduleConditionDto> list,
			int i) {
		if (job.hasProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_ID)) {
			ScheduleConditionDto scheduleCond = new ScheduleConditionDto();
			Long conditionId = Long.valueOf(
					job.getProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_ID)
							.getValue());
			ReportCondition condition = conditionService.getReportConditionById(conditionId);
			Report report = condition.getReport();
			if (null == report || !(report instanceof TableReport))
				return;

			if (!securityService.checkRights(report, Read.class, Execute.class))
				return;

			scheduleCond.setCondition((ReportConditionDto) dtoService.createDto(condition));
			scheduleCond.setExpression(
					job.getProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_EXPRESSION)
							.getValue());
			list.add(scheduleCond);
		}
	}
	
	private void adaptScheduleDefinitionForPredefinedCondition(ReportExecuteJob job, List<ScheduleConditionDto> list,
			int i) {
		if (job.hasProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_KEY) && !job
				.hasProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_ID)) {
			String key = job.getProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_KEY).getValue();
			for(ConditionProviderHook provider : hookHandler.getHookers(ConditionProviderHook.class)){
				if(provider.consumes(key)){
					SimpleCondition condition = provider.provideConditionFor(job.getReport());
					
					Report report = job.getReport();
					if(null == report || ! (report instanceof TableReport))
						continue;
					if(! securityService.checkRights(report, Read.class, Execute.class))
						continue;
					
					ScheduleConditionDto scheduleCondition = new ScheduleConditionDto();
					scheduleCondition.setCondition(condition);
					list.add(scheduleCondition);
				}
			}
		}
	}

	@Override
	public void adaptScheduleDefinition(ReportScheduleDefinition rsd,
			ReportExecuteJob job) {
		ScheduleConditionList conditionList = new ScheduleConditionList();
		
		int i = 0;
		List<ScheduleConditionDto> list = new ArrayList<ScheduleConditionDto>();
		
		while (job.hasProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_ID)
				|| job.hasProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_KEY)) {

			if (job.hasProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_ID)) {
				adaptScheduleDefinitionForCondition(job, list, i);
			} else if (job.hasProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_KEY)) {
				adaptScheduleDefinitionForPredefinedCondition(job, list, i);
			}
			i++;
		}
		
		if(! list.isEmpty()){
			if(job.hasProperty(ConditionModule.PROPERTY_FAILURE_STRATEGY)){
				int ordinal = Integer.valueOf(job.getProperty(ConditionModule.PROPERTY_FAILURE_STRATEGY).getValue());
				for(ConditionFailureStrategy strat : ConditionFailureStrategy.values()){
					if(strat.ordinal() == ordinal){
						conditionList.setFailureStrategy(strat);
						break;
					}
				}
			}
			if(job.hasProperty(ConditionModule.PROPERTY_RETRY_STRATEGY_UNIT)){
				int ordinal = Integer.valueOf(job.getProperty(ConditionModule.PROPERTY_RETRY_STRATEGY_UNIT).getValue());
				for(RetryTimeUnitDto unit : RetryTimeUnitDto.values()){
					if(unit.ordinal() == ordinal){
						conditionList.setRetryStrategyUnit(unit);
						break;
					}
				}
				
			}
			if(job.hasProperty(ConditionModule.PROPERTY_RETRY_STRATEGY_AMOUNT)){
				int amount = Integer.parseInt(job.getProperty(ConditionModule.PROPERTY_RETRY_STRATEGY_AMOUNT).getValue());
				conditionList.setRetryStrategyAmount(amount);
			}
			
			conditionList.setConditionList(list);
			rsd.addAdditionalInfo(conditionList);
		}
	}

}
