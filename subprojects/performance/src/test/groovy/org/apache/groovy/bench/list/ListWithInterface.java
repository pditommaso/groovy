package org.apache.groovy.bench.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
public class ListWithInterface<T> extends ArrayList<T> implements MarkerInterface {

    ListWithInterface(int size) { super(size); }

    ListWithInterface(Collection<T> c) { super(c); }

    public boolean equals(Object obj) {
        return ListHelper.equals(this, (List) obj);
    }

}
