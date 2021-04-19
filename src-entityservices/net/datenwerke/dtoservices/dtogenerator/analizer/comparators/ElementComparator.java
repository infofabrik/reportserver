package net.datenwerke.dtoservices.dtogenerator.analizer.comparators;

import java.util.Comparator;

import javax.lang.model.element.Element;

public class ElementComparator implements Comparator<Element> {

	@Override
	public int compare(Element o1, Element o2) {
		return o1.getSimpleName().toString().compareTo(o2.getSimpleName().toString());
	}

}
