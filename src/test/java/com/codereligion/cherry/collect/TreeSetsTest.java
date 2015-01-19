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
 * Tests {@link com.codereligion.cherry.collect.TreeSets} contract.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 29.12.2014
 */
public class TreeSetsTest extends AbstractSortedSetFactoryTest {


    @Override
    protected Iterable<Integer> createFrom(final Iterable<Integer> iterable, final Predicate<Integer> predicate, final Comparator<Integer> comparator) {
        return TreeSets.createFrom(iterable, predicate, comparator);
    }

    @Override
    protected Iterable<String> createFrom(final Iterable<Integer> iterable, final Function<Integer, String> function, final Comparator<String> comparator) {
        return TreeSets.createFrom(iterable, function, comparator);
    }

    @Override
    protected Iterable<String> createFrom(final Iterable<Integer> iterable,
                                    final Predicate<Integer> predicate,
                                    final Function<Integer, String> function,
                                    final Comparator<String> comparator) {
        return TreeSets.createFrom(iterable, predicate, function, comparator);
    }

    @Override
    protected Iterable<Integer> createFrom(final Iterable<Integer> iterable, final Predicate<Integer> predicate) {
        return TreeSets.createFrom(iterable, predicate);
    }

    @Override
    protected Iterable<String> createFrom(final Iterable<Integer> iterable, final Function<Integer, String> function) {
        return TreeSets.createFrom(iterable, function);
    }

    @Override
    protected Iterable<String> createFrom(final Iterable<Integer> iterable, final Predicate<Integer> predicate, final Function<Integer, String> function) {
        return TreeSets.createFrom(iterable, predicate, function);
    }

    @Override
    protected Class<?> getFactoryClass() {
        return TreeSets.class;
    }
}
