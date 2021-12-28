package net.datenwerke.rs.condition.service.condition;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ConditionModule extends AbstractModule {

   public static final String PROPERTY_PREFIX = "__schedule_condition_";
   public static final String PROPERTY_POSTFIX_CONDITION_ID = "_cond_id";
   public static final String PROPERTY_POSTFIX_CONDITION_KEY = "_cond_key";
   public static final String PROPERTY_POSTFIX_EXPRESSION = "_expr";
   public static final String PROPERTY_FAILURE_STRATEGY = "__schedule_condition_f_strat";
   public static final String PROPERTY_RETRY_STRATEGY_UNIT = "__schedule_condition_retry_unit";
   public static final String PROPERTY_RETRY_STRATEGY_AMOUNT = "__schedule_condition_retry_amount";
   public static final String PROPERTY_POSTFIX_CONDITION_REPORT_COND_KEY = "__report_cond";

   @Override
   protected void configure() {
      bind(ConditionService.class).to(ConditionServiceImpl.class).in(Singleton.class);

      bind(ConditionStartup.class).asEagerSingleton();
   }

}
