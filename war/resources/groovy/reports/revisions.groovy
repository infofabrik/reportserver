package net.datenwerke.rs.reportdoc.service.reports

import groovy.text.SimpleTemplateEngine

import java.text.SimpleDateFormat
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.rs.core.service.reportmanager.ReportService
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledHtmlReportImpl
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant
import net.datenwerke.rs.incubator.service.versioning.VersioningService
import net.datenwerke.rs.incubator.service.versioning.entities.Revision
import net.datenwerke.rs.utils.entitydiff.EntityDiffService
import net.datenwerke.rs.utils.entitydiff.result.EntityDiffResult
import net.datenwerke.rs.utils.entitydiff.result.FieldDiffResult
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.usermanager.entities.User

import org.apache.commons.lang3.StringEscapeUtils

return new RevisionsRpt(GLOBALS, baseurl, parameterMap).start();

class RevisionsRpt {

	def GLOBALS;
	def Map<String, String> parameterMap;
	def baseurl;

	/* Services */
	VersioningService versioningService;
	ReportService reportService;
	EntityDiffService entityDiffService;
	HttpServletRequest httpRequest;
	LocalizationServiceImpl localizationService;
	RemoteMessageService remoteMessageService;
	ConfigObject localisedTexts;
	String locale;
		
	public RevisionsRpt(glob, baseurl, params){
		this.GLOBALS = glob;
		this.parameterMap = params;
		this.baseurl = baseurl; 

		this.versioningService = GLOBALS.getInstance(VersioningService.class);
		this.reportService = GLOBALS.getInstance(ReportService.class);
		this.entityDiffService = GLOBALS.getInstance(EntityDiffService.class);
		this.httpRequest = GLOBALS.getInstance(HttpServletRequest.class);
		this.localizationService = GLOBALS.getInstance(LocalizationServiceImpl.class);
		this.remoteMessageService = GLOBALS.getInstance(RemoteMessageService.class);
		
		/* setup localisation */
		this.locale = localizationService.getLocale().getLanguage();
		this.localisedTexts = new ConfigObject();
		HashMap<String, HashMap<String, String>> msgs = remoteMessageService.getMessages(this.locale);
		localisedTexts.putAll(msgs.get("net.datenwerke.rs.reportdoc.client.locale.ReportDocumentationMessages"));
	}

	public Object start(){
		int	numberOfRevisions = Integer.MAX_VALUE;
		def repId = Long.valueOf(parameterMap['reportId'])
		def report = reportService.getReportById(repId)
		

		def cnt = 0;
		
		Set<Revision> revisions = versioningService.getRevisions(report);

		StringBuilder res = new StringBuilder();
		
		if(! revisions.isEmpty()){
			Iterator<Revision> iterator = revisions.iterator();
			res.append("<div class=\"headlineSeparator\">"+getLocalText("history")+"</div><div class='container computedColListe'>");
			res.append("<table style=\"width: 100%\">")
			res.append("<tr class=\"odd\"><th>"+getLocalText("revision")+"</th><th>"+getLocalText("date")+"</th><th>"+getLocalText("user")+"</th><th>"+getLocalText("change")+"</th></tr>");
			
			Object last = versioningService.getAtRevision(report.getClass(), report, iterator.next().getId());
			while (iterator.hasNext()){ 
				Revision rev = iterator.next();

				
			
				
				/* make sure that we only load number of revision many revisions */
				if(numberOfRevisions <= 0)
					break;
				else
					numberOfRevisions--;

				/* load revision */
				Object current = versioningService.getAtRevision(report.getClass(), report, rev.getId());
				User u = versioningService.getAuditReader().find(User.class, rev.getUserId(), rev.getId());

				String diffStr = "";

				if(null == current)
					diffStr += "["+getLocalText("deleted")+"]";
				else {
					EntityDiffResult diff = entityDiffService.diff(current, last);
					for(FieldDiffResult fr: diff.getFieldResults()){
						if(!fr.isEqual()){
							if(diffStr.length() > 0)
								diffStr += ", ";
							diffStr += fr.getField().getName();
						}
					}
				}

				last = current;
				
				res.append("<tr class=\""+(cnt++ % 2 == 0 ? 'even' : 'odd')+"\">");
				res.append("<td><a target=\"_blank\" href=\"" + mkDokuLink(report, rev.getId()) + "\">");
				res.append(String.valueOf(rev.getId()) + "</a></td>");
				res.append("<td>" + SimpleDateFormat.getDateTimeInstance().format(rev.getRevisionDate()) + "</td>");
				res.append("<td>" + String.valueOf(u?.getFirstname()) + " " + String.valueOf(u?.getLastname()) + "</td>");
				res.append("<td>" + diffStr + "</td>");
				res.append("</tr>");
              
			}
			res.append("</table></div>");
			
			
			return new CompiledHtmlReportImpl(fillTemplate(res.toString(), report));
		}
      return getLocalText("noHistory");
	}
	
	private String fillTemplate(String revInfo, Report report){
		def htmlTemplate = '''<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	 <head>
	  <title>'''+getLocalText("history")+'''</title>
	  <style type="text/css">
	    
	  
	   	body{font-size: 8pt; font-family: Sans-Serif; ${fitForPdf ? 'width: 180mm;' : 'max-width: 1000px;' } margin: 0 auto; margin-top: 1.5cm; background-color: white}
	  	
	   .container { margin: 0 auto; }
		.columnL { float: left; width: ${fitForPdf ?  '60mm' : '300px' }; margin-right: 10px; }
		.columnLlarge { float: left; margin-right: 10px; }
		.columnR { float: left; width: ${fitForPdf ?  '113mm' : '400px' }; margin-right: 0; }
		.columnRsmall { float: right; margin-right: 0; }
		
		.metadatacontainer {border-left: 1px solid #000000; padding-left: 5px; width: 41.5mm}
		.metadata {margin: 0 auto; clear: both;}
		.metadata .key { float: left; margin-right: 3px; }
		.metadata .value { float: right; margin-right: 0px; } 
		.break {clear: both}
		
		.reportHead { color: #000000;  font-size: 15pt; font-weight: bold; }
		.reportDesc { font-style: italic; }
		
		.berechnetColL {float: left; width: ${fitForPdf ?  '60mm' : '200px' }; margin-right: 10px;  }
		.berechnetColR {float: left; width: ${fitForPdf ?  '110mm' : '500px' }; margin-right: 0px; padding-left: 10px;}
		
		.spaltenColL {float: left; width: ${fitForPdf ?  '35mm' : '150px' }; margin-right: 10px; }
		.spaltenColR {float: left; 		
		  	width: ${fitForPdf ? '135mm' : '750px' }; 
		  	margin-right: 0px; padding-left: 10px;}
		.spaltenColRO { }
		.spaltenColRU { padding-top: 10px; padding-left: 5px; }
		
		.spaltenName { font-weight: bold; }
		.spaltenDesc { font-style: italic; }
		
		.spaltenMerkmal {float: left; width: ${fitForPdf ?  '29mm' : '150px'}; border-right: 1px solid #000000; padding-top: 10xp; padding-bottom: 0px; padding-left: 5px;}
		.spaltenMerkmalWide { width: ${fitForPdf ?  '46mm' : '180' }; }
		.spaltenMerkmal .key {font-weight: bold; font-size: 7.5pt;}
		.spaltenMerkmal .value {font-size: 7.5pt;}
			
		
		.filtertype {float: left; width: ${fitForPdf ? '8mm' : '30px' }; margin-left: 0px !important;}
		.filtervalue {float: left; 
		  	width: ${fitForPdf ? '120mm' : '670px'}; 
		  	margin: 0px auto; margin-bottom: 10px;  
			margin-left: 0px !important;}
		.filtervalueMultiCol { 
			-moz-column-count: 2;
	        -moz-column-gap: 10px;
	        -webkit-column-count: 2;
	        -webkit-column-gap: 10px;
	        column-count: 2;
	        column-gap: 10px;
		    margin-left: 0px !important;
		}
		
		.currentDate {
			text-align: right;
		}
		
		.berechnetesFeldValue { font-family: monospace; }
			
		.headlineSeparator { 
			 
		  	margin: 0 auto; margin-bottom: 5px; margin-top: 20px; padding-top: 10px; padding-bottom: 1px;
			color: #000000; border-bottom: 1px solid #000000; 
			font-size: 15pt;  }
			
		.row {padding: 3px; margin-left: 5px; margin-right: 5px;}
		.odd {background-color: #CECECE}
		.even {background-color: #FFFFFF}
		
		.parameterName {font-weight: bold}
		.parameterDesc {font-style: italic}
		
		.vorfilterBlock{border: 1px solid black;}
		.vorfilterBlock *{margin-left: 35px; border-bottom: none; border-right: none; line-height: 1.5em;}
		.vorfilterOp{margin-left: 0px; padding-left: 5px; display: block; }
		.vorfilterChildFilter{border-left: 1px solid black; border-top: 1px solid black;}
		
		.fetteLinie {border-bottom: 5px solid #3E4059; margin: 0px auto; margin-top: 5px; margin-bottom: 5px;}
	
	    .listeVorfilter {
			  margin-left: 5px;
			  margin-right: 5px;
	    }
	  </style>
 	  <link rel="stylesheet" type="text/css" href="${baseurl}rstheme"></link>	  
	 </head>
	<body class="rs-documentation">
		<div class='container header'> 
		  <div class='columnLlarge'> 
		      <div class="reportHead">${reportName}</div>
		  </div> 
		  <div class='columnRsmall'> 
		      <div class="logo"><img alt="Logo" src="${baseurl}rstheme?logo=true"/></div>
		  </div>
		  <div class="break"></div> 
		</div> 
		<div class="fetteLinie"></div> 
		
		${revInfo}
		
		
	</body>
	</html>'''
							

		
		def usrBinding = [
			'reportName': 		report.getName(),
			'reportId':			report.getId(),
			'fitForPdf':		false,
			'baseurl':		 	baseurl
			]
		def safeBinding = [
			'revInfo': 			revInfo,
			]
		def engine = new SimpleTemplateEngine();
		def html = engine.createTemplate(htmlTemplate).make(sanitizeBinding(usrBinding) + safeBinding).toString()
		
		return html;
	}
	
	def htmlspecialchars(String str){
		//return str.replace("<", "&lt;").replace(">", "&gt;");
		return StringEscapeUtils.escapeHtml4(str)
	}
	
	def sanitizeBinding(binding){
		return binding.collectEntries{ k, v -> [k, htmlspecialchars(String.valueOf(v))] }
	}
	
	private String mkDokuLink(Report report, Long rev){
		String moduleBase = ""; //httpRequest.getHeader("X-GWT-Module-Base");
		String url = moduleBase + "reportdocumentation?nonce=" + Math.random();
		
		String format = "pdf";
		url +=  "&format=" + format + "&id=" + report.getId();
		url += "&report=revdocumentation&revid=" + rev;
		url += "&download=true";
		
		return url;
	}
	
	private String getLocalText(key){
		return localisedTexts[key];
	}
	
}
