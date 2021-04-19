package net.sf.jxls.transformer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

import net.sf.jxls.tag.JxTaglib;
import net.sf.jxls.tag.TagLib;

import org.apache.commons.digester.Digester;

/**
 * Defines configuration properties for XLS transformation
 * @author Leonid Vysochyn
 */
public class Configuration {
    private String metaInfoToken = "//";
    private String startExpressionToken = "${";
    private String endExpressionToken = "}";
    private String startFormulaToken = "$[";
    private String endFormulaToken = "]";

    private String startFormulaPartToken = "{";
    private String endFormulaPartToken = "}";

    private static final String tagPrefix = "jx:";
    private static final String tagPrefixWithBrace = "<jx:";
    private static final String forTagName = "forEach";
    private static final String forTagItems = "items";
    private static final String forTagVar = "var";

    boolean isUTF16 = false;
    private HashMap tagLibs = new HashMap();
    private Digester digester;
    private String jxlsRoot;
    private boolean encodeXMLAttributes = true;
    String sheetKeyName = "sheet";
    String workbookKeyName = "workbook";
    String rowKeyName = "hssfRow";
    String cellKeyName = "hssfCell";

    private String excludeSheetProcessingMark = "#Exclude";
    boolean removeExcludeSheetProcessingMark = false;
    Set excludeSheets = new HashSet();

    public Configuration() {
        registerTagLib(new JxTaglib(), "jx");
    }

    public Configuration(String startExpressionToken, String endExpressionToken, String startFormulaToken, String endFormulaToken, String metaInfoToken) {
        this.startExpressionToken = startExpressionToken;
        this.endExpressionToken = endExpressionToken;
        this.startFormulaToken = startFormulaToken;
        this.endFormulaToken = endFormulaToken;
        this.metaInfoToken = metaInfoToken;
        registerTagLib(new JxTaglib(), "jx");
    }

    public Configuration(String startExpressionToken, String endExpressionToken, String startFormulaToken, String endFormulaToken, String metaInfoToken, boolean isUTF16) {
        this.startExpressionToken = startExpressionToken;
        this.endExpressionToken = endExpressionToken;
        this.startFormulaToken = startFormulaToken;
        this.endFormulaToken = endFormulaToken;
        this.metaInfoToken = metaInfoToken;
        this.isUTF16 = isUTF16;
        registerTagLib(new JxTaglib(), "jx");
    }

    public static final String NAMESPACE_URI = "http://jxls.sourceforge.net/jxls";
    public static final String JXLS_ROOT_TAG = "jxls";
    public static final String JXLS_ROOT_START = "<jx:jxls xmlns:jx=\"" + NAMESPACE_URI + "\">";
    public static final String JXLS_ROOT_END = "</jx:jxls>";


    private boolean jexlInnerCollectionsAccess;

    public boolean isJexlInnerCollectionsAccess() {
        return jexlInnerCollectionsAccess;
    }

    public void setJexlInnerCollectionsAccess(boolean jexlInnerCollectionsAccess) {
        this.jexlInnerCollectionsAccess = jexlInnerCollectionsAccess;
    }

    public boolean isUTF16() {
        return isUTF16;
    }

    public void setUTF16(boolean UTF16) {
        this.isUTF16 = UTF16;
    }

    public String getSheetKeyName() {
        return sheetKeyName;
    }

    public void setSheetKeyName(String sheetKeyName) {
        this.sheetKeyName = sheetKeyName;
    }

    public String getWorkbookKeyName() {
        return workbookKeyName;
    }

    public void setWorkbookKeyName(String workbookKeyName) {
        this.workbookKeyName = workbookKeyName;
    }


    public String getRowKeyName() {
        return rowKeyName;
    }

    public void setRowKeyName(String rowKeyName) {
        this.rowKeyName = rowKeyName;
    }

    public String getCellKeyName() {
        return cellKeyName;
    }

    public void setCellKeyName(String cellKeyName) {
        this.cellKeyName = cellKeyName;
    }

    public String getTagPrefix() {
        return tagPrefix;
    }

    public String getTagPrefixWithBrace() {
        return tagPrefixWithBrace;
    }

    public String getForTagName() {
        return forTagName;
    }

    public String getForTagItems() {
        return forTagItems;
    }

    public String getForTagVar() {
        return forTagVar;
    }

    public String getMetaInfoToken() {
        return metaInfoToken;
    }

    public void setMetaInfoToken(String metaInfoToken) {
        this.metaInfoToken = metaInfoToken;
    }

    public String getStartExpressionToken() {
        return startExpressionToken;
    }

    public void setStartExpressionToken(String startExpressionToken) {
        this.startExpressionToken = startExpressionToken;
    }

    public String getEndExpressionToken() {
        return endExpressionToken;
    }

    public void setEndExpressionToken(String endExpressionToken) {
        this.endExpressionToken = endExpressionToken;
    }

    public String getStartFormulaToken() {
        return startFormulaToken;
    }

    public void setStartFormulaToken(String startFormulaToken) {
        this.startFormulaToken = startFormulaToken;
    }

    public String getEndFormulaToken() {
        return endFormulaToken;
    }

    public void setEndFormulaToken(String endFormulaToken) {
        this.endFormulaToken = endFormulaToken;
    }


    public String getStartFormulaPartToken() {
        return startFormulaPartToken;
    }

    public void setStartFormulaPartToken(String startFormulaPartToken) {
        this.startFormulaPartToken = startFormulaPartToken;
    }

    public String getEndFormulaPartToken() {
        return endFormulaPartToken;
    }

    public void setEndFormulaPartToken(String endFormulaPartToken) {
        this.endFormulaPartToken = endFormulaPartToken;
    }

    public String getExcludeSheetProcessingMark() {
        return excludeSheetProcessingMark;
    }

    public void setExcludeSheetProcessingMark(String excludeSheetProcessingMark) {
        this.excludeSheetProcessingMark = excludeSheetProcessingMark;
    }

    public boolean isRemoveExcludeSheetProcessingMark() {
        return removeExcludeSheetProcessingMark;
    }

    public void setRemoveExcludeSheetProcessingMark(boolean removeExcludeSheetProcessingMark) {
        this.removeExcludeSheetProcessingMark = removeExcludeSheetProcessingMark;
    }

    public void registerTagLib(TagLib tagLib, String namespace) {

        if (this.tagLibs.containsKey(namespace)) {
            throw new RuntimeException("Duplicate tag-lib namespace: " + namespace);
        }

        this.tagLibs.put(namespace, tagLib);
    }

    public Digester getDigester() {

        synchronized (this) {
            if (digester == null) {
                initDigester();
            }
        }

        return digester;
    }

    private void initDigester() {
        digester = new Digester();
        digester.setNamespaceAware(true);
        digester.setValidating(false);

        StringBuffer sb = new StringBuffer();
        sb.append("<jxls ");
        boolean firstTime = true;

        Map.Entry entry = null;

        for (Iterator itr = tagLibs.entrySet().iterator(); itr.hasNext();) {

            entry = (Map.Entry) itr.next();

            String namespace = (String) entry.getKey();
            String namespaceURI = Configuration.NAMESPACE_URI + "/" + namespace;
            digester.setRuleNamespaceURI(namespaceURI);

            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(" ");
            }
            sb.append("xmlns:");
            sb.append(namespace);
            sb.append("=\"");
            sb.append(namespaceURI);
            sb.append("\"");

            TagLib tagLib = (TagLib) entry.getValue();
            Map.Entry tagEntry = null;
            for (Iterator itr2 = tagLib.getTags().entrySet().iterator(); itr2.hasNext();) {
                tagEntry = (Map.Entry) itr2.next();
                digester.addObjectCreate(Configuration.JXLS_ROOT_TAG + "/" + tagEntry.getKey(), (String) tagEntry.getValue());
                digester.addSetProperties(Configuration.JXLS_ROOT_TAG + "/" + tagEntry.getKey());
            }
        }

        sb.append(">");
        this.jxlsRoot = sb.toString();
    }

    public String getJXLSRoot() {

        synchronized(this) {
            if (jxlsRoot == null) {
                initDigester();
            }
        }

        return jxlsRoot;
    }

    public Set getExcludeSheets() {
        return this.excludeSheets;
    }

    public void addExcludeSheet(String name) {
       this.excludeSheets.add(name);
    }

    public String getJXLSRootEnd() {
        return "</jxls>";
    }

    public boolean getEncodeXMLAttributes() {
        return encodeXMLAttributes;
    }

    public void setEncodeXMLAttributes(boolean encodeXMLAttributes) {
        this.encodeXMLAttributes = encodeXMLAttributes;
    }
}
