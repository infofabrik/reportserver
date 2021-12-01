package net.datenwerke.rs.utils.eventbus;

public interface EventHandler<E extends Event> {

	void handle(E event);

}
