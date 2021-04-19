package net.datenwerke.async.helpers;

public interface TransactionalRunnableFactory {

	public TransactionalRunnable create(Runnable runnable);
}
