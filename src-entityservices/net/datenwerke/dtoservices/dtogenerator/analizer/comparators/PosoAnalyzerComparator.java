package net.datenwerke.dtoservices.dtogenerator.analizer.comparators;

import java.util.Comparator;

import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;

public class PosoAnalyzerComparator implements Comparator<PosoAnalizer> {

	@Override
	public int compare(PosoAnalizer o1, PosoAnalizer o2) {
		return o1.getFullyQualifiedClassName().compareTo(o2.getFullyQualifiedClassName());
	}

}
