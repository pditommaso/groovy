package org.apache.groovy.bench.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;

/**
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
public class ListWithInterface<T> extends ArrayList<T> implements MarkerInterface {

    public ListWithInterface(int size) { super(size); }

    public ListWithInterface(Collection<T> c) { super(c); }

    public boolean equals(Object obj) {
        return DefaultGroovyMethods.equals(this, (List) obj);
    }

}
