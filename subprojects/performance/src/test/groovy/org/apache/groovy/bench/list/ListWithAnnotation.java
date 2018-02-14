package org.apache.groovy.bench.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
@MarkerAnnotation
public class ListWithAnnotation<T> extends ArrayList<T> {

    ListWithAnnotation(int size) { super(size); }

    ListWithAnnotation(Collection<T> c) { super(c); }

    public boolean equals(Object obj) {
        return ListHelper.equals(this, (List) obj);
    }

}
