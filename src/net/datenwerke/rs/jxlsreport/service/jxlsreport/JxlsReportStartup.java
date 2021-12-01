package net.datenwerke.rs.jxlsreport.service.jxlsreport;

import org.jxls.builder.xls.XlsCommentAreaBuilder;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportEngineProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.hookers.BaseJxlsOutputGeneratorProvider;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.hookers.JxlsReportEngineProviderHooker;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.hookers.JxlsReportTypeProviderHooker;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.hookers.JxlsReportUploadHooker;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.hooks.JxlsOutputGeneratorProviderHook;

public class JxlsReportStartup {

	@Inject
	public JxlsReportStartup(
		HookHandlerService hookHandlerService,
			
		JxlsReportEngineProviderHooker jxlsReportEngineProviderHooker,
		
		
		Provider<BaseJxlsOutputGeneratorProvider> baseOutputGenerators,
		
		JxlsReportUploadHooker jxlsReportUploadHooker
		) {
		
		hookHandlerService.attachHooker(ReportTypeProviderHook.class, new JxlsReportTypeProviderHooker());
		
		hookHandlerService.attachHooker(ReportEngineProviderHook.class, jxlsReportEngineProviderHooker);
		
		hookHandlerService.attachHooker(FileUploadHandlerHook.class, jxlsReportUploadHooker);
		
		/* base exporters */
		hookHandlerService.attachHooker(JxlsOutputGeneratorProviderHook.class, baseOutputGenerators, HookHandlerService.PRIORITY_LOW);
		
		/* multi-line and comment support */
		hookHandlerService.attachHooker(LateInitHook.class, () -> XlsCommentAreaBuilder.MULTI_LINE_SQL_FEATURE = true);

	}
	
}
