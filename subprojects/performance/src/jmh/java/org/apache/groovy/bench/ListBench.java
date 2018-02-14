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
package org.apache.groovy.bench.list;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class ListBench {

    @Param({"1", "10", "100", "1000", "1000000"})
    private int count;

    @Benchmark
    public void equalsDefaultGroovy() {

        List<String> list1 = new ArrayList<>(count);
        List<String> list2 = new ArrayList<>(count);

        for( int i=0; i<count; i++) {
            list1.add(UUID.randomUUID().toString());
            list2.add(UUID.randomUUID().toString());
        }

        ListHelper.equals(list1, list2);
        ListHelper.equals(list1, new ArrayList<>(list1));
    }

    @Benchmark
    public void equalsWithAnnotation() {

        List<String> list1 = new ListWithAnnotation<>(count);
        List<String> list2 = new ArrayList<>(count);

        for( int i=0; i<count; i++) {
            list1.add(UUID.randomUUID().toString());
            list2.add(UUID.randomUUID().toString());
        }

        ListHelper.equalsWithAnnotation(list1, list2);
        ListHelper.equalsWithAnnotation(list1, new ListWithAnnotation<>(list1));
    }

    @Benchmark
    public void equalsWithInterface() {

        List<String> list1 = new ListWithInterface<>(count);
        List<String> list2 = new ListWithInterface<>(count);

        for( int i=0; i<count; i++) {
            list1.add(UUID.randomUUID().toString());
            list2.add(UUID.randomUUID().toString());
        }

        ListHelper.equalsWithInterface(list1, list2);
        ListHelper.equalsWithInterface(list1, new ListWithInterface<>(list1));
    }
}
