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

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import java.util.Comparator;

/**
 * Tests {@link ImmutableSortedSets} contract.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 29.12.2014
 */
public class ImmutableSortedSetsTest extends AbstractSortedSetFactoryTest {

    @Override
    protected Iterable<Integer> from(final Iterable<Integer> iterable, final Predicate<Integer> predicate, final Comparator<Integer> comparator) {
        return ImmutableSortedSets.from(iterable, predicate, comparator);
    }

    @Override
    protected Iterable<String> from(final Iterable<Integer> iterable, final Function<Integer, String> function, final Comparator<String> comparator) {
        return ImmutableSortedSets.from(iterable, function, comparator);
    }

    @Override
    protected Iterable<String> from(final Iterable<Integer> iterable,
                                    final Predicate<Integer> predicate,
                                    final Function<Integer, String> function,
                                    final Comparator<String> comparator) {
        return ImmutableSortedSets.from(iterable, predicate, function, comparator);
    }

    @Override
    protected Iterable<Integer> from(final Iterable<Integer> iterable, final Predicate<Integer> predicate) {
        return ImmutableSortedSets.from(iterable, predicate);
    }

    @Override
    protected Iterable<String> from(final Iterable<Integer> iterable, final Function<Integer, String> function) {
        return ImmutableSortedSets.from(iterable, function);
    }

    @Override
    protected Iterable<String> from(final Iterable<Integer> iterable, final Predicate<Integer> predicate, final Function<Integer, String> function) {
        return ImmutableSortedSets.from(iterable, predicate, function);
    }

    @Override
    protected Class<?> getFactoryClass() {
        return ImmutableSortedSets.class;
    }
}