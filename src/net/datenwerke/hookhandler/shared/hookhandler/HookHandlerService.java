package net.datenwerke.hookhandler.shared.hookhandler;

import java.util.List;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

import com.google.inject.Provider;

/**
 * 
 * 
 * 
 *
 */
public interface HookHandlerService {

	public static final int PRIORITY_LOWER = 200;
	public static final int PRIORITY_LOW = 100;
	public static final int PRIORITY_MEDIUM = 50;
	public static final int PRIORITY_HIGH = 0;
	
	/**
	 * Attaches the given hooker to the given {@link Hook} with the given priority
	 * 
	 * @param <H> can be any type extending {@link Hook}
	 * @param hook The hook to connect the hooker to
	 * @param hooker The hooker
	 * @param priority The priority
	 */
	public <H extends Hook> void attachHooker(Class<? extends H> hook, H hooker, int priority);
	
	/**
	 * Attaches the given hooker to the given {@link Hook} with a priority of <b>PRIORITY_MEDIUM</b>
	 * 
	 * @param <H> can be any type extending {@link Hook}
	 * @param hook The hook to connect the hooker to
	 * @param hooker The hooker
	 */
	public <H extends Hook> void attachHooker(Class<? extends H> hook, H hooker);
	
	/**
	 * Detaches the given hooker from the hook
	 * 
	 * @param <H> can be any type extending {@link Hook}
	 * @param hook
	 * @param hooker
	 */
	public <H extends Hook> void detachHooker(Class<? extends H> hook, H hooker);
	
	/**
	 * Attaches the given hooker to the given {@link Hook} with the given priority
	 * 
	 * @param <H> can be any type extending {@link Hook}
	 * @param hook The hook to connect the hooker to
	 * @param hookerProvider The provider for the hooker
	 * @param priority The priority
	 */
	public <H extends Hook> void attachHooker(Class<? extends H> hook, Provider<? extends H> hookerProvider, int priority);
	
	/**
	 * Attaches the given hooker to the given {@link Hook} with a priority of <b>PRIORITY_MEDIUM</b>
	 * 
	 * @param <H> can be any type extending {@link Hook}
	 * @param hook The hook to connect the hooker to
	 * @param hookerProvider The provider for the hooker
	 */
	public <H extends Hook> void attachHooker(Class<? extends H> hook, Provider<? extends H> hookerProvider);
	
	/**
	 * Detaches the given hooker from the given {@link Hook}
	 * 
	 * @param <H> can be any type extending {@link Hook}
	 * @param hook The hook to disconnect the hooker from
	 * @param hookerProvider The provider for the hooker
	 */
	public <H extends Hook> void detachHooker(Class<? extends H> hook,  Provider<? extends H> hookerProvider);
	
	/**
	 * Returns a {@link List} of all hookers which are attached to the given hook.
	 * 
	 * @param <H> can be any type extending {@link Hook}
	 * @param hook The {@link Hook} to get the attached hookers from
	 */
	public <H extends Hook> List<H> getHookers(Class<? extends H> hook);


	public HookConfiguration getConfig(Class<? extends Hook> hook);
	
	public void setConfig(HookConfiguration config);

	public <H extends Hook> List<Provider<H>> getRawHookerProviders(Class<? extends H> hook);

	public <H extends Hook> List<H> getRawHookers(Class<? extends H> hook);
}
