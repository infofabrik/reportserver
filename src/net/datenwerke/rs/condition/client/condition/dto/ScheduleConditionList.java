package net.datenwerke.rs.condition.client.condition.dto;

import java.util.List;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.RetryTimeUnitDto;

public class ScheduleConditionList extends RsDto implements AdditionalScheduleInformation {

   /**
    * 
    */
   private static final long serialVersionUID = -8293294731533802491L;

   public static final String PROPERTY_CONDITION_LIST = "conditionList";
   public final static String PROPERTY_FAILURE_STRATEGY = "failureStrategy";
   public final static String PROPERTY_RETRY_STRATEGY_UNIT = "retryStrategyUnit";
   public final static String PROPERTY_RETRY_STRATEGY_AMOUNT = "retryStrategyAmount";

   RetryTimeUnitDto wl_1;
   ConditionFailureStrategy wl_2;

   private List<ScheduleConditionDto> conditionList;
   private ConditionFailureStrategy failureStrategy;
   private RetryTimeUnitDto retryStrategyUnit;
   private int retryStrategyAmount;

   public ScheduleConditionList() {
      super();
   }

   public List<ScheduleConditionDto> getConditionList() {
      return conditionList;
   }

   public void setConditionList(List<ScheduleConditionDto> conditionList) {
      this.conditionList = conditionList;
   }

   public ConditionFailureStrategy getFailureStrategy() {
      return failureStrategy;
   }

   public void setFailureStrategy(ConditionFailureStrategy failureStrategy) {
      this.failureStrategy = failureStrategy;
   }

   public RetryTimeUnitDto getRetryStrategyUnit() {
      return retryStrategyUnit;
   }

   public void setRetryStrategyUnit(RetryTimeUnitDto retryStrategyUnit) {
      this.retryStrategyUnit = retryStrategyUnit;
   }

   public int getRetryStrategyAmount() {
      return retryStrategyAmount;
   }

   public void setRetryStrategyAmount(int retryStrategyAmount) {
      this.retryStrategyAmount = retryStrategyAmount;
   }

}
