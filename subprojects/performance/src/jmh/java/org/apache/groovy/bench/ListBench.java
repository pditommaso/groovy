/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.groovy.bench;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.groovy.bench.list.ListHelper;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(3)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class ListBench {

    @Param({"1", "10", "100", "1000", "1000000"})
    private int size;

    List<String> list1;
    List<String> list2;
    List<String> list3;

    public ListBench() {
        List<String> list1 = new ArrayList<>(size);
        List<String> list2 = new ArrayList<>(size);

        for(int i = 0; i< size; i++) {
            list1.add(UUID.randomUUID().toString());
            list2.add(UUID.randomUUID().toString());
        }

        list3 = new ArrayList<>(list1);
    }

    @Benchmark
    public void equalsDefaultGroovy() {
        // uses plain groovy equals to compare the
        DefaultGroovyMethods.equals(list1, list2);
        DefaultGroovyMethods.equals(list1, list3);
    }

    @Benchmark
    public void equalsWithAnnotation() {
        // use a modified version of equals method that checks if both classes
        // are annotated with the MarkerAnnotation
        ListHelper.equalsWithAnnotation(list1, list2);
        ListHelper.equalsWithAnnotation(list1, list3);
    }

    @Benchmark
    public void equalsWithInterface() {
        // use a modified version of equals method that checks if both classes
        // declare a Marker interface
        ListHelper.equalsWithInterface(list1, list2);
        ListHelper.equalsWithInterface(list1, list3);
    }
}
