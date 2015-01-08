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
    public void fromMethodWithKeyFunctionOrdersGivenElementsAccordingToTheirNaturalOrder() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, Integer> result = from(iterable, keyFunction);

        // then
        assertThat(result.keySet(), contains("1", "2", "3", "4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromMethodWithKeyFunctionAndComparatorDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        from(iterable, keyFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromMethodWithKeyFunctionAndComparatorDoesNotAllowNullKeyFunction() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = null;
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        from(iterable, keyFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromMethodWithKeyFunctionAndComparatorDoesNotAllowNullKeyComparator() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = null;

        // when
        from(iterable, keyFunction, comparator);
    }

    @Test
    public void fromMethodWithKeyFunctionAndComparatorTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural();

        // when
        final Map<String, Integer> result = from(iterable, keyFunction, comparator);

        // then
        assertThat(result, hasEntry("1", 1));
        assertThat(result, hasEntry("2", 2));
        assertThat(result, hasEntry("3", 3));
        assertThat(result, hasEntry("4", 4));
    }

    @Test
    public void fromMethodWithKeyFunctionAndComparatorOrdersGivenElementsAccordingToTheGivenComparator() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Map<String, Integer> result = from(iterable, keyFunction, comparator);

        // then
        assertThat(result.keySet(), contains("4", "3", "2", "1"));
    }

    @Test
    public void fromMethodWithKeyAndValueFunctionOrdersGivenElementsAccordingToTheirNaturalOrder() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, String> result = from(iterable, keyFunction, valueFunction);

        // then
        assertThat(result.keySet(), contains("1", "2", "3", "4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromMethodWithKeyAndValueFunctionAndComparatorDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        from(iterable, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromMethodWithKeyAndValueFunctionAndComparatorDoesNotAllowNullKeyFunction() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = null;
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        from(iterable, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromMethodWithKeyAndValueFunctionAndComparatorDoesNotAllowNullValueFunction() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = null;
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        from(iterable, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromMethodWithKeyAndValueFunctionAndComparatorDoesNotAllowNullKeyComparator() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = null;

        // when
        from(iterable, keyFunction, valueFunction, comparator);
    }

    @Test
    public void fromMethodWithKeyAndValueFunctionAndComparatorTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural();

        // when
        final Map<String, String> result = from(iterable, keyFunction, valueFunction, comparator);

        // then
        assertThat(result, hasEntry("1", "1"));
        assertThat(result, hasEntry("2", "2"));
        assertThat(result, hasEntry("3", "3"));
        assertThat(result, hasEntry("4", "4"));
    }

    @Test
    public void fromMethodWithKeyAndValueFunctionAndComparatorOrdersGivenElementsAccordingToTheGivenComparator() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Map<String, String> result = from(iterable, keyFunction, valueFunction, comparator);

        // then
        assertThat(result.keySet(), contains("4", "3", "2", "1"));
    }

    @Test
    public void filteringFromMethodWithKeyFunctionOrdersGivenElementsAccordingToTheirNaturalOrder() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, Integer> result = from(iterable, predicate, keyFunction);

        // then
        assertThat(result.keySet(), contains("1", "2", "3", "4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyFunctionAndComparatorDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        from(iterable, predicate, keyFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyFunctionAndComparatorDoesNotAllowNullPredicate() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        from(iterable, predicate, keyFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyFunctionAndComparatorDoesNotAllowNullKeyFunction() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = null;
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        from(iterable, predicate, keyFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyFunctionAndComparatorDoesNotAllowNullKeyComparator() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = null;

        // when
        from(iterable, predicate, keyFunction, comparator);
    }

    @Test
    public void filteringFromMethodWithKeyFunctionAndComparatorTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural();

        // when
        final Map<String, Integer> result = from(iterable, predicate, keyFunction, comparator);

        // then
        assertThat(result, hasEntry("1", 1));
        assertThat(result, hasEntry("2", 2));
        assertThat(result, hasEntry("3", 3));
        assertThat(result, hasEntry("4", 4));
    }

    @Test
    public void filteringFromMethodWithKeyFunctionAndComparatorOrdersGivenElementsAccordingToTheGivenComparator() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Map<String, Integer> result = from(iterable, predicate, keyFunction, comparator);

        // then
        assertThat(result.keySet(), contains("4", "3", "2", "1"));
    }

    @Test
    public void filteringFromMethodWithKeyFunctionAndComparatorFiltersOutUnwantedElements() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Predicate<Integer> predicate = Predicates.not(Predicates.equalTo(2));
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Map<String, Integer> result = from(iterable, predicate, keyFunction, comparator);

        // then
        assertThat(result, not(hasEntry("2", 2)));
    }

    @Test
    public void filteringFromMethodWithKeyAndValueFunctionOrdersGivenElementsAccordingToTheirNaturalOrder() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        final Map<String, String> result = from(iterable, predicate, keyFunction, valueFunction);

        // then
        assertThat(result.keySet(), contains("1", "2", "3", "4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyAndValueFunctionAndComparatorDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        from(iterable, predicate, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyAndValueFunctionAndComparatorDoesNotAllowNullPredicate() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        from(iterable, predicate, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyAndValueFunctionAndComparatorDoesNotAllowNullKeyFunction() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = null;
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        from(iterable, predicate, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyAndValueFunctionAndComparatorDoesNotAllowNullValueFunction() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = null;
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        from(iterable, predicate, keyFunction, valueFunction, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyAndValueFunctionAndComparatorDoesNotAllowNullKeyComparator() {
        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = null;

        // when
        from(iterable, predicate, keyFunction, valueFunction, comparator);
    }

    @Test
    public void filteringFromMethodWithKeyAndValueFunctionAndComparatorTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural();

        // when
        final Map<String, String> result = from(iterable, predicate, keyFunction, valueFunction, comparator);

        // then
        assertThat(result, hasEntry("1", "1"));
        assertThat(result, hasEntry("2", "2"));
        assertThat(result, hasEntry("3", "3"));
        assertThat(result, hasEntry("4", "4"));
    }

    @Test
    public void filteringFromMethodWithKeyAndValueFunctionAndComparatorOrdersGivenElementsAccordingToTheGivenComparator() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Map<String, String> result = from(iterable, predicate, keyFunction, valueFunction, comparator);

        // then
        assertThat(result.keySet(), contains("4", "3", "2", "1"));
    }

    @Test
    public void filteringFromMethodWithKeyAndValueFunctionAndComparatorFiltersOutUnwantedElements() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(4, 1, 3, 2);
        final Predicate<Integer> predicate = Predicates.not(Predicates.equalTo(2));
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Map<String, String> result = from(iterable, predicate, keyFunction, valueFunction, comparator);

        // then
        assertThat(result, not(hasEntry("2", "2")));
    }

    protected abstract Map<String, Integer> from(Iterable<Integer> iterable, Function<Integer, String> keyFunction, Comparator<String> comparator);

    protected abstract Map<String, String> from(Iterable<Integer> iterable,
                                                Function<Integer, String> keyFunction,
                                                Function<Integer, String> valueFunction,
                                                Comparator<String> comparator);

    protected abstract Map<String, Integer> from(Iterable<Integer> iterable,
                                                 Predicate<Integer> predicate,
                                                 Function<Integer, String> keyFunction,
                                                 Comparator<String> comparator);

    protected abstract Map<String, String> from(Iterable<Integer> iterable,
                                                Predicate<Integer> predicate,
                                                Function<Integer, String> keyFunction,
                                                Function<Integer, String> valueFunction,
                                                Comparator<String> comparator);
}
