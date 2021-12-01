/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
package net.datenwerke.rs.jxlsreport.service.jxlsreport.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.converter.AbstractExcelConverter;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.converter.ExcelToHtmlUtils;
import org.apache.poi.hwpf.converter.HtmlDocumentFacade;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * 
 * adapted from  Sergey Vladimirov (vlsergey {at} gmail {dot} com)
 */
public class XlsxToHtmlConverter extends AbstractExcelConverter
{
	static final String EMPTY = "";
	

    private static final POILogger logger = POILogFactory
            .getLogger( ExcelToHtmlConverter.class );


    private String cssClassContainerCell = null;

    private String cssClassContainerDiv = null;

    private String cssClassPrefixCell = "c";

    private String cssClassPrefixDiv = "d";

    private String cssClassPrefixRow = "r";

    private String cssClassPrefixTable = "t";

    private Map<Short, String> excelStyleToClass = new LinkedHashMap<Short, String>();

    private final HtmlDocumentFacade htmlDocumentFacade;

    private boolean useDivsToSpan = false;

    public XlsxToHtmlConverter( Document doc )
    {
        htmlDocumentFacade = new HtmlDocumentFacade( doc );
    }

    public XlsxToHtmlConverter( HtmlDocumentFacade htmlDocumentFacade )
    {
        this.htmlDocumentFacade = htmlDocumentFacade;
    }

    protected String buildStyle( XSSFWorkbook workbook, XSSFCellStyle cellStyle )
    {
        StringBuilder style = new StringBuilder();

        style.append( "white-space:pre-wrap;" );
        ExcelToHtmlUtils.appendAlign( style, cellStyle.getAlignment() );

        if ( cellStyle.getFillPattern() == FillPatternType.NO_FILL )
        {
            // no fill
        }
        else if ( cellStyle.getFillPattern() == FillPatternType.SOLID_FOREGROUND )
        {
            final XSSFColor foregroundColor = cellStyle
                    .getFillForegroundColorColor();
            if ( foregroundColor != null )
                style.append( "background-color:"
                        + getColor( foregroundColor ) + ";" );
        }
        else
        {
            final XSSFColor backgroundColor = cellStyle
                    .getFillBackgroundColorColor();
            if ( backgroundColor != null )
                style.append( "background-color:"
                        + getColor( backgroundColor ) + ";" );
        }

        buildStyle_border( workbook, style, "top", cellStyle.getBorderTop(),
                cellStyle.getTopBorderColor() );
        buildStyle_border( workbook, style, "right",
                cellStyle.getBorderRight(), cellStyle.getRightBorderColor() );
        buildStyle_border( workbook, style, "bottom",
                cellStyle.getBorderBottom(), cellStyle.getBottomBorderColor() );
        buildStyle_border( workbook, style, "left", cellStyle.getBorderLeft(),
                cellStyle.getLeftBorderColor() );

        XSSFFont font = cellStyle.getFont();
        buildStyle_font( workbook, style, font );

        return style.toString();
    }

    private void buildStyle_border( XSSFWorkbook workbook, StringBuilder style,
            String type, BorderStyle xlsBorder, short borderColor )
    {
        if ( xlsBorder == BorderStyle.NONE )
            return;

        StringBuilder borderStyle = new StringBuilder();
        borderStyle.append( ExcelToHtmlUtils.getBorderWidth( xlsBorder ) );
        borderStyle.append( ' ' );
        borderStyle.append( ExcelToHtmlUtils.getBorderStyle( xlsBorder ) );

        style.append( "border-" + type + ":" + borderStyle + ";" );
    }

    void buildStyle_font( XSSFWorkbook workbook, StringBuilder style,
            XSSFFont font )
    {
        if (font.getBold()) 
            style.append( "font-weight:bold;" );

        if ( font.getFontHeightInPoints() != 0 )
            style.append( "font-size:" + font.getFontHeightInPoints() + "pt;" );

        if ( font.getItalic() )
        {
            style.append( "font-style:italic;" );
        }
    }

    public String getCssClassPrefixCell()
    {
        return cssClassPrefixCell;
    }

    public String getCssClassPrefixDiv()
    {
        return cssClassPrefixDiv;
    }

    public String getCssClassPrefixRow()
    {
        return cssClassPrefixRow;
    }

    public String getCssClassPrefixTable()
    {
        return cssClassPrefixTable;
    }

    public Document getDocument()
    {
        return htmlDocumentFacade.getDocument();
    }

    protected String getStyleClassName( XSSFWorkbook workbook,
            XSSFCellStyle cellStyle )
    {
        final Short cellStyleKey = Short.valueOf( cellStyle.getIndex() );

        String knownClass = excelStyleToClass.get( cellStyleKey );
        if ( knownClass != null )
            return knownClass;

        String cssStyle = buildStyle( workbook, cellStyle );
        String cssClass = htmlDocumentFacade.getOrCreateCssClass(
                cssClassPrefixCell, cssStyle );
        excelStyleToClass.put( cellStyleKey, cssClass );
        return cssClass;
    }

    public boolean isUseDivsToSpan()
    {
        return useDivsToSpan;
    }

    protected boolean processCell( XSSFCell cell, Element tableCellElement,
            int normalWidthPx, int maxSpannedWidthPx, float normalHeightPt )
    {
        final XSSFCellStyle cellStyle = cell.getCellStyle();

        String value;
        switch ( cell.getCellType() )
        {
        case STRING:
            // XXX: enrich
            value = cell.getRichStringCellValue().getString();
            break;
        case FORMULA:
            switch ( cell.getCachedFormulaResultType() )
            {
            case STRING:
                XSSFRichTextString str = cell.getRichStringCellValue();
                if ( str != null && str.length() > 0 )
                {
                    value = ( str.toString() );
                }
                else
                {
                    value = EMPTY;
                }
                break;
            case NUMERIC:
                XSSFCellStyle style = cellStyle;
                if ( style == null )
                {
                    value = String.valueOf( cell.getNumericCellValue() );
                }
                else
                {
                    value = ( _formatter.formatRawCellContents(
                            cell.getNumericCellValue(), style.getDataFormat(),
                            style.getDataFormatString() ) );
                }
                break;
            case BOOLEAN:
                value = String.valueOf( cell.getBooleanCellValue() );
                break;
            case ERROR:
                value = ErrorEval.getText( cell.getErrorCellValue() );
                break;
            default:
                logger.log(
                        POILogger.WARN,
                        "Unexpected cell cachedFormulaResultType ("
                                + cell.getCachedFormulaResultType() + ")" );
                value = EMPTY;
                break;
            }
            break;
        case BLANK:
            value = EMPTY;
            break;
        case NUMERIC:
            value = _formatter.formatCellValue( cell );
            break;
        case BOOLEAN:
            value = String.valueOf( cell.getBooleanCellValue() );
            break;
        case ERROR:
            value = ErrorEval.getText( cell.getErrorCellValue() );
            break;
        default:
            logger.log( POILogger.WARN,
                    "Unexpected cell type (" + cell.getCellType() + ")" );
            return true;
        }

        final boolean noText = isEmpty( value );
        final boolean wrapInDivs = !noText && isUseDivsToSpan()
                && !cellStyle.getWrapText();

        final short cellStyleIndex = cellStyle.getIndex();
        if ( cellStyleIndex != 0 )
        {
            XSSFWorkbook workbook = cell.getRow().getSheet().getWorkbook();
            String mainCssClass = getStyleClassName( workbook, cellStyle );
            if ( wrapInDivs )
            {
                tableCellElement.setAttribute( "class", mainCssClass + " "
                        + cssClassContainerCell );
            }
            else
            {
                tableCellElement.setAttribute( "class", mainCssClass );
            }

            if ( noText )
            {
                /*
                 * if cell style is defined (like borders, etc.) but cell text
                 * is empty, add "&nbsp;" to output, so browser won't collapse
                 * and ignore cell
                 */
                value = "\u00A0";
            }
        }

        if ( isOutputLeadingSpacesAsNonBreaking() && value.startsWith( " " ) )
        {
            StringBuilder builder = new StringBuilder();
            for ( int c = 0; c < value.length(); c++ )
            {
                if ( value.charAt( c ) != ' ' )
                    break;
                builder.append( '\u00a0' );
            }

            if ( value.length() != builder.length() )
                builder.append( value.substring( builder.length() ) );

            value = builder.toString();
        }

        Text text = htmlDocumentFacade.createText( value );

        if ( wrapInDivs )
        {
            Element outerDiv = htmlDocumentFacade.createBlock();
            outerDiv.setAttribute( "class", this.cssClassContainerDiv );

            Element innerDiv = htmlDocumentFacade.createBlock();
            StringBuilder innerDivStyle = new StringBuilder();
            innerDivStyle.append( "position:absolute;min-width:" );
            innerDivStyle.append( normalWidthPx );
            innerDivStyle.append( "px;" );
            if ( maxSpannedWidthPx != Integer.MAX_VALUE )
            {
                innerDivStyle.append( "max-width:" );
                innerDivStyle.append( maxSpannedWidthPx );
                innerDivStyle.append( "px;" );
            }
            innerDivStyle.append( "overflow:hidden;max-height:" );
            innerDivStyle.append( normalHeightPt );
            innerDivStyle.append( "pt;white-space:nowrap;" );
            ExcelToHtmlUtils.appendAlign( innerDivStyle,
                    cellStyle.getAlignment() );
            htmlDocumentFacade.addStyleClass( outerDiv, cssClassPrefixDiv,
                    innerDivStyle.toString() );

            innerDiv.appendChild( text );
            outerDiv.appendChild( innerDiv );
            tableCellElement.appendChild( outerDiv );
        }
        else
        {
            tableCellElement.appendChild( text );
        }

        return isEmpty( value ) && cellStyleIndex == 0;
    }

    protected void processColumnHeaders( XSSFSheet sheet, int maxSheetColumns,
            Element table )
    {
        Element tableHeader = htmlDocumentFacade.createTableHeader();
        table.appendChild( tableHeader );

        Element tr = htmlDocumentFacade.createTableRow();

        if ( isOutputRowNumbers() )
        {
            // empty row at left-top corner
            tr.appendChild( htmlDocumentFacade.createTableHeaderCell() );
        }

        for ( int c = 0; c < maxSheetColumns; c++ )
        {
            if ( !isOutputHiddenColumns() && sheet.isColumnHidden( c ) )
                continue;

            Element th = htmlDocumentFacade.createTableHeaderCell();
            String text = getColumnName( c );
            th.appendChild( htmlDocumentFacade.createText( text ) );
            tr.appendChild( th );
        }
        tableHeader.appendChild( tr );
    }

    /**
     * Creates COLGROUP element with width specified for all columns. (Except
     * first if <tt>{@link #isOutputRowNumbers()}==true</tt>)
     */
    protected void processColumnWidths( XSSFSheet sheet, int maxSheetColumns,
            Element table )
    {
        // draw COLS after we know max column number
        Element columnGroup = htmlDocumentFacade.createTableColumnGroup();
        if ( isOutputRowNumbers() )
        {
            columnGroup.appendChild( htmlDocumentFacade.createTableColumn() );
        }
        for ( int c = 0; c < maxSheetColumns; c++ )
        {
            if ( !isOutputHiddenColumns() && sheet.isColumnHidden( c ) )
                continue;

            Element col = htmlDocumentFacade.createTableColumn();
            col.setAttribute( "width",
                    String.valueOf( getColumnWidth( sheet, c ) ) );
            columnGroup.appendChild( col );
        }
        table.appendChild( columnGroup );
    }

    /**
     * @return maximum 1-base index of column that were rendered, zero if none
     */
    protected int processRow( CellRangeAddress[][] mergedRanges, XSSFRow row,
            Element tableRowElement )
    {
        final XSSFSheet sheet = row.getSheet();
        final short maxColIx = row.getLastCellNum();
        if ( maxColIx <= 0 )
            return 0;

        final List<Element> emptyCells = new ArrayList<Element>( maxColIx );

        if ( isOutputRowNumbers() )
        {
            Element tableRowNumberCellElement = htmlDocumentFacade
                    .createTableHeaderCell();
            processRowNumber( row, tableRowNumberCellElement );
            emptyCells.add( tableRowNumberCellElement );
        }

        int maxRenderedColumn = 0;
        for ( int colIx = 0; colIx < maxColIx; colIx++ )
        {
            if ( !isOutputHiddenColumns() && sheet.isColumnHidden( colIx ) )
                continue;

            CellRangeAddress range = ExcelToHtmlUtils.getMergedRange(
                    mergedRanges, row.getRowNum(), colIx );

            if ( range != null
                    && ( range.getFirstColumn() != colIx || range.getFirstRow() != row
                            .getRowNum() ) )
                continue;

            XSSFCell cell = row.getCell( colIx );

            int divWidthPx = 0;
            if ( isUseDivsToSpan() )
            {
                divWidthPx = getColumnWidth( sheet, colIx );

                boolean hasBreaks = false;
                for ( int nextColumnIndex = colIx + 1; nextColumnIndex < maxColIx; nextColumnIndex++ )
                {
                    if ( !isOutputHiddenColumns()
                            && sheet.isColumnHidden( nextColumnIndex ) )
                        continue;

                    if ( row.getCell( nextColumnIndex ) != null
                            && !isTextEmpty( row.getCell( nextColumnIndex ) ) )
                    {
                        hasBreaks = true;
                        break;
                    }

                    divWidthPx += getColumnWidth( sheet, nextColumnIndex );
                }

                if ( !hasBreaks )
                    divWidthPx = Integer.MAX_VALUE;
            }

            Element tableCellElement = htmlDocumentFacade.createTableCell();

            if ( range != null )
            {
                if ( range.getFirstColumn() != range.getLastColumn() )
                    tableCellElement.setAttribute(
                            "colspan",
                            String.valueOf( range.getLastColumn()
                                    - range.getFirstColumn() + 1 ) );
                if ( range.getFirstRow() != range.getLastRow() )
                    tableCellElement.setAttribute(
                            "rowspan",
                            String.valueOf( range.getLastRow()
                                    - range.getFirstRow() + 1 ) );
            }

            boolean emptyCell;
            if ( cell != null )
            {
                emptyCell = processCell( cell, tableCellElement,
                        getColumnWidth( sheet, colIx ), divWidthPx,
                        row.getHeight() / 20f );
            }
            else
            {
                emptyCell = true;
            }

            if ( emptyCell )
            {
                emptyCells.add( tableCellElement );
            }
            else
            {
                for ( Element emptyCellElement : emptyCells )
                {
                    tableRowElement.appendChild( emptyCellElement );
                }
                emptyCells.clear();

                tableRowElement.appendChild( tableCellElement );
                maxRenderedColumn = colIx;
            }
        }

        return maxRenderedColumn + 1;
    }

    protected void processRowNumber( XSSFRow row,
            Element tableRowNumberCellElement )
    {
        tableRowNumberCellElement.setAttribute( "class", "rownumber" );
        Text text = htmlDocumentFacade.createText( getRowName( row ) );
        tableRowNumberCellElement.appendChild( text );
    }

    protected void processSheet( XSSFSheet sheet )
    {
        processSheetHeader( htmlDocumentFacade.getBody(), sheet );

        final int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        if ( physicalNumberOfRows <= 0 )
            return;

        Element table = htmlDocumentFacade.createTable();
        htmlDocumentFacade.addStyleClass( table, cssClassPrefixTable,
                "border-collapse:collapse;border-spacing:0;" );

        Element tableBody = htmlDocumentFacade.createTableBody();

        final CellRangeAddress[][] mergedRanges = buildMergedRangesMap( sheet );

        final List<Element> emptyRowElements = new ArrayList<Element>(
                physicalNumberOfRows );
        int maxSheetColumns = 1;
        for ( int r = sheet.getFirstRowNum(); r <= sheet.getLastRowNum(); r++ )
        {
            XSSFRow row = sheet.getRow( r );

            if ( row == null )
                continue;

            if ( !isOutputHiddenRows() && row.getZeroHeight() )
                continue;

            Element tableRowElement = htmlDocumentFacade.createTableRow();
            htmlDocumentFacade.addStyleClass( tableRowElement,
                    cssClassPrefixRow, "height:" + ( row.getHeight() / 20f )
                            + "pt;" );

            int maxRowColumnNumber = processRow( mergedRanges, row,
                    tableRowElement );

            if ( maxRowColumnNumber == 0 )
            {
                emptyRowElements.add( tableRowElement );
            }
            else
            {
                if ( !emptyRowElements.isEmpty() )
                {
                    for ( Element emptyRowElement : emptyRowElements )
                    {
                        tableBody.appendChild( emptyRowElement );
                    }
                    emptyRowElements.clear();
                }

                tableBody.appendChild( tableRowElement );
            }
            maxSheetColumns = Math.max( maxSheetColumns, maxRowColumnNumber );
        }

        processColumnWidths( sheet, maxSheetColumns, table );

        if ( isOutputColumnHeaders() )
        {
            processColumnHeaders( sheet, maxSheetColumns, table );
        }

        table.appendChild( tableBody );

        htmlDocumentFacade.getBody().appendChild( table );
    }

    protected void processSheetHeader( Element htmlBody, XSSFSheet sheet )
    {
        Element h2 = htmlDocumentFacade.createHeader2();
        h2.appendChild( htmlDocumentFacade.createText( sheet.getSheetName() ) );
        htmlBody.appendChild( h2 );
    }

    public void processWorkbook( XSSFWorkbook workbook )
    {
        if ( isUseDivsToSpan() )
        {
            // prepare CSS classes for later usage
            this.cssClassContainerCell = htmlDocumentFacade
                    .getOrCreateCssClass( cssClassPrefixCell,
                            "padding:0;margin:0;align:left;vertical-align:top;" );
            this.cssClassContainerDiv = htmlDocumentFacade.getOrCreateCssClass(
                    cssClassPrefixDiv, "position:relative;" );
        }

        for ( int s = 0; s < workbook.getNumberOfSheets(); s++ )
        {
            XSSFSheet sheet = workbook.getSheetAt( s );
            processSheet( sheet );
        }

        htmlDocumentFacade.updateStylesheet();
    }

    public void setCssClassPrefixCell( String cssClassPrefixCell )
    {
        this.cssClassPrefixCell = cssClassPrefixCell;
    }

    public void setCssClassPrefixDiv( String cssClassPrefixDiv )
    {
        this.cssClassPrefixDiv = cssClassPrefixDiv;
    }

    public void setCssClassPrefixRow( String cssClassPrefixRow )
    {
        this.cssClassPrefixRow = cssClassPrefixRow;
    }

    public void setCssClassPrefixTable( String cssClassPrefixTable )
    {
        this.cssClassPrefixTable = cssClassPrefixTable;
    }

    /**
     * Allows converter to wrap content into two additional DIVs with tricky
     * styles, so it will wrap across empty cells (like in Excel).
     * <p>
     * <b>Warning:</b> after enabling this mode do not serialize result HTML
     * with INDENT=YES option, because line breaks will make additional
     * (unwanted) changes
     */
    public void setUseDivsToSpan( boolean useDivsToSpan )
    {
        this.useDivsToSpan = useDivsToSpan;
    }
    
    static boolean isEmpty( String str )
    {
        return str == null || str.length() == 0;
    }

    static boolean isNotEmpty( String str )
    {
        return !isEmpty( str );
    }
    
    protected static int getColumnWidth( XSSFSheet sheet, int columnIndex )
    {
        return ExcelToHtmlUtils.getColumnWidthInPx( sheet
                .getColumnWidth( columnIndex ) );
    }

    protected static int getDefaultColumnWidth( XSSFSheet sheet )
    {
        return ExcelToHtmlUtils.getColumnWidthInPx( sheet
                .getDefaultColumnWidth() );
    }
    
    public static CellRangeAddress[][] buildMergedRangesMap( XSSFSheet sheet )
    {
        CellRangeAddress[][] mergedRanges = new CellRangeAddress[1][];
        for ( int m = 0; m < sheet.getNumMergedRegions(); m++ )
        {
            final CellRangeAddress cellRangeAddress = sheet.getMergedRegion( m );

            final int requiredHeight = cellRangeAddress.getLastRow() + 1;
            if ( mergedRanges.length < requiredHeight )
            {
                CellRangeAddress[][] newArray = new CellRangeAddress[requiredHeight][];
                System.arraycopy( mergedRanges, 0, newArray, 0,
                        mergedRanges.length );
                mergedRanges = newArray;
            }

            for ( int r = cellRangeAddress.getFirstRow(); r <= cellRangeAddress
                    .getLastRow(); r++ )
            {
                final int requiredWidth = cellRangeAddress.getLastColumn() + 1;

                CellRangeAddress[] rowMerged = mergedRanges[r];
                if ( rowMerged == null )
                {
                    rowMerged = new CellRangeAddress[requiredWidth];
                    mergedRanges[r] = rowMerged;
                }
                else
                {
                    final int rowMergedLength = rowMerged.length;
                    if ( rowMergedLength < requiredWidth )
                    {
                        final CellRangeAddress[] newRow = new CellRangeAddress[requiredWidth];
                        System.arraycopy( rowMerged, 0, newRow, 0,
                                rowMergedLength );

                        mergedRanges[r] = newRow;
                        rowMerged = newRow;
                    }
                }

                Arrays.fill( rowMerged, cellRangeAddress.getFirstColumn(),
                        cellRangeAddress.getLastColumn() + 1, cellRangeAddress );
            }
        }
        return mergedRanges;
    }
    
    protected String getRowName( XSSFRow row )
    {
        return String.valueOf( row.getRowNum() + 1 );
    }

    protected boolean isTextEmpty( XSSFCell cell )
    {
        final String value;
        switch ( cell.getCellType() )
        {
        case STRING:
            // XXX: enrich
            value = cell.getRichStringCellValue().getString();
            break;
        case FORMULA:
            switch ( cell.getCachedFormulaResultType() )
            {
            case STRING:
                XSSFRichTextString str = cell.getRichStringCellValue();
                if ( str == null || str.length() <= 0 )
                    return false;

                value = str.toString();
                break;
            case NUMERIC:
                XSSFCellStyle style = cell.getCellStyle();
                if ( style == null )
                {
                    return false;
                }

                value = ( _formatter.formatRawCellContents(
                        cell.getNumericCellValue(), style.getDataFormat(),
                        style.getDataFormatString() ) );
                break;
            case BOOLEAN:
                value = String.valueOf( cell.getBooleanCellValue() );
                break;
            case ERROR:
                value = ErrorEval.getText( cell.getErrorCellValue() );
                break;
            default:
                value = EMPTY;
                break;
            }
            break;
        case BLANK:
            value = EMPTY;
            break;
        case NUMERIC:
            value = _formatter.formatCellValue( cell );
            break;
        case BOOLEAN:
            value = String.valueOf( cell.getBooleanCellValue() );
            break;
        case ERROR:
            value = ErrorEval.getText( cell.getErrorCellValue() );
            break;
        default:
            return true;
        }

        return isEmpty( value );
    }
    
    public static String getColor( XSSFColor color )
    {
        StringBuilder stringBuilder = new StringBuilder( 7 );
        stringBuilder.append( '#' ).append(color.getARGBHex().substring(2));
        return stringBuilder.toString();
    }

}
