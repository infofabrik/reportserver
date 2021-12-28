package net.datenwerke.gf.client.theme;

import com.google.gwt.user.client.ui.HTML;

import net.datenwerke.gf.client.theme.dto.ThemeUiConfig;

public interface ThemeUiService {

   void setThemeConfig(ThemeUiConfig result);

   ThemeUiConfig getThemeConfig();

   HTML getHeaderLogo();

   HTML getLoginLogo();

}
