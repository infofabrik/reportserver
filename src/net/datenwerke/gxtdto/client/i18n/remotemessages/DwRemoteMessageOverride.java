package net.datenwerke.gxtdto.client.i18n.remotemessages;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gwt.i18n.client.Messages;

@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.TYPE})
@Inherited
public @interface DwRemoteMessageOverride {
	public Class<? extends Messages> value();
}
