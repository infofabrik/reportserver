package org.legacysaiku.olap.util;

import java.util.Comparator;

import org.legacysaiku.olap.dto.ISaikuObject;

public class SaikuUniqueNameComparator implements Comparator<ISaikuObject> {

	public int compare(ISaikuObject o1, ISaikuObject o2) {
		return o1.getUniqueName().compareTo(o2.getUniqueName());
	}
	
}
