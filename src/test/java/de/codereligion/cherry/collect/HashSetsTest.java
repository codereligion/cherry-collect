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

/**
 * Tests {@link de.codereligion.cherry.collect.HashSets} contract.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 29.12.2014
 */
public class HashSetsTest extends AbstractIterableFactoryMethodTest {

    @Override
    protected <ENTRY> Iterable<ENTRY> from(final Iterable<ENTRY> iterable, final Predicate<? super ENTRY> predicate) {
        return HashSets.from(iterable, predicate);
    }

    @Override
    protected <FROM_ENTRY, TO_ENTRY> Iterable<TO_ENTRY> from(final Iterable<FROM_ENTRY> iterable, final Function<? super FROM_ENTRY, TO_ENTRY> function) {
        return HashSets.from(iterable, function);
    }

    @Override
    protected <FROM_ENTRY, TO_ENTRY> Iterable<TO_ENTRY> from(final Iterable<FROM_ENTRY> iterable,
                                                             final Predicate<? super FROM_ENTRY> predicate,
                                                             final Function<? super FROM_ENTRY, TO_ENTRY> function) {
        return HashSets.from(iterable, predicate, function);
    }

    @Override
    protected Class<?> getFactoryClass() {
        return HashSets.class;
    }
}
