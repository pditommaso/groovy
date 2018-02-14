package org.apache.groovy.bench.list;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker interface to by-pass the default groovy formatting 
 * and equality rules and allowing a user to provide a custom 
 * format and equals method
 *
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface MarkerAnnotation {
}