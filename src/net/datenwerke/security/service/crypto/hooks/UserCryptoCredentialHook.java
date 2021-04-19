package net.datenwerke.security.service.crypto.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.security.service.crypto.CryptoCredentials;

@HookConfig
public interface UserCryptoCredentialHook extends Hook {
	public CryptoCredentials getUserCryptoCredentials(String email);
}
