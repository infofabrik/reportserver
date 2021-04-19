package net.datenwerke.gxtdto.client.i18n.remotemessages;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.METHOD})
@Inherited
public @interface DwRemoteMessageOpts {
	
	/**
	 * If set escaped character in the messagefile will be unescaped
	 * e.g. '' -&gt; '
	 */
	public boolean unescape() default true;

}
