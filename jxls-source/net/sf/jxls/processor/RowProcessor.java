package net.sf.jxls.processor;

import java.util.Map;

import net.sf.jxls.transformer.Row;

/**
 * Allows dynamic processing of rows
 * @author <a href="mailto:Lvissochin@db.luxoft.com">Leonid Vysochin</a>
 */
public interface RowProcessor {
    void processRow(Row row, Map namedCells);
}
