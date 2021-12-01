package net.datenwerke.rs.utils.localization.hooks;

import java.util.Locale;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;

@HookConfig
public interface LocaleChangedNotificationHook extends Hook{

	void localeChanged(Locale locale);

}
