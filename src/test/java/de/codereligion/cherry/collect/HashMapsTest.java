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

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import java.util.Map;

/**
 * Tests {@link de.codereligion.cherry.collect.HashMaps} contract.
 *
 * @author sgroebler
 * @since 29.12.2014
 */
public class HashMapsTest extends AbstractMapFactoryMethodTest {

    @Override
    protected Map<String, Integer> from(final Iterable<Integer> iterable, final Function<Integer, String> keyFunction) {
        return HashMaps.from(iterable, keyFunction);
    }

    @Override
    protected Map<String, String> from(final Iterable<Integer> iterable,
                                       final Function<Integer, String> keyFunction,
                                       final Function<Integer, String> valueFunction) {
        return HashMaps.from(iterable, keyFunction, valueFunction);
    }

    @Override
    protected Map<String, Integer> from(final Iterable<Integer> iterable, final Predicate<Integer> predicate, final Function<Integer, String> keyFunction) {
        return HashMaps.from(iterable, predicate, keyFunction);
    }

    @Override
    protected Map<String, String> from(final Iterable<Integer> iterable,
                                       final Predicate<Integer> predicate,
                                       final Function<Integer, String> keyFunction,
                                       final Function<Integer, String> valueFunction) {
        return HashMaps.from(iterable, predicate, keyFunction, valueFunction);
    }

    @Override
    protected Class<?> getFactoryClass() {
        return HashMaps.class;
    }
}