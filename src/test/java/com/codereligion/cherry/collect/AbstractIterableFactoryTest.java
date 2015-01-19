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
import com.google.common.collect.Lists;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Tests common logic for all iterable factory methods.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 28.12.2014
 */
public abstract class AbstractIterableFactoryTest {

    @Test
    public void isNotInstantiateable() {
        assertThat(getFactoryClass(), IsNotInstantiatable.isNotInstantiatable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Predicate<Integer> predicate = Predicates.alwaysTrue();

        // when
        createFrom(iterable, predicate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromDoesNotAllowNullPredicate() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = null;

        // when
        createFrom(iterable, predicate);
    }

    @Test
    public void filteringCreateFromFiltersOutUnwantedEntries() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.not(Predicates.equalTo(2));

        // when
        final Iterable<Integer> result = createFrom(iterable, predicate);

        // then
        assertThat(result, not(hasItem(2)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void transformingCreateFromDoesNotAllowNullIterable() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> function = null;

        // when
        createFrom(iterable, function);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transformingCreateFromDoesNotAllowNullFunction() {

        // given
        final Iterable<Integer> iterable = null;
        final Function<Integer, String> function = ToStringFunction.toStringFunction();

        // when
        createFrom(iterable, function);
    }

    @Test
    public void transformingCreateFromTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Function<Integer, String> function = ToStringFunction.toStringFunction();

        // when
        final Iterable<String> result = createFrom(iterable, function);

        // then
        assertThat(result, hasItems("1", "2", "3", "4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringAndTransformingCreateFromDoesNotAllowNullIterable() {

        // given
        final Iterable<Integer> iterable = null;
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> function = ToStringFunction.toStringFunction();

        // when
        createFrom(iterable, predicate, function);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringAndTransformingCreateFromDoesNotAllowNullPredicate() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = null;
        final Function<Integer, String> function = ToStringFunction.toStringFunction();

        // when
        createFrom(iterable, predicate, function);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringAndTransformingCreateFromDoesNotAllowNullFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> function = null;

        // when
        createFrom(iterable, predicate, function);
    }

    @Test
    public void filteringAndTransformingCreateFromFiltersOutUnwantedEntries() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.not(Predicates.equalTo(2));
        final Function<Integer, String> function = ToStringFunction.toStringFunction();

        // when
        final Iterable<String> result = createFrom(iterable, predicate, function);

        // then
        assertThat(result, not(hasItem("2")));
    }

    @Test
    public void filteringAndTransformingCreateFromTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, null, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.notNull();
        final Function<Integer, String> function = ToStringFunction.toStringFunction();

        // when
        final Iterable<String> result = createFrom(iterable, predicate, function);

        // then
        assertThat(result, hasItems("1", "2", "3", "4"));
    }

    protected abstract Iterable<Integer> createFrom(Iterable<Integer> iterable, Predicate<Integer> predicate);

    protected abstract Iterable<String> createFrom(Iterable<Integer> iterable, Function<Integer, String> function);

    protected abstract Iterable<String> createFrom(Iterable<Integer> iterable, Predicate<Integer> predicate, Function<Integer, String> function);

    protected abstract Class<?> getFactoryClass();
}
