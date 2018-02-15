package org.apache.groovy.bench.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;

/**
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
@MarkerAnnotation
public class ListWithAnnotation<T> extends ArrayList<T> {

    public ListWithAnnotation(int size) { super(size); }

    public ListWithAnnotation(Collection<T> c) { super(c); }

    public boolean equals(Object obj) {
        return DefaultGroovyMethods.equals(this, (List) obj);
    }

}
