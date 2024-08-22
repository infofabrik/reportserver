package net.datenwerke.rs.reportdoc.service.reports

import java.text.SimpleDateFormat

import org.apache.commons.lang3.StringEscapeUtils

import groovy.text.*
import net.datenwerke.gf.service.genrights.AdministrationViewSecurityTarget
import net.datenwerke.gf.service.localization.RemoteMessageService
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService
import net.datenwerke.rs.core.service.reportmanager.genrights.ReportManagerAdminViewSecurityTarget
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorServiceImpl
import net.datenwerke.rs.core.service.reportmanager.ReportService
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledHtmlReportImpl
import net.datenwerke.rs.reportdoc.client.ReportDocumentationUIModule
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl
import net.datenwerke.rs.utils.misc.PdfUtils
import net.datenwerke.security.service.security.SecurityService
import net.datenwerke.security.service.security.SecurityServiceSecuree
import net.datenwerke.security.service.security.rights.Read

def repService = GLOBALS.getInstance(ReportService.class);
def dsService = GLOBALS.getInstance(DatasourceService.class);
def reportExecutorService = GLOBALS.getInstance(ReportExecutorServiceImpl.class)
def font = GLOBALS.getInstance(PdfUtils.class).getBaseFontFamily();
def remoteMessageService = GLOBALS.getInstance(RemoteMessageService.class)
def localizationService = GLOBALS.getInstance(LocalizationServiceImpl.class)
def securityService = GLOBALS.getInstance(SecurityService.class)

/* setup localisation */
locale = localizationService.getLocale().toLanguageTag();
localisedTexts = new ConfigObject();
HashMap<String, HashMap<String, String>> msgs = remoteMessageService.getMessages(locale);
localisedTexts.putAll(msgs.get("net.datenwerke.rs.reportdoc.client.locale.ReportDocumentationMessages"));


public String getLocalText(key){
	return localisedTexts[key];
}


def fitForPdf =  "pdf".equals(outputFormat)
def reportId = null;
def dsIds = []
def datasources = []
	try{
		reportId = Long.valueOf(parameterMap[ReportDocumentationUIModule.REPORT_PROPERTY_ID])
		dsIds = parameterMap[ReportDocumentationUIModule.DATASOURCE_PROPERTY_ID]
	}catch(Exception e){
		e.printStackTrace();
		return getLocalText("loadError")
	}
	if( null != dsIds && 0 != dsIds.size()){
		for(Long dsId: dsIds){
			def datasource = dsService.getDatasourceById(dsId);
			datasources.add(datasource)
		}
	}
	def report = repService.getReportById(reportId)

	def testResultList = reportExecutorService.variantTest(report, datasources)
	def tableData = ''
	def statusStr = ''
	for (Map<String, Object> testResult : testResultList) {
			if( null != testResult.get("ERROR") )
				testResult.replace("ERROR", htmlspecialchars(testResult.get("ERROR")))
			
			if( null != testResult.get("SHORT_ERROR") )
				testResult.replace("SHORT_ERROR", htmlspecialchars(testResult.get("SHORT_ERROR")))
			
			if (! securityService.checkRights(AdministrationViewSecurityTarget.class, SecurityServiceSecuree.class, Read.class) || 
					! securityService.checkRights(ReportManagerAdminViewSecurityTarget.class, SecurityServiceSecuree.class, Read.class))
				testResult.remove("QUERY")
		
			if(null != testResult.get("QUERY") )
				testResult.replace("QUERY", htmlspecialchars(testResult.get("QUERY")) )
			
			if(null != testResult.get("TEAMSPACES") )
				testResult.replace("TEAMSPACES", htmlspecialchars(testResult.get("TEAMSPACES")))
			
			status = testResult.get("STATUS")
			if('SUCCESS'.equals(status))
				statusStr = '<div class="headlineSeparator success">Status: '+status+'</div>'
			else if('FAILURE'.equals(status))
				statusStr = '<div class="headlineSeparator failure">Status: '+status+'</div>'
			
			tableData += statusStr +'<table>'
			for (Map.Entry<String, Object> entry : testResult.entrySet()) {
				if(entry.getKey().equals("STATUS"))
					if('SUCCESS'.equals(entry.getValue()))
						tableData += '<tr class="success"><th>'+entry.getKey()+':</th><td>'+entry.getValue()+'</td></tr>'
					else
						tableData += '<tr class="failure"><th>'+entry.getKey()+':</th><td>'+entry.getValue()+'</td></tr>'
				else if(entry.getKey().equals("SHORT_ERROR"))
					tableData += '<tr class="failure"><th>'+entry.getKey()+':</th><td>'+entry.getValue()+'</td></tr>'
				else
					tableData += '<tr><th>'+entry.getKey()+':</th><td>'+entry.getValue()+'</td></tr>'
				
			}
		tableData += '</table>'
	}

def htmlTemplate = ''' <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	 <head>
	  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	  <title>Variant test results</title>  
	  <style type="text/css">
	   @page {
		  size: A4 portrait;
		  margin-top:1.5cm;
		}
		 ${fitForPdf ?  '* {font-size: 8pt;}':'' }
		body{font-size: 8pt; font-family: '${font}', Sans-Serif; ${fitForPdf ? 'width: 180mm;' : 'max-width: 1000px;' } margin: 0 auto; margin-top: 1.5cm; background-color: white;}
			.headlineSeparator { 
			 margin: 0 auto; margin-bottom: 5px; margin-top: 20px; padding-top: 10px; padding-bottom: 1px;
			font-size: 15pt; font-weight: bold;  }
			.container { margin: 0 auto; }
			.reportHead { color: #000000;  font-size: 15pt; font-weight: bold; }
			.reportHeadSmall { color: #000000;  font-size: 11pt;  }
			.columnRsmall { float: right; margin-right: 0; }
			.columnLlarge { float: left; margin-right: 10px; }
			.currentDate {
			text-align: right;
			margin-top: 6px;
		}
		.break {clear: both}
		.fetteLinie {border-bottom: 5px solid #3E4059; margin: 0px auto; margin-top: 5px; margin-bottom: 5px;}
		.table {
			border-collapse: collapse;
			width: 100%;
		}
		.th,.td{
			border:1px solid #dddddd;
			padding:8px;
			vertical-align:bottom;
			text-align:left
		}
		tr:nth-child(even){
			background-color: #dddddd
		}
		.success{
		 color:green
		}
		.failure{
		 color:red
		}
		</style>
		<link rel="stylesheet" type="text/css" href="${baseurl}rstheme"></link>
		</head>
		<body class="rs-documentation">
		<div class='container header'> 
			<div class='columnLlarge'> 
		      <div class="reportHead">${getLocalText("variantTest")}<div class="reportHeadSmall">${reportName}: ${reportId}</div></div>		  
			</div>
			<div class='columnRsmall'> 
		      <div class="logo"><img alt="Logo" src="${baseurl}rstheme?logo=true"/></div>
		      <div class="currentDate">${now}</div>
			</div>
		  <div class="break"></div> 
		</div>
		  <div class="fetteLinie"></div> 
		  
		  ${tableData}		  
		  </body>
	</html>'''
	
	def htmlspecialchars(String str){
		return StringEscapeUtils.escapeHtml4(str).replace("\r\n", "\n").replace("\n", "<br />")
	}
	
		def sanitizeBinding(binding){
		return binding.collectEntries{ k, v -> [k, htmlspecialchars(String.valueOf(v))] }
	}
	
		/* User-defined variables, so we have to sanitize the text */
	def usrBinding = [
		'now': 				formatDate(new Date()),
		'reportName': 		report.getName(),
		'reportId':			reportId,
		'fitForPdf': 		fitForPdf,
		]
	/* The variables here are set in this template */
	def safeBinding = [
		'baseurl':		 	baseurl,
		'tableData':		tableData,
		'statusStr':		statusStr,
		'font':				font,
		getLocalText:  		{ key ->	getLocalText(key) }		
		]
	def engine = new SimpleTemplateEngine();
	def html = engine.createTemplate(htmlTemplate).make(sanitizeBinding(usrBinding) + safeBinding).toString()
		if("pdf".equals(outputFormat))
		return renderer.get("pdf").render(html);
	
	return new CompiledHtmlReportImpl(html);
	
		def formatDate(date){
		if(null == date)
			return "";
		pattern = "dd.MM.yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
		
		return sdf.format(date);
	}