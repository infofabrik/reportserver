package net.datenwerke.scheduler.service.scheduler.helper;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;

public class SkipJobExecution implements VetoJobExecution {

	private final String explanation;
	
	public SkipJobExecution(String explanation) {
		super();
		this.explanation = explanation;
	}

	@Override
	public VetoJobExecutionMode getMode() {
		return VetoJobExecutionMode.SKIP;
	}

	@Override
	public String getExplanation() {
		return explanation;
	}

	@Override
	public void updateTrigger(AbstractJob job, ExecutionLogEntry logEntry) {

	}

	@Override
	public RetryTimeUnit getRetryUnit() {
		return null;
	}

	@Override
	public int getRetryAmount() {
		return 0;
	}

}
