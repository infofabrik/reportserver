package net.datenwerke.gf.service.theme;

import java.util.Map;

import com.google.inject.ImplementedBy;

import net.datenwerke.gf.client.theme.dto.ThemeUiConfig;

@ImplementedBy(ThemeServiceImpl.class)
public interface ThemeService {

   String getTheme();

   ThemeUiConfig loadUiConfig();

   byte[] getLogo();

   Map<String, String> getColorMap();

}
