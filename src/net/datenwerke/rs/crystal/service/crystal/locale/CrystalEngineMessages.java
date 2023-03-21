package net.datenwerke.rs.crystal.service.crystal.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface CrystalEngineMessages extends Messages {

   public final static CrystalEngineMessages INSTANCE = LocalizationServiceImpl
         .getMessages(CrystalEngineMessages.class);

   String reportTypeName();

   String reportVariantTypeName();

   String crystalReports();

   String crystalReportVariants();

}
