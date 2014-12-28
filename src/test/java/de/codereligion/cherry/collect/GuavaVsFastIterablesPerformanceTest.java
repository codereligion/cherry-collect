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
package de.codereligion.cherry.collect;

import com.google.common.base.Predicate;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Collections2;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class GuavaVsFastIterablesPerformanceTest {

    private static final Predicate<Long> IS_EVEN = new Predicate<Long>() {
        @Override
        public boolean apply(final Long input) {
            return input % 2 == 0;
        }
    };

    static {
        // dry run to make sure class loading is not part of the measurement

        final Collection<Long> numbers = Lists.newArrayList(1L, 2L);
        FluentIterable.from(numbers).filter(IS_EVEN).toList().size();
        Collections2.filter(numbers, IS_EVEN).size();
        FastIterables.filterToArrayList(numbers, IS_EVEN).size();
    }

    @Test
    public void filterToList() {

        final int powers = 20;
        for (int p = 1; p <= powers; p++) {

            final long numElements = (long) Math.pow(2, p);

            System.out.println();
            System.out.println("Testing with " + numElements + " elements.");

            final Collection<Long> numbers = createLongsUntil(numElements);

            measureMillisAndPrint("FluentIterable", new Runnable() {

                @Override
                public void run() {
                    final ImmutableList<Long> result = FluentIterable.from(numbers).filter(IS_EVEN).toList();
                    result.size();
                    result.size();
                    result.size();
                    result.size();
                    result.size();
                }
            });

            measureMillisAndPrint("Collections2", new Runnable() {

                @Override
                public void run() {
                    final Collection<Long> result = Collections2.filter(numbers, IS_EVEN);
                    result.size();
                    result.size();
                    result.size();
                    result.size();
                    result.size();
                }
            });

            measureMillisAndPrint("Collections3", new Runnable() {

                @Override
                public void run() {
                    final List<Long> result = FastIterables.filterToArrayList(numbers, IS_EVEN);
                    result.size();
                    result.size();
                    result.size();
                    result.size();
                    result.size();
                }
            });
        }

    }

    private void measureMillisAndPrint(final String operationName, final Runnable runnable) {
        System.out.println(operationName + ": " + measureMillis(runnable) + "ms");
    }

    private long measureMillis(final Runnable runnable) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        runnable.run();
        return stopwatch.elapsed(TimeUnit.MILLISECONDS);
    }

    private List<Long> createLongsUntil(final long limit) {
        final List<Long> list = Lists.newArrayList();

        for (long i = 0; i < limit; i++) {
            list.add(i);
        }

        return list;
    }

}