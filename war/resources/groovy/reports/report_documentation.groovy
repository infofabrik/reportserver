package net.datenwerke.rs.reportdoc.service.reports

/**
 * Berichtsdokumentation - ReportServer
 *
 * This script serves as documentary report for other report objects in reportserver.
 * It is meant to be used as a script report and expects one mandatory 
 * parameter "reportId" as well as one optional parameter "tsObjectId"
 * specifying the report that should be documented.
 *
 * Distributed under the GNU Affero General Public License (aGPL)
 * www.infofabrik.org
 *
 */

import groovy.sql.*
import groovy.text.*

import java.text.SimpleDateFormat

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

import org.apache.commons.lang3.StringEscapeUtils


	def LocalizationServiceImpl localizationService = GLOBALS.getInstance(LocalizationServiceImpl.class)
	def RemoteMessageService remoteMessageService = GLOBALS.getInstance(RemoteMessageService.class)
	def ParameterSetFactory parameterSetFactory = GLOBALS.getInstance(ParameterSetFactory.class)
	def TsDiskRpcServiceImpl tsDiskService = GLOBALS.getInstance(TsDiskRpcServiceImpl.class)
	def DtoService dtoService = GLOBALS.getInstance(DtoService.class)
	def SchedulerRpcServiceImpl schedulerService = GLOBALS.getInstance(SchedulerRpcServiceImpl.class)
	
	/* setup localisation */
	locale = localizationService.getLocale().toLanguageTag();
	localisedTexts = new ConfigObject();
	HashMap<String, HashMap<String, String>> msgs = remoteMessageService.getMessages(locale);
	localisedTexts.putAll(msgs.get("net.datenwerke.rs.reportdoc.client.locale.ReportDocumentationMessages"));
	
	String font = GLOBALS.getInstance(PdfUtils.class).getBaseFontFamily();
	
	
	public String getLocalText(key){
		return localisedTexts[key];		
	}

	def repId = null;	
	try{
		repId = Long.valueOf(parameterMap['reportId'])
	}catch(Exception e){
		return getLocalText("loadError")
	}
	def repService = GLOBALS.getInstance(ReportService.class);
	def tsService = GLOBALS.getInstance(TsDiskService.class);
	EntityUtils entityUtils = GLOBALS.getInstance(EntityUtils.class);
	def report = repService.getReportById(repId)
	
	def fitForPdf =  "pdf".equals(outputFormat)
	
	/* load teamspace object if possible */
	def tsObjectId = null;
	def tsObject = null;
	try{
		tsObjectId = Long.valueOf(parameterMap['tsObjectId'])
		tsObject = tsService.getNodeById(tsObjectId)
	}catch(Exception e){
	}	
	
	/* if report is table report .. augment it with metadata */
	if(report instanceof TableReport){
		try{
			TableReportColumnMetadataService metadataService = GLOBALS.getInstance(TableReportColumnMetadataService.class);
			metadataService.augmentWithMetadata(report);
		}catch(all){}
	}
	
	/* config */
	def mainConfigTemplate = '<div class="headlineSeparator">'+getLocalText("general")+'</div>';
	mainConfigTemplate += '<div class="container">';
	mainConfigTemplate += '<div class="columnL"><b>UUID:</b></div><div class="columnR">' + report.getUuid()?.toString() + '</div>'
	if(report instanceof ReportVariant)
		mainConfigTemplate += '<div class="columnL"><b>'+getLocalText("baseReport")+':</b></div><div class="columnR">' + "${report.getParent()?.getName()} (${report.getParent()?.getId()})" + '</div>'
		
	if(report instanceof TableReport){
		mainConfigTemplate += '<div class="columnL"><b>'+getLocalText("distinct")+':</b></div><div class="columnR">' + (null != report.isDistinctFlag() ? report.isDistinctFlag() ? getLocalText("yes") : getLocalText("no") : getLocalText("no")) + '</div>'
		if(report.isEnableSubtotals()){
			def groupingColumns = report.getColumns()?.findResults{it.isSubtotalGroup() ? it : null}?.collect{it.getName()}.join(",");
			mainConfigTemplate += '<div class="columnL"><b>'+getLocalText("subtotals")+':</b></div><div class="columnR">'+getLocalText("yes")+', ' + getLocalText("groupedBy") + ': ' + groupingColumns + '</div>';
		} else {
			mainConfigTemplate += ('<div class="columnL"><b>' + getLocalText("subtotals") + ' :</b></div><div class="columnR">'+  getLocalText("no") + '</div>');
		}
	}
	mainConfigTemplate += '''<div class="break"></div></div>''';
	
	/* report variants */
	def variantsTemplate = ''
	if (! (report instanceof ReportVariant)) {
		List<Report> variants = report.getChildren();
		if (! variants.isEmpty()) {
			variantsTemplate = '<div class="headlineSeparator">' + getLocalText("variants") + '</div>'
			variantsTemplate += '<ol>'
			
			variants.sort { variant -> variant.getName() }
			
			variants.each { variant ->
				variantsTemplate += '<li>' + variant.getName() + ' (' + variant.getId() + ')</li>'
			}
			
			variantsTemplate += '</ol>'
		}
		
	}
	
	
	/* prepare computed columns */
	def computedColTemplate = "";
	if(report instanceof TableReport){
		if(!report.getAdditionalColumns().isEmpty()){
			computedColTemplate = '<div class="headlineSeparator">' + getLocalText("computedColumns") + '''</div>
					<div class='container computedColListe'>''';
			
			def cnt = 0;
			report.getAdditionalColumns().each{
				def engine = new SimpleTemplateEngine();
				def tpl = '''<div class='row ${cnt}'>
						<div class="berechnetColL">
					 		<div class="spaltenName">${colName}</div>
				 			<div class="spaltenDesc">${colDesc}</div>
				 		</div>
				 		<div class="berechnetColR">
				 			<div class="berechnetesFeldValue">
				 			 ${colExpr}
				 			</div>
				 		</div>
		 		 	    <div class="break"></div>
		 		 	  </div>''';
						
						
			bindings =	[colName: 	it.getName(),
						colDesc:	it.getDescription(),
						colExpr:	it.getExpression(),
						cnt:		(cnt % 2 == 0 ? 'even' : 'odd')];
				
				computedColTemplate += engine.createTemplate(tpl).make(sanitizeBinding(bindings)).toString()
				cnt++;
			}
					
			computedColTemplate += '</div>';
		}
	}
	/* prepare columns */
	def spaltenTemplate = "";
	if(report instanceof TableReport && !report.getColumns().isEmpty()){
		if(report instanceof TableReport){
			spaltenTemplate = '<div class="headlineSeparator">'+getLocalText("columns")+'''</div>
			<div class='container spaltenListe'>''';
			
			report.getColumns().each{
				def engine = new SimpleTemplateEngine();
				def tpl = '''<div class='row ${pos}'>
					 <div class="spaltenColL">
						 <div class="spaltenName">${colName}</div>
						 <div class="spaltenAlias">${colAlias}</div>
						 <div class="spaltenDesc">${colDesc}</div>
					 </div>
					 <div class="spaltenColR">
						 <div class="spaltenColRO">
							<div class="spaltenMerkmal" style="width: 21mm;">
								 <span class="key">${getLocalText("hidden")}:</span>
								 <span class="value">${hidden}</span>
							</div>
							<div class="spaltenMerkmal" style="width: 31mm;">
								 <span class="key">${getLocalText("order")}:</span>
								 <span class="value">${sortOrder}</span>
							</div>
						   <div class="spaltenMerkmal spaltenMerkmalWide" style="width: 38mm;">
								 <span class="key">${getLocalText("emptyCells")}:</span>
								 <span class="value">${emptyCells}</span>
							</div>
							<div class="spaltenMerkmal" style="width: 38mm; border-right: none;">
								 <span class="key">${getLocalText("aggregation")}:</span>
								 <span class="value">${agg}</span>
							</div>
							   <div class="break"></div>
						 </div>''';
						 
				/* handle format */
				if(null != it.getFormat()){
					def format = it.getFormat();
					tpl += '<div style="padding-left: 5px; font-size: 7.5pt;"><b style="font-size: 7.5pt;">'+getLocalText("formattedAs")+'</b>: '
					if(format instanceof ColumnFormatCurrency){
						tpl += getLocalText("formatCurrency") + ': ${format.getNumberOfDecimalPlaces()}'
					} else if(format instanceof ColumnFormatNumber){
						tpl += getLocalText("formatNumber") + ': ${format.getNumberOfDecimalPlaces()}'
					} else if(format instanceof ColumnFormatDate){
						tpl += getLocalText("formatDate") + ': ${format.getTargetFormat()}'
					}else if(format instanceof ColumnFormatText){
						tpl += getLocalText("formatText") + "."
					}else if(format instanceof ColumnFormatTemplate){
						tpl += getLocalText("formatTemplate") + ': ${format.getTemplate()}'
					}
					tpl += '</div>';
				}
						 
				/* case sensitive */
				if(it.getFilter()?.isCaseSensitive()){
					tpl += '<div style="padding-left: 5px; font-size: 7.5pt;"><b style="font-size: 7.5pt;">'+getLocalText("caseSensitiveFilter")+'</b></div>'
				}
						 
				/* handle filters */
				filterIV = "";
				if(null != it.getFilter()?.getIncludeValues() && !it.getFilter()?.getIncludeValues().isEmpty()){
					filterIV += '<div class="filtertype">=</div>';
					filterIV += '<div class="filtervalue filtervalueMultiCol">';
					filterIV += it.getFilter()?.getIncludeValues().collect({htmlspecialchars("\"${it}\"")}).join(fitForPdf?", ":",<br/>");
					filterIV += '</div><div class="break"></div>';
				}
				filterIR = "";
				if(null != it.getFilter()?.getIncludeRanges() && !it.getFilter()?.getIncludeRanges().isEmpty()){
					filterIR += '<div class="filtertype">[]</div>';
					filterIR += '<div class="filtervalue">';
					filterIR += it.getFilter()?.getIncludeRanges().collect({htmlspecialchars("\"${it.getRangeFrom()} - ${it.getRangeTo()}\"")}).join(fitForPdf?", ":",<br/>");
					filterIR += '</div><div class="break"></div>';
				}
				filterEV = "";
				if(null != it.getFilter()?.getExcludeValues() && !it.getFilter()?.getExcludeValues().isEmpty()){
					filterEV += '<div class="filtertype">&lt;&gt;</div>';
					filterEV += '<div class="filtervalue filtervalueMultiCol">';
					filterEV += it.getFilter()?.getExcludeValues().collect({htmlspecialchars("\"${it}\"")}).join(fitForPdf?", ":",<br/>");
					filterEV += '</div><div class="break"></div>';
				}
				filterER = "";
				if(null != it.getFilter()?.getExcludeRanges() && !it.getFilter()?.getExcludeRanges().isEmpty()){
					filterER += '<div class="filtertype">][</div>';
					filterER += '<div class="filtervalue">';
					filterER += it.getFilter()?.getExcludeRanges().collect({htmlspecialchars("\"${it.getRangeFrom()} - ${it.getRangeTo()}\"")}).join(fitForPdf?", ":",<br/>");
					filterER += '</div><div class="break"></div>';
				}
				
				if((filterIV + filterIR + filterEV + filterER).length() > 0){
					tpl += '''<div class="spaltenColRU">${filterIV}${filterIR}${filterEV}${filterER}</div>''';
				}
				
				tpl +='''</div>
				   <div class="break"></div>
				 </div>
				''';
				bindings = [
					pos: 		(it.getPosition() % 2 == 0 ? 'even' : 'odd'),
					colName:	it.getName(),
					colDesc:	null==it.getDescription()?"":it.getDescription(),
					colAlias:	it.getAlias() == null ? it.getDefaultAlias() == null ? '' : it.getDefaultAlias() : it.getAlias(),
					hidden:		(it.isHidden() ? getLocalText("yes") : getLocalText("no")),
					sortOrder: 	(null == it.getOrder() ? '-' : Order.ASC == it.getOrder() ? getLocalText("ascending") : getLocalText("descending")),
					emptyCells:	(null == it.getNullHandling() ? '-' : NullHandling.Exlude == it.getNullHandling() ? getLocalText("excluded") : getLocalText("included")),
					agg:		(null == it.getAggregateFunction() ? '-' : it.getAggregateFunction().name() )
					];
				safeBindings = [
					filterIV:	filterIV,
					filterEV:	filterEV,
					filterIR:	filterIR,
					filterER:	filterER,
					format: 	it.getFormat(), 
					getLocalText:  { key ->	getLocalText(key) }
					];
				spaltenTemplate += engine.createTemplate(tpl).make(sanitizeBinding(bindings)+safeBindings).toString()
			}
				
			spaltenTemplate += '''<div class="break"></div>
			</div>''';
		}
	}
	
	/* parameter */
	def parameterTemplate = '';
	if(!report.getParameterInstances().isEmpty()){
		parameterTemplate = '<div class="headlineSeparator">'+ getLocalText("parameter") + '''</div>
		<div class='container parameterListe'>''';

		ParameterSet parameterSet = null != user ? parameterSetFactory.create(user, report) : parameterSetFactory.create(report) ;
		for(ParameterInstance pi : report.getParameterInstances())
			parameterSet.add(entityUtils.deepHibernateUnproxy(pi));
		Map<String, Object> parameterMap = parameterSet.getParameterMapSimple();
		
		int cnt = 0;
		report.getParameterInstances().sort({it.getDefinition().getN()}).each{
			/* ignore certain parameter types */
			if(! (it instanceof SeparatorParameterInstance ||
				  it instanceof BlatextParameterInstance || 
			      it instanceof HeadlineParameterInstance )){
			      	
				GLOBALS.injector.injectMembers(it);
				it = entityUtils.deepHibernateUnproxy(it);
				
				def engine = new SimpleTemplateEngine();
				def tpl = '''<div class='row ${cnt}'>
					<div class='columnL'>
					<div class="parameterName">${pName}</div>
					  <div class="parameterDesc">${pDesc}</div>
				  </div>
				 <div class='columnR'>
				   <div class="parameterWert">
					${pVal}
				   </div>
				 </div>
				 <div class="break"></div>
				</div>'''
				
				val = parameterMap.get(it.getKey());
	
				/* handle datasource parameters */			
				if(it instanceof DatasourceParameterInstance){
					if(null != val && val instanceof List && val.isEmpty()){
						val = getLocalText("all");
					}else if(null != val){
						val = val.toString();
					}else{
						if(Mode.Single == it.getDefinition().getMode()){
							val = "<" + getLocalText('defaultOrFirst')+">"
						}else{
							val = "<"+getLocalText("all")+">";
						}
					}
				}
				
				/* handle fileselection parameter */
				if(it instanceof FileSelectionParameterInstance){
					if(null == val){
						val = "[]";
					}else{
						val = "[" + val.collect({it.getName()}).join(",") + "]";
					}
				}
				
				bindings = [
					pName:	it.getDefinition()?.getName(),
					pDesc:	it.getDefinition()?.getDescription(),
					pVal:	val,
					cnt:	(cnt % 2 == 0 ? 'even' : 'odd') 
					]
				parameterTemplate += engine.createTemplate(tpl).make(sanitizeBinding(bindings)).toString()
				cnt++
			}
		}
			
		parameterTemplate += '''<div class="break"></div>
		</div>''';
	}
	
	/* vorfilter */
	def vorfilterTemplate = ''
	if(report instanceof TableReport){
			TableReport tr = report;
			PreFilter pf = tr.getPreFilter();
			FilterBlock rootBlock = pf.getRootBlock();
		
			if(null != rootBlock && ((null != rootBlock.getChildBlocks() && ! rootBlock.getChildBlocks().isEmpty()) || (null != rootBlock.getFilters() && ! rootBlock.getFilters().isEmpty()))){
			vorfilterTemplate = '<div class="headlineSeparator">'+getLocalText("prefilter")+'</div>\r\n';
			vorfilterTemplate += '\t<div class="container listeVorfilter">\r\n';
			
			
			def blks = {FilterBlock block, depth ->
				
				txt = ("\t" * (depth + 1)) + "<div class=\"vorfilterBlock ${depth % 2 == 0 ? 'even' : 'odd'}\">\r\n";
				
				BlockType bt = block.getBlockType();
				txt += ("\t" * (depth + 2)) + "<span class=\"vorfilterOp\">${bt.name()}";
				if(null != block.getDescription() && !block.getDescription().isEmpty()){
					txt += " (${block.getDescription()})";
				}
				txt += "</span>\r\n";
				txt +="<div class=\"vorfilterChildFilter ${(depth + 1) % 2 == 0 ? 'even' : 'odd'}\">";
				
				def filterComp = [compare:  {FilterSpec a, FilterSpec b->
					res = 1;
					if(a.getClass() != b.getClass()){
						res = a.getClass().getName().compareTo(b.getClass().getName());
					}else
					if(a instanceof BinaryColumnFilter){
						res = (a.getColumnA().getName() + a.getColumnB().getName()).compareTo((b.getColumnA().getName() + b.getColumnB().getName()))
					}else
					if(a instanceof ColumnFilter){
						res = a.getColumn().getName().compareTo(b.getColumn().getName());
					}
					if(res == 0)
						return 1;
					return res;
				}] as Comparator;
				def fts = new TreeSet(filterComp);
				fts.addAll(block.getFilters());
				
				fts.asList().each { FilterSpec filter -> 
					if(filter instanceof ColumnFilter){
						col = filter.getColumn();
						
						txt += ("\t" * (depth + 2)) + '<div class="vorfilterValue">\r\n';
						txt += ("\t" * (depth + 3)) + htmlspecialchars(col.getName());
						if(null != filter.getDescription() && !filter.getDescription().isEmpty()){
							txt += " (${filter.getDescription()})";
						}
						txt +="\r\n";
						
						txt +='<div class="rs-prefilter-att">';
						boolean attSep = false;
						if(col.getFilter()?.isCaseSensitive()){
							txt += '<span style="font-size: 7.5pt; margin-left: 0px;">'+getLocalText("caseSensitiveFilter")+'</span>'
							attSep = true;
						}
						if(null != col.getNullHandling()){
							if(attSep)
								txt += ' | ';
							txt += '<span style="font-size: 7.5pt;  margin-left: 0px;">' + getLocalText("emptyCells") + ": " + (NullHandling.Exlude == col.getNullHandling() ? getLocalText("excluded") : getLocalText("included")) +'</span>'
						} 
						
						txt +='</div>';
						
						txt += '<div class="">';
						
						if(null != col.getFilter()?.getIncludeValues() && !col.getFilter()?.getIncludeValues().isEmpty()){
							txt += '<div class="filtertype">=</div>';
							txt += '<div class="filtervalue filtervalueMultiCol">';
							txt += col.getFilter()?.getIncludeValues().collect({htmlspecialchars("\"${it}\"")}).join(fitForPdf?", ":",<br/>");
							txt += '</div><div class="break"></div>';
						}
						if(null != col.getFilter()?.getIncludeRanges() && !col.getFilter()?.getIncludeRanges().isEmpty()){
							txt += '<div class="filtertype">[]</div>';
							txt += '<div class="filtervalue">';
							txt += col.getFilter()?.getIncludeRanges().collect({htmlspecialchars("\"${it.getRangeFrom()} - ${it.getRangeTo()}\"")}).join(fitForPdf?", ":",<br/>");
							txt += '</div><div class="break"></div>';
						}
						if(null != col.getFilter()?.getExcludeValues() && !col.getFilter()?.getExcludeValues().isEmpty()){
							txt += '<div class="filtertype">&lt;&gt;</div>';
							txt += '<div class="filtervalue filtervalueMultiCol">';
							txt += col.getFilter()?.getExcludeValues().collect({htmlspecialchars("\"${it}\"")}).join(fitForPdf?", ":",<br/>");
							txt += '</div><div class="break"></div>';
						}
						if(null != col.getFilter()?.getExcludeRanges() && !col.getFilter()?.getExcludeRanges().isEmpty()){
							txt += '<div class="filtertype">][</div>';
							txt += '<div class="filtervalue">';
							txt += col.getFilter()?.getExcludeRanges().collect({htmlspecialchars("\"${it.getRangeFrom()} - ${it.getRangeTo()}\"")}).join(fitForPdf?", ":",<br/>");
							txt += '</div><div class="break"></div>';
						}
						
						txt += '</div>';
						txt += ("\t" * (depth + 2)) + '</div>\r\n';
						
					}else if(filter instanceof BinaryColumnFilter){
						txt += ("\t" * (depth + 2)) + '<div class="vorfilterCompare">';
						txt += htmlspecialchars(filter.getColumnA().getName() + " " +
						       filter.getOperator().getStrOp() + " " +
							   filter.getColumnB().getName());
						txt += '</div>\r\n';
					}
				};
				txt +="</div>"
			
				def blkComp = [compare:  {FilterBlock a, FilterBlock b-> 
					(a.getFilters().size() + a.getChildBlocks().size() * 100 < b.getFilters().size() + b.getChildBlocks().size() * 100)?-1:1;  
				}] as Comparator;
				def ts = new TreeSet(blkComp);
				ts.addAll(block.getChildBlocks());
				for(childBlock in ts.asList()){
					txt += call(childBlock, depth + 1)
				}
				
				txt += ("\t" * (depth + 1)) + '</div>\r\n';
				txt;
			}(rootBlock, 1)
		
			vorfilterTemplate += blks;
			
			vorfilterTemplate += '\t</div>\r\n\r\n';
		}	
		
	}
	
	/* teamspaces */
	def teamspacesTemplate = ''
	ReportDto rDto = (ReportDto) dtoService.createDto(report)
	SafeHtml teamspacesAsHtml = tsDiskService.getTeamSpacesWithPathsThatLinkToAsHtml(rDto)
	if (null != teamspacesAsHtml) {
		teamspacesTemplate = '<div class="headlineSeparator">'+getLocalText("teamspaces")+'</div>' 
		teamspacesTemplate += teamspacesAsHtml.asString()
	}
	
	/* scheduler */
	def schedulerTemplate = ''
	SafeHtml schedulerAsHtml = schedulerService.getReportJobListAsHtml(rDto)
	if (null != schedulerAsHtml) {
		schedulerTemplate = '<div class="headlineSeparator">'+getLocalText("scheduler")+'</div>' 
		schedulerTemplate += schedulerAsHtml.asString()
	}
	
	def htmlTemplate = '''<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	 <head>
	  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	  <title>${getLocalText("reportDoku")}</title>  
	  <style type="text/css">

	    @page {
		  size: A4 portrait;
		  margin-top:1.5cm;
	
	 	 @top-left {
	       content: "${reportName}";
		   font-family: '${font}', Sans-Serif;
		   font-size: 8pt;
	     }
	     @top-right {
		  content: "${now}";
		  font-family: '${font}', Sans-Serif;
		  font-size: 8pt;
		 }
		 @bottom-left {
		  content: "UuId: ${uuid}";
		  font-family: '${font}', Sans-Serif;
		  font-size: 8pt;
		}
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
			margin-top: 6px;
		}
		
		.berechnetesFeldValue { font-family: monospace; }
			
		.headlineSeparator { 
			 
		  	margin: 0 auto; margin-bottom: 5px; margin-top: 20px; padding-top: 10px; padding-bottom: 1px;
			color: #000000; border-bottom: 1px solid #000000; 
			font-size: 15pt; font-weight: bold;  }
			
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
		      <div class="currentDate">${now}</div>
		  </div>
		  <div class="break"></div> 
		</div> 
		<div class="fetteLinie"></div> 
		<div class='container'> 
		  <div class='columnLlarge'>
		    <div class="reportDesc">${desc}</div>
		  </div> 
		  <div class='columnRsmall'> 
		    <div>
				<div class="metadatacontainer">
					<div class="metadata">
					  <div class="key">${getLocalText("created")}:</div>
		  			  <div class="value">${created}</div>
		  			</div>
					<div class="metadata">
					  <div class="key">${getLocalText("lastChanged")}:</div>
		  			  <div class="value">${changed}</div>
		  			</div>
					<div class="metadata">
					  <div class="key">${getLocalText("creator")}:</div>
		  			  <div class="value">${owner}</div>
		  			</div>
					<div class="metadata">
					  <div class="key">Id:</div>
		  			  <div class="value">${reportId}</div>
		  			</div>
		  			
		  			<div class="break"></div>
		  		</div>
		    </div>
		  </div> 
	  	  <div class="break"></div> 
		</div> 
		
		${mainConfig}

		${variants}
		
		${parameter}
		
		${compCol}
				
		${vorfilter}
		
		${spalten}

		${teamspaces}

		${scheduler}
		
		
	</body>
	</html>'''
						
	def htmlspecialchars(String str){
		//return str.replace("<", "&lt;").replace(">", "&gt;");
		return StringEscapeUtils.escapeHtml4(str).replace("\r\n", "\n").replace("\n", "<br />")
	}
	
	def sanitizeBinding(binding){
		return binding.collectEntries{ k, v -> [k, htmlspecialchars(String.valueOf(v))] }
	}
	
	/* User-defined variables, so we have to sanitize the text */
	def usrBinding = [
		'parentReport':		(report instanceof ReportVariant ? report.getParent()?.getName() : '' ),
		'owner':			report.getOwner()?.getFirstname() + " " + report.getOwner()?.getLastname(),
		'changed':			formatDate(report.getLastUpdated()),
		'created':			formatDate(report.getCreatedOn()),
		'desc':				null==report.getDescription()?"":report.getDescription(),
		'now': 				formatDate(new Date()), 
		'reportName': 		tsObject instanceof TsDiskReportReference ? ((TsDiskReportReference)tsObject).getName() : report.getName(), 
		'fitForPdf': 		fitForPdf, 
		'reportId':			report.getId(),
		'uuid':				report.getUuid()
		]
	/* The variables here are set in this template */
	def safeBinding = [
		'variants':			variantsTemplate,
		'teamspaces':		teamspacesTemplate,
		'scheduler':		schedulerTemplate,
		'spalten': 			spaltenTemplate,
		'parameter': 		parameterTemplate,
		'vorfilter': 		vorfilterTemplate,
		'compCol': 			computedColTemplate,
		'mainConfig': 		mainConfigTemplate, 
		'baseurl':		 	baseurl,
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