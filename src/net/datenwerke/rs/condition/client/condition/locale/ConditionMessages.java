package net.datenwerke.rs.condition.client.condition.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ConditionMessages extends Messages {

   public final static ConditionMessages INSTANCE = GWT.create(ConditionMessages.class);

   String expressionLabel();

   String addPredefinedConditionLabel();

   String removeConditionLabel();

   String removeAllConditionLabel();

   String conditionPageHeadline();

   String conditionPageDescription();

   String editConditionHeader(String name);

   String testConditionLabel();

   String removeAllConfirmHeading();

   String removeAllConfirmText();

   String addReplacementLabel();

   String schedulerPageHeadline();

   String schedulerPageDescription();

   String failureStrategyLabel();

   String skipLabel();

   String retryLabel();

   String minutesLabel();

   String hoursLabel();

   String daysLabel();

   String weeksLabel();

   String monthsLabel();

   String yearsLabel();

   String failureRetryUnitLabel();

   String failureRetryAmount();

   String addConditionHeader();

   String conditionHolds();

   String conditionFails();

   String addConditionLabel();

}
