package net.datenwerke.rs.reportdoc.service.reports

/**
 * Report Documentation Analyze
 *
 * Distributed under the GNU Affero General Public License (aGPL)
 * www.infofabrik.org
 *
 */

import groovy.sql.*
import groovy.text.*

import java.text.SimpleDateFormat

import net.datenwerke.rs.deployment.service.ReportDeploymentService
import net.datenwerke.rs.reportdoc.client.ReportDocumentationUIModule
import net.datenwerke.gf.service.localization.RemoteMessageService
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterInstance
import net.datenwerke.rs.base.service.parameters.blatext.BlatextParameterInstance
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterInstance
import net.datenwerke.rs.base.service.parameters.datasource.Mode
import net.datenwerke.rs.base.service.parameters.headline.HeadlineParameterInstance
import net.datenwerke.rs.base.service.parameters.separator.SeparatorParameterInstance
import net.datenwerke.rs.base.service.reportengines.table.entities.NullHandling
import net.datenwerke.rs.base.service.reportengines.table.entities.Order
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryColumnFilter
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BlockType
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.ColumnFilter
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterSpec
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatCurrency
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatDate
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatNumber
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatTemplate
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatText
import net.datenwerke.rs.base.service.reportengines.table.utils.TableReportColumnMetadataService
import net.datenwerke.rs.tsreportarea.server.tsreportarea.TsDiskRpcServiceImpl
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance
import net.datenwerke.rs.core.service.reportmanager.ReportService
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledHtmlReportImpl
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference
import net.datenwerke.rs.scheduler.server.scheduler.SchedulerRpcServiceImpl
import net.datenwerke.rs.utils.jpa.EntityUtils
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl
import net.datenwerke.gxtdto.server.dtomanager.DtoService
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto
import net.datenwerke.rs.utils.misc.PdfUtils
import com.google.gwt.safehtml.shared.SafeHtml
import net.datenwerke.rs.deployment.service.ColumnDifference
import net.datenwerke.rs.base.service.reportengines.table.TableReportUtils
import net.datenwerke.rs.base.service.reportengines.table.entities.Column
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition
import java.util.Set
import java.util.List
import net.datenwerke.gxtdto.client.utils.SqlTypes
import org.apache.commons.lang3.StringEscapeUtils


	def LocalizationServiceImpl localizationService = GLOBALS.getInstance(LocalizationServiceImpl.class)
	def RemoteMessageService remoteMessageService = GLOBALS.getInstance(RemoteMessageService.class)
	def ParameterSetFactory parameterSetFactory = GLOBALS.getInstance(ParameterSetFactory.class)
	def TsDiskRpcServiceImpl tsDiskService = GLOBALS.getInstance(TsDiskRpcServiceImpl.class)
	def DtoService dtoService = GLOBALS.getInstance(DtoService.class)
	def SchedulerRpcServiceImpl schedulerService = GLOBALS.getInstance(SchedulerRpcServiceImpl.class)
	def ReportDeploymentService deploymentService = GLOBALS.getInstance(ReportDeploymentService.class)
	def TableReportUtils tableReportUtils = GLOBALS.getInstance(TableReportUtils.class)
	
	/* setup localisation */
	locale = localizationService.getLocale().toLanguageTag();
	localisedTexts = new ConfigObject();
	HashMap<String, HashMap<String, String>> msgs = remoteMessageService.getMessages(locale);
	localisedTexts.putAll(msgs.get("net.datenwerke.rs.reportdoc.client.locale.ReportDocumentationMessages"));
	
	String font = GLOBALS.getInstance(PdfUtils.class).getBaseFontFamily();
	
	public String getLocalText(key){
		return localisedTexts[key];		
	}

	def leftRepId = null;	
	def rightRepId = null;
	def ignoreCase = false
	try{
		leftRepId = Long.valueOf(parameterMap[ReportDocumentationUIModule.LEFT_REPORT_PROPERTY_ID])
		rightRepId = Long.valueOf(parameterMap[ReportDocumentationUIModule.RIGHT_REPORT_PROPERTY_ID])
		ignoreCase = Boolean.valueOf(parameterMap[ReportDocumentationUIModule.IGNORE_CASE_PROPERTY_ID])
	}catch(Exception e){
		e.printStackTrace();
		return getLocalText("loadError")
	}
	
	def repService = GLOBALS.getInstance(ReportService.class);
	def tsService = GLOBALS.getInstance(TsDiskService.class);
	EntityUtils entityUtils = GLOBALS.getInstance(EntityUtils.class);
	def leftReport = repService.getReportById(leftRepId)
	def rightReport = repService.getReportById(rightRepId)
	
	def fitForPdf =  "pdf".equals(outputFormat)
	
	def columnDiffsTemplate = ''
	def variantsConflictingColsTemplate = ''
	
	if(leftReport instanceof TableReport && rightReport instanceof TableReport){
		def leftReportCols = tableReportUtils.getReturnedPlainColumns(leftReport, null)
		def rightReportCols = tableReportUtils.getReturnedPlainColumns(rightReport, null)
		def leftReportTableDef = tableReportUtils.getReturnedPlainTableDefinition(leftReport, null)
		def rightReportTableDef = tableReportUtils.getReturnedPlainTableDefinition(rightReport, null)
		
		def columnDiffs = deploymentService.calculateColumnDifferences(leftReport, leftReportTableDef, rightReport, rightReportTableDef, ignoreCase)
		
		def leftDiffs = []
		def rightDiffs = []
		def bothDiffs = []
		columnDiffs.each { diff -> 
			if (diff.leftReport == leftReport && null == diff.rightReport)
				leftDiffs.add(diff)
			if (diff.rightReport == rightReport && null == diff.leftReport)
				rightDiffs.add(diff)
			if (diff.leftReport == leftReport && diff.rightReport == rightReport)
				bothDiffs.add(diff)
		}
		
		leftDiffs.sort { diff -> 
			diff.leftColumnInfo.name
		}
		
		rightDiffs.sort { diff ->
			diff.rightColumnInfo.name
		}
		
		bothDiffs.sort { diff ->
			diff.leftColumnInfo.name
		}
		
		columnDiffsTemplate += '<div class="headlineSeparator">' + getLocalText("deploymentAnalysisLeftMsg") + '</div>'
				columnDiffsTemplate += '<div class="container">'
		if (leftDiffs.size() > 0) {
			
			def counter = 0
			leftDiffs.each { diff ->
				def engine = new SimpleTemplateEngine();
				def tpl = '''
				<div class='row ${pos}'>
					 <div class="spaltenColL">
						 <div class="spaltenName">${colName}</div>
					 </div>
					 <div class="spaltenColR">
						 <div class="spaltenColRO">
							<div class="spaltenMerkmal" style="width:72mm;">
								 <span class="key">${getLocalText("deploymentAnalysisJavaType")}:</span>
								 <span class="value">${javaType}</span>
							</div>
							<div class="spaltenMerkmal" style="width: 40mm;">
								 <span class="key">${getLocalText("deploymentAnalysisSqlType")}:</span>
								 <span class="value">${sqlType}</span>
							</div>
							<div class="spaltenMerkmal" style="width: 17mm; border-right: none;">
								 <span class="key">${getLocalText("deploymentAnalysisSize")}:</span>
								 <span class="value">${size}</span>
							</div>
							<div class="break"></div>
						 </div>
					 </div>
					 <div class="break"></div>
				</div>
				''';

				bindings = [
					pos: 		counter % 2 == 0? 'even': 'odd',
					colName:	diff.leftColumnInfo.name,
					javaType:	diff.leftColumnInfo.javaType,
					size:	diff.leftColumnInfo.size,
					sqlType:	SqlTypes.getName(diff.leftColumnInfo.sqlType),
				];
				safeBindings = [
					getLocalText:  { key ->
						getLocalText(key)
					}
				];
				columnDiffsTemplate += engine.createTemplate(tpl).make(sanitizeBinding(bindings)+safeBindings).toString()
				
				counter++
			}
			
			
		} else 
			columnDiffsTemplate += getLocalText("deploymentAnalysisNoConflicts")
		
		columnDiffsTemplate += '</div>'
		
		columnDiffsTemplate += '<div class="headlineSeparator">' + getLocalText("deploymentAnalysisRightMsg") + '</div>'
		columnDiffsTemplate += '<div class="container">'
		
		if (rightDiffs.size() > 0) {
			
			def counter = 0
			rightDiffs.each { diff ->
				def engine = new SimpleTemplateEngine();
				def tpl = '''
				<div class='row ${pos}'>
					 <div class="spaltenColL">
						 <div class="spaltenName">${colName}</div>
					 </div>
					 <div class="spaltenColR">
						 <div class="spaltenColRO">
							<div class="spaltenMerkmal" style="width:72mm;">
								 <span class="key">${getLocalText("deploymentAnalysisJavaType")}:</span>
								 <span class="value">${javaType}</span>
							</div>
							<div class="spaltenMerkmal" style="width: 40mm;">
								 <span class="key">${getLocalText("deploymentAnalysisSqlType")}:</span>
								 <span class="value">${sqlType}</span>
							</div>
							<div class="spaltenMerkmal" style="width: 17mm; border-right: none;">
								 <span class="key">${getLocalText("deploymentAnalysisSize")}:</span>
								 <span class="value">${size}</span>
							</div>
							<div class="break"></div>
						 </div>
					 </div>
					 <div class="break"></div>
				</div>
				''';

				bindings = [
					pos: 		counter % 2 == 0? 'even': 'odd',
					colName:	diff.rightColumnInfo.name,
					javaType:	diff.rightColumnInfo.javaType,
					size:	diff.rightColumnInfo.size,
					sqlType:	SqlTypes.getName(diff.rightColumnInfo.sqlType),
				];
				safeBindings = [
					getLocalText:  { key ->
						getLocalText(key)
					}
				];
				columnDiffsTemplate += engine.createTemplate(tpl).make(sanitizeBinding(bindings)+safeBindings).toString()
				
				counter++
			}
			
		} else 
			columnDiffsTemplate += getLocalText("deploymentAnalysisNoConflicts")
		
		columnDiffsTemplate += '</div>'
		
		columnDiffsTemplate += '<div class="headlineSeparator">' + getLocalText("deploymentAnalysisBothMsg") + '</div>'
		columnDiffsTemplate += '<div class="container">'
		
		if (bothDiffs.size() > 0) {
			
			def counter = 0
			bothDiffs.each { diff ->
				[1,2].each {
					def engine = new SimpleTemplateEngine();
					def tpl = '''
					<div class='row ${pos}'>
						 <div class="spaltenColL">
							 <div class="spaltenName">${colName}</div>
						 </div>
						 <div class="spaltenColR">
							 <div class="spaltenColRO">
								<div class="spaltenMerkmal" style="width:25mm;">
									 <span class="key">${getLocalText("deploymentAnalysisReport")}:</span>
									 <span class="value">${reportPos}</span>
								</div>
								<div class="spaltenMerkmal" style="width:46mm;">
									 <span class="key">${getLocalText("deploymentAnalysisJavaType")}:</span>
									 <span class="value">${javaType}</span>
								</div>
								<div class="spaltenMerkmal" style="width: 40mm;">
									 <span class="key">${getLocalText("deploymentAnalysisSqlType")}:</span>
									 <span class="value">${sqlType}</span>
								</div>
								<div class="spaltenMerkmal" style="width: 17mm; border-right: none;">
									 <span class="key">${getLocalText("deploymentAnalysisSize")}:</span>
									 <span class="value">${size}</span>
								</div>
								<div class="break"></div>
							 </div>
						 </div>
						 <div class="break"></div>
					</div>
					''';
	
					bindings = [
						pos: 		counter.intdiv(2) % 2 == 0? 'even': 'odd',
						reportPos: 	counter % 2 == 0? getLocalText('deploymentAnalysisLeft'): getLocalText('deploymentAnalysisRight'),
						colName:	counter % 2 == 0? diff.leftColumnInfo.name: 
											diff.rightColumnInfo.name,
						javaType:	counter % 2 == 0? diff.leftColumnInfo.javaType:
											diff.rightColumnInfo.javaType,
						size:		counter % 2 == 0? diff.leftColumnInfo.size:
											diff.rightColumnInfo.size,
						sqlType:	counter % 2 == 0? SqlTypes.getName(diff.leftColumnInfo.sqlType):
											SqlTypes.getName(diff.rightColumnInfo.sqlType),
					];
					safeBindings = [
						getLocalText:  { key ->
							getLocalText(key)
						}
					];
					columnDiffsTemplate += engine.createTemplate(tpl).make(sanitizeBinding(bindings)+safeBindings).toString()
					
					counter++
				}
			}
			
		} else 
			columnDiffsTemplate += getLocalText("deploymentAnalysisNoConflicts")
			
		columnDiffsTemplate += '</div>'
		
		// get variants of right report using columns not available in left report
		columnDiffs = deploymentService.getVariantsConflictingColumns(leftReport, leftReportTableDef, rightReport, rightReportTableDef, ignoreCase).
			sort( { k1, k2 -> k1.id <=> k2.id } as Comparator )
				
		variantsConflictingColsTemplate += '<div class="headlineSeparator">' + getLocalText("deploymentAnalysisVariantsMissingColumns") + '</div>'
		variantsConflictingColsTemplate += '<div class="container">'
		
		def counter = 0
		def nonEmpty = false
		columnDiffs.each { variant, diffs -> 
			rightDiffs = []
			diffs.each { diff -> 
				if (diff.rightReport == rightReport && null == diff.leftReport)
					rightDiffs.add(diff)
			}
			
			rightDiffs.sort { diff ->
				diff.rightColumnInfo.name
			}
					
			if (rightDiffs.size() > 0) {
					nonEmpty = true
					def engine = new SimpleTemplateEngine();
					def tpl = '''
					<div class='row ${pos}'>
						 <div class="spaltenColL">
							 <div class="spaltenName">${varId}: ${varName}</div>
						 </div>
						 <div class="spaltenColR">
							 <div class="spaltenColRO">
								<div class="spaltenMerkmal" style="width:129mm; border-right: none;">
									 <span class="key">${getLocalText("columns")}:</span>
									 <span class="value">${cols}</span>
								</div>
								<div class="break"></div>
							 </div>
						 </div>
						 <div class="break"></div>
					</div>
					''';
	
					bindings = [
						pos: 		counter % 2 == 0? 'even': 'odd',
						varName:	variant.name,
						cols:		rightDiffs.collect{diff -> diff.rightColumnInfo.name}
					];
					safeBindings = [
						varId:		variant.id,
						getLocalText:  { key ->
							getLocalText(key)
						}
					];
					variantsConflictingColsTemplate += engine.createTemplate(tpl).make(sanitizeBinding(bindings)+safeBindings).toString()
					
			} 
			
			counter++
			
		}
		
		if (! nonEmpty)
			variantsConflictingColsTemplate += getLocalText("deploymentAnalysisNoConflicts")
		
		variantsConflictingColsTemplate += '</div>'
		
		// conflicting columns
		variantsConflictingColsTemplate += '<div class="headlineSeparator">' + getLocalText("deploymentAnalysisVariantsConflictingColumns") + '</div>'
		variantsConflictingColsTemplate += '<div class="container">'
		
		counter = 0
		nonEmpty = false
		columnDiffs.each { variant, diffs ->
			bothDiffs = []
			diffs.each { diff ->
				if (diff.leftReport == leftReport && diff.rightReport == rightReport)
					bothDiffs.add(diff)
			}
			
			bothDiffs.sort { diff ->
				diff.leftColumnInfo.name
			}
					
			if (bothDiffs.size() > 0) {
				nonEmpty = true
				bothDiffs.each { diff ->
					[1,2].each {
						def engine = new SimpleTemplateEngine();
						def tpl = '''
					<div class='row ${pos}'>
						 <div class="spaltenColL">
							 <div class="spaltenName">${varNameAndId}</div>
						 </div>
						 <div class="spaltenColR">
							 <div class="spaltenColRO">
								<div class="spaltenMerkmal" style="width:50mm;">
									 <span class="key">${getLocalText("column")}:</span>
									 <span class="value">${colName}</span>
								</div>
								<div class="spaltenMerkmal" style="width:25mm;">
									 <span class="key">${getLocalText("deploymentAnalysisReport")}:</span>
									 <span class="value">${reportPos}</span>
								</div>
								<div class="spaltenMerkmal" style="width: 52mm; border-right: none;">
									 <span class="key">${getLocalText("deploymentAnalysisSqlType")}:</span>
									 <span class="value">${sqlType}</span>
								</div>
								<div class="break"></div>
							 </div>
						 </div>
						 <div class="break"></div>
					</div>
					''';
		
						bindings = [
							pos: 		counter.intdiv(2) % 2 == 0? 'even': 'odd',
							varName:	counter % 2 == 0? '': variant.name,
							varNameAndId:	counter % 2 == 0? '--': variant.id + ": " + variant.name,
							reportPos: 	counter % 2 == 0? getLocalText('deploymentAnalysisLeft'): getLocalText('deploymentAnalysisRight'),
							colName:	counter % 2 == 0? diff.leftColumnInfo.name:
												diff.rightColumnInfo.name,
							sqlType:	counter % 2 == 0? SqlTypes.getName(diff.leftColumnInfo.sqlType):
												SqlTypes.getName(diff.rightColumnInfo.sqlType),
						];
						safeBindings = [
							varId:		variant.id,
							getLocalText:  { key ->
								getLocalText(key)
							}
						];
						variantsConflictingColsTemplate += engine.createTemplate(tpl).make(sanitizeBinding(bindings)+safeBindings).toString()
					
						counter++
					}
				}
				
			}
			
		}
		
		if (! nonEmpty)
			variantsConflictingColsTemplate += getLocalText("deploymentAnalysisNoConflicts")
		
		variantsConflictingColsTemplate += '</div>'
		
	}
	
	def htmlTemplate = '''<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	 <head>
	  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	  <title>Deployment Analysis</title>  
	  <style type="text/css">

	    @page {
		  size: A4 portrait;
		  margin-top:1.5cm;
	
	     @bottom-right {
	       content: "${getLocalText("page")} " counter(page) " ${getLocalText("of")} " counter(pages);
		   font-family: '${font}', Sans-Serif;
		   font-size: 8pt;
	     }
		}
	
	
		  ${fitForPdf ?  '* {font-size: 8pt;}':'' }
	  
	   	body{font-size: 8pt; font-family: '${font}', Sans-Serif; ${fitForPdf ? 'width: 180mm;' : 'max-width: 1000px;' } margin: 0 auto; margin-top: 1.5cm; background-color: white;}
	  	
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
		.reportHeadSmall { color: #000000;  font-size: 11pt;  }
		.reportDesc { font-style: italic; }
		
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
			
		
		.currentDate {
			text-align: right;
			margin-top: 6px;
		}
		
		.headlineSeparator { 
			 
		  	margin: 0 auto; margin-bottom: 5px; margin-top: 20px; padding-top: 10px; padding-bottom: 1px;
			color: #000000; border-bottom: 1px solid #000000; 
			font-size: 15pt; font-weight: bold;  }
			
		.row {padding: 3px; margin-left: 5px; margin-right: 5px;}
		.odd {background-color: #CECECE}
		.even {background-color: #FFFFFF}
		
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
		      <div class="reportHead">${getLocalText("deploymentAnalysis")}: ${headerType}${options}<div class="reportHeadSmall">${leftReportName}: ${leftRepId}<br />${rightReportName}: ${rightRepId}</div></div>
		  </div> 
		  <div class='columnRsmall'> 
		      <div class="logo"><img alt="Logo" src="${baseurl}rstheme?logo=true"/></div>
		      <div class="currentDate">${now}</div>
		  </div>
		  <div class="break"></div> 
		</div> 
		<div class="fetteLinie"></div> 
		<div class='container'> 
		  <div class='columnLlarge'>
		    <div class="reportDesc">${ replaceTranslationValues(replaceTranslationValues(getLocalText("deploymentAnalysisDesc"), 0, leftReportName), 1, rightReportName)}
			<br />${desc2}
			</div>
		  </div> 
	  	  <div class="break"></div> 
		</div> 
		
		${columnDiffs}

		${variantsConflictingColsTemplate}
		
	</body>
	</html>'''
						
	def htmlspecialchars(String str){
		//return str.replace("<", "&lt;").replace(">", "&gt;");
		return StringEscapeUtils.escapeHtml4(str).replace("\r\n", "\n").replace("\n", "<br />")
	}
	
	def replaceTranslationValues(String str, Integer old, String replacement) {
		return str.replace("{" + old + "}", replacement);
	}
	
	def sanitizeBinding(binding){
		return binding.collectEntries{ k, v -> [k, htmlspecialchars(String.valueOf(v))] }
	}
	
	/* User-defined variables, so we have to sanitize the text */
	def usrBinding = [
		'now': 				formatDate(new Date()), 
		'leftReportName':	leftReport.name,
		'leftRepId':		leftRepId,
		'rightReportName':	rightReport.name,
		'rightRepId':		rightRepId,
		'options':			ignoreCase == true? " (-i)": "",
//		'reportName': 		tsObject instanceof TsDiskReportReference ? ((TsDiskReportReference)tsObject).getName() : report.getName(), 
		'fitForPdf': 		fitForPdf, 
		]
	/* The variables here are set in this template */
	def safeBinding = [
		'baseurl':		 	baseurl,
		'columnDiffs':				columnDiffsTemplate,
		'variantsConflictingColsTemplate':	variantsConflictingColsTemplate,
		'font':						font,
		'headerType':				getLocalText('deploymentAnalysisConflicts'),
		'desc2':					getLocalText('deploymentAnalysisDesc2'),
		getLocalText:  				{ key ->	getLocalText(key) },
		replaceTranslationValues: 	{ str, old, replacement -> replaceTranslationValues(str, old, replacement) }
		] 
	def engine = new SimpleTemplateEngine();
	def html = engine.createTemplate(htmlTemplate).make(sanitizeBinding(usrBinding) + safeBinding).toString()
	
//	System.out.println(html)
	    
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