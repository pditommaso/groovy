package org.apache.groovy.bench.list;

import java.util.Iterator;
import java.util.List;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

/**
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
public class ListHelper {

    public static boolean equals(List left, List right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return false;
        }
        if (left == right) {
            return true;
        }
        if (left.size() != right.size()) {
            return false;
        }
        final Iterator it1 = left.iterator(), it2 = right.iterator();
        while (it1.hasNext()) {
            final Object o1 = it1.next();
            final Object o2 = it2.next();
            if (o1 == null) {
                if (o2 != null) return false;
            } else if (!coercedEquals(o1, o2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsWithAnnotation(List left, List right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return false;
        }
        if (left == right) {
            return true;
        }
        if( left.getClass().getAnnotation(MarkerAnnotation.class)!=null && right.getClass().getAnnotation(MarkerAnnotation.class)!=null ) {
            return left.equals(right);
        }
        if (left.size() != right.size()) {
            return false;
        }
        final Iterator it1 = left.iterator(), it2 = right.iterator();
        while (it1.hasNext()) {
            final Object o1 = it1.next();
            final Object o2 = it2.next();
            if (o1 == null) {
                if (o2 != null) return false;
            } else if (!coercedEquals(o1, o2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsWithInterface(List left, List right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return false;
        }
        if (left == right) {
            return true;
        }
        if( left instanceof MarkerInterface && right instanceof MarkerInterface) {
            return left.equals(right);
        }
        if (left.size() != right.size()) {
            return false;
        }
        final Iterator it1 = left.iterator(), it2 = right.iterator();
        while (it1.hasNext()) {
            final Object o1 = it1.next();
            final Object o2 = it2.next();
            if (o1 == null) {
                if (o2 != null) return false;
            } else if (!coercedEquals(o1, o2)) {
                return false;
            }
        }
        return true;
    }

    private static boolean coercedEquals(Object o1, Object o2) {
        if (o1 instanceof Comparable) {
            if (!(o2 instanceof Comparable && DefaultGroovyMethods.numberAwareCompareTo((Comparable) o1, (Comparable) o2) == 0)) {
                return false;
            }
        }
        return DefaultTypeTransformation.compareEqual(o1, o2);
    }
}
