package net.sf.jxls.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jxls.parser.Expression;
import net.sf.jxls.transformer.Configuration;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;

/**
 * @author Leonid Vysochyn
 */
public class ReportUtil {

    public static Collection groupCollectionData(Collection objects, String groupBy) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Collection result = new ArrayList();
        if (objects != null) {
            Set groupByValues = new TreeSet(); // using TreeSet to ensure groups are sorted according to natural order
            for (Iterator iterator = objects.iterator(); iterator.hasNext();) {
                Object bean = iterator.next();
                groupByValues.add(PropertyUtils.getProperty(bean, groupBy));
            }
            for (Iterator iterator = groupByValues.iterator(); iterator.hasNext();) {
                Object groupValue = iterator.next();
                BeanPropertyValueEqualsPredicate predicate = new BeanPropertyValueEqualsPredicate(groupBy, groupValue);
                Collection groupItems = CollectionUtils.select(objects, predicate);
                GroupData groupData = new GroupData(CollectionUtils.get(groupItems, 0), groupItems);
                result.add(groupData);
            }
        }
        return result;
    }

    /**
     * Groups collection of objects by given object property using provided groupOrder
     *
     * @param objects    - Collection of objects to group
     * @param groupBy    - Name of the property to group objects in the original collection
     * @param groupOrder - Indicates how groups should be sorted. If groupOrder is null then group order is preserved the same
     *                   as iteration order of the original collection. If groupOrder equals to "asc" or "desc" (case insensitive)
     *                   groups will be sorted accordingly
     * @param select     - binary expression used to select which items go in the collection
     * @param configuration - {@link Configuration} class for this transformation
     * @return Collection of {@link GroupData} objects containing group data and collection of corresponding group items
     * @throws NoSuchMethodException     - Thrown when there is an error accessing given bean property by reflection
     * @throws IllegalAccessException    - Thrown when there is an error accessing given bean property by reflection
     * @throws InvocationTargetException - Thrown when there is an error accessing given bean property by reflection
     */
//    public static Collection groupCollectionData(Collection objects, String groupBy, String groupOrder) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    public static Collection groupCollectionData(Collection objects, String groupBy, String groupOrder, String select, Configuration configuration) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Collection result = new ArrayList();
        if (objects != null) {
            Set groupByValues;
            if (groupOrder != null) {
                if ("desc".equalsIgnoreCase(groupOrder)) {
                    groupByValues = new TreeSet(Collections.reverseOrder()); // using TreeSet with comparator to ensure groups are sorted according to reversed natural order
                } else {
                    groupByValues = new TreeSet(); // using TreeSet to ensure groups are sorted according to natural order
                }
            } else {
                groupByValues = new LinkedHashSet(); // using LinkedHashSet to ensure groups iteration order is preserved
            }
            Map beans = new HashMap();
            for (Iterator iterator = objects.iterator(); iterator.hasNext();) {
                Object bean = iterator.next();
                beans.put("group.item", bean);
                if (shouldSelectCollectionData(beans, select, configuration)) {
                    groupByValues.add(PropertyUtils.getProperty(bean, groupBy));
                }
            }
            for (Iterator iterator = groupByValues.iterator(); iterator.hasNext();) {
                Object groupValue = iterator.next();
                BeanPropertyValueEqualsPredicate predicate = new BeanPropertyValueEqualsPredicate(groupBy, groupValue);
                Collection groupItems = CollectionUtils.select(objects, predicate);
                GroupData groupData = new GroupData(CollectionUtils.get(groupItems, 0), groupItems);
                result.add(groupData);
            }
        }
        return result;
    }

    public static boolean shouldSelectCollectionData(Map beans, String select, Configuration configuration) {
        if (select == null) return true;
        try {
            Expression expr = new Expression(select, beans, configuration);
            Object obj = expr.evaluate();
            if (obj instanceof Boolean) return ((Boolean) obj).booleanValue();
            return false;
        }
        catch (Exception e) {
            System.err.println("Exception evaluation select '" + select + "': " + e);
            return false;
        }
    }

}
