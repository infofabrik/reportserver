package net.datenwerke.rs.core.service.pdf;

import com.google.inject.Inject;

import net.datenwerke.rs.birt.service.reportengine.BirtReportService;
import net.datenwerke.rs.configservice.service.configservice.hooks.ReloadConfigNotificationHook;
import net.datenwerke.rs.utils.misc.PdfUtils;

public class RegisterPdfFontsOnConfigReload implements ReloadConfigNotificationHook {

	private final PdfUtils pdfUtils;
	private final BirtReportService birtService;

	@Inject
	public RegisterPdfFontsOnConfigReload(PdfUtils pdfUtils, BirtReportService birtService) {
		this.pdfUtils = pdfUtils;
		this.birtService = birtService;
	}
	
	@Override
	public void reloadConfig() {
		registerFonts();
	}

	@Override
	public void reloadConfig(String identifier) {
		if(PdfUtils.PDF_CONFIG_FILE.equals(identifier))
			registerFonts();
	}

	protected void registerFonts() {
		pdfUtils.registerPdfFonts();
		birtService.clearFontCache();
	}

}
