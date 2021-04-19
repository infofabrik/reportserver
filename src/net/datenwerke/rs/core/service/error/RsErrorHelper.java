package net.datenwerke.rs.core.service.error;

import com.google.inject.ImplementedBy;

@ImplementedBy(RsErrorHelperImpl.class)
public interface RsErrorHelper {

	public String getHtmlErrorPage(String headline, String msg, String stacktrace);

	public String getHtmlErrorPage(String headline, Exception e);
}
