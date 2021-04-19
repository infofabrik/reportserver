package net.datenwerke.scheduler.client.scheduler.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface SchedulerMessages extends Messages {
	
	public static SchedulerMessages INSTANCE = GWT.create(SchedulerMessages.class);
	
	String enumLabelHours();
	String enumLabelMinutes();

	String enumLabelFirst();
	String enumLabelSecond();
	String enumLabelThird();
	String enumLabelFourth();
	String enumLabelLast();
	
	String enumLabelJanuary();
	String enumLabelFebruary();
	String enumLabelMarch();
	String enumLabelApril();
	String enumLabelMay();
	String enumLabelJune();
	String enumLabelJuly();
	String enumLabelAugust();
	String enumLabelSeptember();
	String enumLabelOctober();
	String enumLabelNovember();
	String enumLabelDecember();
	
	String enumLabelMonday();
	String enumLabelTuesday();
	String enumLabelWednesday();
	String enumLabelThursday();
	String enumLabelFriday();
	String enumLabelSaturday();
	String enumLabelSunday();
	
	String enumLabelDay();
	String enumLabelWorkingDay();
	String enumLabelWeekendDay();
	
	String enumLabelExecutionStatusInactive();
	String enumLabelExecutionStatusWaiting();
	String enumLabelExecutionStatusExecuting();
	String enumLabelExecutionStatusActions();
	String enumLabelExecutionStatusBadFailure();
	
	String enumLabelOutcomeSuccess();
	String enumLabelOutcomeFailure();
	String enumLabelOutcomeVeto();
	String enumLabelOutcomeActionVeto();
	String enumLabelOutcomeExecuting();
}
