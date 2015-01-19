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
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import java.util.Comparator;
import java.util.Map;
import org.junit.Test;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Tests common logic for all sorted map factory methods.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 29.12.2014
 */
public abstract class AbstractSortedMapFactoryTest extends AbstractMapFactoryTest {

    @Test
    public void createFromWithKeyFunctionOrdersGivenElementsAccordingToTheirNaturalOrder() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, Integer> result = createFrom(iterable, keyFunction);

        // then
        assertThat(result.keySet(), contains("1", "2", "3", "4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFromWithKeyFunctionAndComparatorDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        createFrom(iterable, keyFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFromWithKeyFunctionAndComparatorDoesNotAllowNullKeyFunction() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = null;
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        createFrom(iterable, keyFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFromWithKeyFunctionAndComparatorDoesNotAllowNullKeyComparator() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = null;

        // when
        createFrom(iterable, keyFunction, comparator);
    }

    @Test
    public void createFromWithKeyFunctionAndComparatorTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural();

        // when
        final Map<String, Integer> result = createFrom(iterable, keyFunction, comparator);

        // then
        assertThat(result, hasEntry("1", 1));
        assertThat(result, hasEntry("2", 2));
        assertThat(result, hasEntry("3", 3));
        assertThat(result, hasEntry("4", 4));
    }

    @Test
    public void createFromWithKeyFunctionAndComparatorOrdersGivenElementsAccordingToTheGivenComparator() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Map<String, Integer> result = createFrom(iterable, keyFunction, comparator);

        // then
        assertThat(result.keySet(), contains("4", "3", "2", "1"));
    }

    @Test
    public void createFromWithKeyAndValueFunctionOrdersGivenElementsAccordingToTheirNaturalOrder() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, String> result = createFrom(iterable, keyFunction, valueFunction);

        // then
        assertThat(result.keySet(), contains("1", "2", "3", "4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFromWithKeyAndValueFunctionAndComparatorDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        createFrom(iterable, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFromWithKeyAndValueFunctionAndComparatorDoesNotAllowNullKeyFunction() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = null;
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        createFrom(iterable, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFromWithKeyAndValueFunctionAndComparatorDoesNotAllowNullValueFunction() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = null;
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        createFrom(iterable, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFromWithKeyAndValueFunctionAndComparatorDoesNotAllowNullKeyComparator() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = null;

        // when
        createFrom(iterable, keyFunction, valueFunction, comparator);
    }

    @Test
    public void createFromWithKeyAndValueFunctionAndComparatorTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural();

        // when
        final Map<String, String> result = createFrom(iterable, keyFunction, valueFunction, comparator);

        // then
        assertThat(result, hasEntry("1", "1"));
        assertThat(result, hasEntry("2", "2"));
        assertThat(result, hasEntry("3", "3"));
        assertThat(result, hasEntry("4", "4"));
    }

    @Test
    public void createFromWithKeyAndValueFunctionAndComparatorOrdersGivenElementsAccordingToTheGivenComparator() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Map<String, String> result = createFrom(iterable, keyFunction, valueFunction, comparator);

        // then
        assertThat(result.keySet(), contains("4", "3", "2", "1"));
    }

    @Test
    public void filteringCreateFromWithKeyFunctionOrdersGivenElementsAccordingToTheirNaturalOrder() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, Integer> result = createFrom(iterable, predicate, keyFunction);

        // then
        assertThat(result.keySet(), contains("1", "2", "3", "4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyFunctionAndComparatorDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        createFrom(iterable, predicate, keyFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyFunctionAndComparatorDoesNotAllowNullPredicate() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        createFrom(iterable, predicate, keyFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyFunctionAndComparatorDoesNotAllowNullKeyFunction() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = null;
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        createFrom(iterable, predicate, keyFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyFunctionAndComparatorDoesNotAllowNullKeyComparator() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = null;

        // when
        createFrom(iterable, predicate, keyFunction, comparator);
    }

    @Test
    public void filteringCreateFromWithKeyFunctionAndComparatorTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural();

        // when
        final Map<String, Integer> result = createFrom(iterable, predicate, keyFunction, comparator);

        // then
        assertThat(result, hasEntry("1", 1));
        assertThat(result, hasEntry("2", 2));
        assertThat(result, hasEntry("3", 3));
        assertThat(result, hasEntry("4", 4));
    }

    @Test
    public void filteringCreateFromWithKeyFunctionAndComparatorOrdersGivenElementsAccordingToTheGivenComparator() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Map<String, Integer> result = createFrom(iterable, predicate, keyFunction, comparator);

        // then
        assertThat(result.keySet(), contains("4", "3", "2", "1"));
    }

    @Test
    public void filteringCreateFromWithKeyFunctionAndComparatorFiltersOutUnwantedElements() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Predicate<Integer> predicate = Predicates.not(Predicates.equalTo(2));
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Map<String, Integer> result = createFrom(iterable, predicate, keyFunction, comparator);

        // then
        assertThat(result, not(hasEntry("2", 2)));
    }

    @Test
    public void filteringCreateFromWithKeyAndValueFunctionOrdersGivenElementsAccordingToTheirNaturalOrder() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, String> result = createFrom(iterable, predicate, keyFunction, valueFunction);

        // then
        assertThat(result.keySet(), contains("1", "2", "3", "4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyAndValueFunctionAndComparatorDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        createFrom(iterable, predicate, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyAndValueFunctionAndComparatorDoesNotAllowNullPredicate() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        createFrom(iterable, predicate, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyAndValueFunctionAndComparatorDoesNotAllowNullKeyFunction() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = null;
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        createFrom(iterable, predicate, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyAndValueFunctionAndComparatorDoesNotAllowNullValueFunction() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = null;
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        createFrom(iterable, predicate, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringCreateFromWithKeyAndValueFunctionAndComparatorDoesNotAllowNullKeyComparator() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = null;

        // when
        createFrom(iterable, predicate, keyFunction, valueFunction, comparator);
    }

    @Test
    public void filteringCreateFromWithKeyAndValueFunctionAndComparatorTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural();

        // when
        final Map<String, String> result = createFrom(iterable, predicate, keyFunction, valueFunction, comparator);

        // then
        assertThat(result, hasEntry("1", "1"));
        assertThat(result, hasEntry("2", "2"));
        assertThat(result, hasEntry("3", "3"));
        assertThat(result, hasEntry("4", "4"));
    }

    @Test
    public void filteringCreateFromWithKeyAndValueFunctionAndComparatorOrdersGivenElementsAccordingToTheGivenComparator() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Map<String, String> result = createFrom(iterable, predicate, keyFunction, valueFunction, comparator);

        // then
        assertThat(result.keySet(), contains("4", "3", "2", "1"));
    }

    @Test
    public void filteringCreateFromWithKeyAndValueFunctionAndComparatorFiltersOutUnwantedElements() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Predicate<Integer> predicate = Predicates.not(Predicates.equalTo(2));
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Map<String, String> result = createFrom(iterable, predicate, keyFunction, valueFunction, comparator);

        // then
        assertThat(result, not(hasEntry("2", "2")));
    }

    protected abstract Map<String, Integer> createFrom(Iterable<Integer> iterable, Function<Integer, String> keyFunction, Comparator<String> comparator);

    protected abstract Map<String, String> createFrom(Iterable<Integer> iterable,
                                                Function<Integer, String> keyFunction,
                                                Function<Integer, String> valueFunction,
                                                Comparator<String> comparator);

    protected abstract Map<String, Integer> createFrom(Iterable<Integer> iterable,
                                                 Predicate<Integer> predicate,
                                                 Function<Integer, String> keyFunction,
                                                 Comparator<String> comparator);

    protected abstract Map<String, String> createFrom(Iterable<Integer> iterable,
                                                Predicate<Integer> predicate,
                                                Function<Integer, String> keyFunction,
                                                Function<Integer, String> valueFunction,
                                                Comparator<String> comparator);
}
