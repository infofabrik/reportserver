package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

import java.awt.image.BufferedImage;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

public interface CompiledPngReport extends CompiledReport {

   public BufferedImage[] getReport();
}
