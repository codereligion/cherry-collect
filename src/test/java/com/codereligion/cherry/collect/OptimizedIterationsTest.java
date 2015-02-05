/**
 * Copyright 2014 www.codereligion.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codereligion.cherry.collect;

import com.codereligion.cherry.function.ToStringFunction;
import com.codereligion.cherry.matcher.IsNotInstantiatable;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class OptimizedIterationsTest {

    private final Predicate<Integer> predicate = Predicates.alwaysTrue();
    private final Function<Object, String> function = ToStringFunction.toStringFunction();

    private final List<TestCandidate> testCandidates = Lists.newArrayList(new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, function, new ArrayList<String>());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, predicate, new ArrayList<Integer>());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, predicate, function, new ArrayList<String>());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, function, ImmutableSortedSet.<String>naturalOrder());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, predicate, ImmutableSortedSet.<Integer>naturalOrder());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, predicate, function, ImmutableSortedSet.<String>naturalOrder());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, function, new HashMap<String, Integer>());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, function, function, new HashMap<String, String>());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, predicate, function, new HashMap<String, Integer>());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, predicate, function, function, new HashMap<String, String>());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, function, ArrayListMultimap.<String, Integer>create());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, function, function, ArrayListMultimap.<String, String>create());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, predicate, function, ArrayListMultimap.<String, Integer>create());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, predicate, function, function, ArrayListMultimap.<String, String>create());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, function, ImmutableMap.<String, Integer>builder());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, function, function, ImmutableMap.<String, String>builder());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, predicate, function, ImmutableMap.<String, Integer>builder());
        }
    }, new TestCandidate() {
        @Override
        public void run(final Iterable<Integer> iterable) {
            OptimizedIterations.createFrom(iterable, predicate, function, function, ImmutableMap.<String, String>builder());
        }
    });

    @Test
    public void isNotInstantiateable() {
        assertThat(OptimizedIterations.class, IsNotInstantiatable.isNotInstantiatable());
    }

    @Test
    public void allMethodsAvoidIteratorInstantiationForArrayLists() {

        // given
        final List<Integer> list = spy(Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

        // when
        runOnAllTestCandidates(list);

        // then
        verify(list, never()).iterator();
    }

    @Test
    public void allMethodsUseIteratorForLinkedLists() {

        // given
        final List<Integer> list = spy(Lists.newLinkedList(Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));

        // when
        runOnAllTestCandidates(list);

        // then
        verify(list, times(testCandidates.size())).iterator();
    }

    private void runOnAllTestCandidates(final Iterable<Integer> iterable) {
        for (final TestCandidate testCandidate : testCandidates) {
            testCandidate.run(iterable);
        }
    }

    private static interface TestCandidate {
        void run(Iterable<Integer> iterable);
    }
}