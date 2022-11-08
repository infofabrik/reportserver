package net.datenwerke.rs.saiku.service.saiku;

import com.google.inject.ImplementedBy;

@ImplementedBy(SaikuChartColorsDummyService.class)
public interface SaikuChartColorsService {
   String getChartColors();
}
