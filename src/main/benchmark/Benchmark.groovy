package benchmark

import groovy.transform.CompileStatic

/**
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
@CompileStatic
class Benchmark {

    static void main(String... args) {

        int count = args[0].toInteger()

        long now = System.currentTimeMillis();
        for(int i=0; i<count; i++) {
            new MyList(1..1_000).toString();
        }

        println "Iterations  : $count"
        println "Elapsed time: ${System.currentTimeMillis()-now} millis"
        println "Print sample: ${new MyList(1..10)}"

    }

}
