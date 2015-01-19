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
import com.google.common.collect.ArrayListMultimap;

/**
 * Tests {@link com.codereligion.cherry.collect.ArrayListMultimaps} contract.
 *
 * @author sgroebler
 * @since 29.12.2014
 */
public class ArrayListMultimapsTest extends AbstractMultimapFactoryTest {

    @Override
    protected ArrayListMultimap<String, Integer> createFrom(final Iterable<Integer> iterable, final Function<Integer, String> keyFunction) {
        return ArrayListMultimaps.createFrom(iterable, keyFunction);
    }

    @Override
    protected ArrayListMultimap<String, String> createFrom(final Iterable<Integer> iterable,
                                                     final Function<Integer, String> keyFunction,
                                                     final Function<Integer, String> valueFunction) {
        return ArrayListMultimaps.createFrom(iterable, keyFunction, valueFunction);
    }

    @Override
    protected ArrayListMultimap<String, Integer> createFrom(final Iterable<Integer> iterable,
                                                      final Predicate<Integer> predicate,
                                                      final Function<Integer, String> keyFunction) {
        return ArrayListMultimaps.createFrom(iterable, predicate, keyFunction);
    }

    @Override
    protected ArrayListMultimap<String, String> createFrom(final Iterable<Integer> iterable,
                                                     final Predicate<Integer> predicate,
                                                     final Function<Integer, String> keyFunction,
                                                     final Function<Integer, String> valueFunction) {
        return ArrayListMultimaps.createFrom(iterable, predicate, keyFunction, valueFunction);
    }

    @Override
    protected Class<?> getFactoryClass() {
        return ArrayListMultimaps.class;
    }
}
