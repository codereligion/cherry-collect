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
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.codereligion.cherry.function.ToStringFunction;
import com.codereligion.cherry.matcher.IsNotInstantiatable;
import java.util.Map;
import org.junit.Test;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Tests common logic for all map factory methods.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 29.12.2014
 */
public abstract class AbstractMapFactoryTest {

    @Test
    public void isNotInstantiateable() {
        assertThat(getFactoryClass(), IsNotInstantiatable.isNotInstantiatable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFromWithKeyFunctionDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        createFrom(iterable, keyFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFromWithKeyFunctionDoesNotAllowNullKeyFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = null;

        // when
        createFrom(iterable, keyFunction);
    }

    @Test
    public void createFromWithKeyFunctionTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, Integer> result = createFrom(iterable, keyFunction);

        // then
        assertThat(result, hasEntry("1", 1));
        assertThat(result, hasEntry("2", 2));
        assertThat(result, hasEntry("3", 3));
        assertThat(result, hasEntry("4", 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFromWithKeyAndValueFunctionDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        createFrom(iterable, keyFunction, valueFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFromWithKeyAndValueFunctionDoesNotAllowNullKeyFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = null;
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        createFrom(iterable, keyFunction, valueFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFromWithKeyAndValueFunctionDoesNotAllowNullValueFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = null;

        // when
        createFrom(iterable, keyFunction, valueFunction);
    }

    @Test
    public void createFromWithKeyAndValueFunctionTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, String> result = createFrom(iterable, keyFunction, valueFunction);

        // then
        assertThat(result, hasEntry("1", "1"));
        assertThat(result, hasEntry("2", "2"));
        assertThat(result, hasEntry("3", "3"));
        assertThat(result, hasEntry("4", "4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyFunctionDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        createFrom(iterable, predicate, keyFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyFunctionDoesNotAllowNullPredicate() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        createFrom(iterable, predicate, keyFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyFunctionDoesNotAllowNullKeyFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = null;

        // when
        createFrom(iterable, predicate, keyFunction);
    }

    @Test
    public void filteringCreateFromWithKeyFunctionTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, Integer> result = createFrom(iterable, predicate, keyFunction);

        // then
        assertThat(result, hasEntry("1", 1));
        assertThat(result, hasEntry("2", 2));
        assertThat(result, hasEntry("3", 3));
        assertThat(result, hasEntry("4", 4));
    }

    @Test
    public void filteringCreateFromWithKeyFunctionFiltersOutUnwantedEntries() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.not(Predicates.equalTo(2));
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, Integer> result = createFrom(iterable, predicate, keyFunction);

        // then
        assertThat(result, not(hasEntry("2", 2)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyAndValueFunctionDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        createFrom(iterable, predicate, keyFunction, valueFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyAndValueFunctionDoesNotAllowNullPredicate() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();;
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        createFrom(iterable, predicate, keyFunction, valueFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyAndValueFunctionDoesNotAllowNullKeyFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = null;
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        createFrom(iterable, predicate, keyFunction, valueFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyAndValueFunctionDoesNotAllowNullValueFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = null;

        // when
        createFrom(iterable, predicate, keyFunction, valueFunction);
    }

    @Test
    public void filteringCreateFromWithKeyAndValueFunctionTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, String> result = createFrom(iterable, predicate, keyFunction, valueFunction);

        // then
        assertThat(result, hasEntry("1", "1"));
        assertThat(result, hasEntry("2", "2"));
        assertThat(result, hasEntry("3", "3"));
        assertThat(result, hasEntry("4", "4"));
    }

    @Test
    public void filteringCreateFromWithKeyAndValueFunctionFiltersOutUnwantedEntries() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.not(Predicates.equalTo(2));
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, String> result = createFrom(iterable, predicate, keyFunction, valueFunction);

        // then
        assertThat(result, not(hasEntry("2", "2")));
    }

    protected abstract Map<String, Integer> createFrom(Iterable<Integer> iterable, Function<Integer, String> keyFunction);

    protected abstract Map<String, String> createFrom(Iterable<Integer> iterable, Function<Integer, String> keyFunction, Function<Integer, String> valueFunction);

    protected abstract Map<String, Integer> createFrom(Iterable<Integer> iterable, Predicate<Integer> predicate, Function<Integer, String> keyFunction);

    protected abstract Map<String, String> createFrom(Iterable<Integer> iterable, Predicate<Integer> predicate, Function<Integer, String> keyFunction, Function<Integer, String> valueFunction);

    protected abstract Class<?> getFactoryClass();
}
