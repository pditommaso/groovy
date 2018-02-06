package benchmark

import groovy.transform.CompileStatic
import org.codehaus.groovy.runtime.InvokerHelper

/**
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
@CompileStatic
@IgnoreDefaultEqualsAndToString
class MyList extends ArrayList {

    private static final int ITEM_ALLOCATE_SIZE = 5;

    final int maxSize = -1;

    final boolean verbose = false;

    final boolean safe = false;

    MyList() { }

    MyList(Collection c) {
        super(c);
    }

    String toString() {
        StringBuilder buffer = new StringBuilder(ITEM_ALLOCATE_SIZE * this.size());
        buffer.append('[');
        boolean first = true;
        for (Object item : this) {
            if (first) {
                first = false;
            } else {
                buffer.append("; ");
            }
            if (maxSize != -1 && buffer.length() > maxSize) {
                buffer.append("...");
                break;
            }
            if (item == this) {
                buffer.append("(this Collection)");
            } else {
                buffer.append(InvokerHelper.format(item, verbose, sizeLeft(maxSize, buffer), safe));
            }
        }
        buffer.append(']');
        return buffer.toString();
    }

    private static int sizeLeft(int maxSize, StringBuilder buffer) {
        return maxSize == -1 ? maxSize : Math.max(0, maxSize - buffer.length());
    }

}
