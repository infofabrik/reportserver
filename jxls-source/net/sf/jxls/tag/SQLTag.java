package net.sf.jxls.tag;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.report.ReportManager;
import net.sf.jxls.transformation.ResultTransformation;
import net.sf.jxls.transformer.Configuration;
import net.sf.jxls.transformer.SheetTransformer;
import net.sf.jxls.util.ReportUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Leonid Vysochyn
 */
public class SQLTag extends BaseTag {
    protected static final Log log = LogFactory.getLog(SQLTag.class);

    Configuration configuration = new Configuration();

    public static final String REPORT_MANAGER_KEY = "reportManager";

    String query;
    String ref;
    String var;
    String select;

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public ResultTransformation process(SheetTransformer sheetTransformer) {
        ResultTransformation shift = new ResultTransformation(0);
        if (query != null) {
            if (tagContext.getBeans().containsKey(REPORT_MANAGER_KEY)) {
                ReportManager reportManager = (ReportManager) tagContext.getBeans().get(REPORT_MANAGER_KEY);
                try {
                    List results = reportManager.exec(query);
                    int shiftNumber = 0;
                    Block body = tagContext.getTagBody();
                    if (results != null && !results.isEmpty()) {
                        tagContext.getSheetTransformationController().removeBorders(body);
                        shiftNumber += -2; // due to the borders
                        Map beans = tagContext.getBeans();
                        int k = 0;
                        ResultTransformation processResult;
                        int startRowNum, endRowNum;
                        Collection c2 = new ArrayList();
                        for (Iterator iterator = results.iterator(); iterator.hasNext();) {
                            Object o = iterator.next();
                            beans.put(var, o);
                            if (ReportUtil.shouldSelectCollectionData(beans, select, configuration)) {
                                c2.add(o);
                            }
                        }
                        shiftNumber += tagContext.getSheetTransformationController().duplicateDown(body, c2.size() - 1);
                        for (Iterator iterator = c2.iterator(); iterator.hasNext();) {
                            Object o = iterator.next();
                            beans.put(var, o);
                            //                        if (ReportUtil.shouldSelectCollectionData(beans, select, configuration)) {
                            try {
                                startRowNum = body.getStartRowNum() + shift.getLastRowShift() + body.getNumberOfRows() * k++;
                                endRowNum = startRowNum + body.getNumberOfRows() - 1;
                                processResult = sheetTransformer.processRows(tagContext.getSheetTransformationController(), tagContext.getSheet(), startRowNum, endRowNum, beans, null);
                                shift.add(processResult);
                            } catch (ParsePropertyException e) {
                                log.error("Can't parse property ", e);
                                throw new RuntimeException("Can't parse property", e);
                            }
                            //                        }
                        }
                        shift.add(new ResultTransformation(shiftNumber, shiftNumber));
                        shift.setTagProcessResult(true);
                        return shift;
                    }
                    log.warn("Result set for query: " + query + " is empty");
                    tagContext.getSheetTransformationController().removeBodyRows(body);
                    shift.add(new ResultTransformation(-1, -body.getNumberOfRows()));
                    shift.setLastProcessedRow(-1);
                    shift.setTagProcessResult(true);
                    return shift;
                } catch (SQLException e) {
                    log.error("Can't execute query " + query, e);
                }
            } else {
                log.error("Can't find ReportManager bean in the tag context under " + REPORT_MANAGER_KEY + " key.");
            }
        }
        return shift;
    }
}
