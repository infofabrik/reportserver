package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.generator;

import java.io.OutputStream;
import java.io.StringWriter;
import java.sql.Connection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;

import com.google.inject.Inject;

import net.datenwerke.async.DwAsyncService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledHtmlReportImpl;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.utils.XlsxToHtmlConverter;

public class JxlsHTMLOutputGenerator extends JxlsOutputGeneratorImpl {

	@Inject
	public JxlsHTMLOutputGenerator(HookHandlerService hookHandler, DwAsyncService asyncService) {
		super(hookHandler, asyncService);
	}
	
	@Override
	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_HTML};
	}
	
	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledHtmlReportImpl(null);
	}
	
	@Override
	public CompiledReport exportReport(OutputStream os, byte[] template,
			Connection connection, JxlsReport jxlsReport, ParameterSet parameters,
			String outputFormat, ReportExecutionConfig... configs)
			throws ReportExecutorException {
	    if(jxlsReport.isJxlsOne()) {
            Workbook workbook = processWorkbookLegacy(parameters, template, connection);
            return exportReportLegacy(os, workbook);
        } else {
            Workbook workbook = processWorkbook(parameters, template, connection, jxlsReport, outputFormat);
            return exportReport(os, workbook);
        }
	}
	
	private CompiledReport exportReportLegacy(OutputStream os, Workbook workbook) throws ReportExecutorException {
        try{
            Document xmlDocument = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();
            
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            transformer.setOutputProperty( OutputKeys.INDENT, "no" );
            transformer.setOutputProperty( OutputKeys.ENCODING, "utf-8" );
            transformer.setOutputProperty( OutputKeys.METHOD, "html" );

            String html = null;
            
            if(workbook instanceof HSSFWorkbook){
                ExcelToHtmlConverter xlsToHtmlConverter = new ExcelToHtmlConverter(xmlDocument);
                xlsToHtmlConverter.processWorkbook(  (HSSFWorkbook) workbook );
                
                if(null == os){
                    StringWriter stringWriter = new StringWriter();
                    transformer.transform(
                            new DOMSource( xlsToHtmlConverter.getDocument() ),
                            new StreamResult( stringWriter ) );
            
                    html = stringWriter.toString();
                } else {
                     transformer.transform(
                                new DOMSource( xlsToHtmlConverter.getDocument() ),
                                new StreamResult(os) );
                }
            } else if(workbook instanceof XSSFWorkbook) {
                XlsxToHtmlConverter xlsToHtmlConverter = new XlsxToHtmlConverter(xmlDocument);
                xlsToHtmlConverter.processWorkbook(  (XSSFWorkbook) workbook );
                
                if(null == os){
                    StringWriter stringWriter = new StringWriter();
                    transformer.transform(
                            new DOMSource( xlsToHtmlConverter.getDocument() ),
                            new StreamResult( stringWriter ) );
            
                    html = stringWriter.toString();
                } else {
                     transformer.transform(
                                new DOMSource( xlsToHtmlConverter.getDocument() ),
                                new StreamResult(os) );
                }
            } else {
                html = "Could not convert file";
            }
            
            return new CompiledHtmlReportImpl(html);
        } catch(Exception e){
            throw new ReportExecutorException(e);
        }
    }
    
    private CompiledReport exportReport(OutputStream os, Workbook workbook) throws ReportExecutorException {
        try{
            Document xmlDocument = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();
            
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            transformer.setOutputProperty( OutputKeys.INDENT, "no" );
            transformer.setOutputProperty( OutputKeys.ENCODING, "utf-8" );
            transformer.setOutputProperty( OutputKeys.METHOD, "html" );

            String html = null;
            
            if(workbook instanceof HSSFWorkbook){
                ExcelToHtmlConverter xlsToHtmlConverter = new ExcelToHtmlConverter(xmlDocument);
                xlsToHtmlConverter.processWorkbook(  (HSSFWorkbook) workbook );
                
                if(null == os){
                    StringWriter stringWriter = new StringWriter();
                    transformer.transform(
                            new DOMSource( xlsToHtmlConverter.getDocument() ),
                            new StreamResult( stringWriter ) );
            
                    html = stringWriter.toString();
                } else {
                     transformer.transform(
                                new DOMSource( xlsToHtmlConverter.getDocument() ),
                                new StreamResult(os) );
                }
            } else if(workbook instanceof XSSFWorkbook) {
                XlsxToHtmlConverter xlsToHtmlConverter = new XlsxToHtmlConverter(xmlDocument);
                xlsToHtmlConverter.processWorkbook(  (XSSFWorkbook) workbook );
                
                if(null == os){
                    StringWriter stringWriter = new StringWriter();
                    transformer.transform(
                            new DOMSource( xlsToHtmlConverter.getDocument() ),
                            new StreamResult( stringWriter ) );
            
                    html = stringWriter.toString();
                } else {
                     transformer.transform(
                                new DOMSource( xlsToHtmlConverter.getDocument() ),
                                new StreamResult(os) );
                }
            } else {
                html = "Could not convert file";
            }
            
            return new CompiledHtmlReportImpl(html);
        } catch(Exception e){
            throw new ReportExecutorException(e);
        }
    }
}
